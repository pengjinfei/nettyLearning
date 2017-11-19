package com.pengjinfei.netty.ch12;

import io.netty.channel.ChannelHandler;
import io.netty.channel.ChannelHandlerAdapter;
import io.netty.channel.ChannelHandlerContext;
import lombok.extern.slf4j.Slf4j;

import java.net.InetSocketAddress;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

/**
 * Created on 11/19/17
 *
 * @author Pengjinfei
 */
@ChannelHandler.Sharable
@Slf4j
public class LoginAuthRespHandler extends ChannelHandlerAdapter {

    private Map<String, Boolean> nodeCheck = new ConcurrentHashMap<>();

    private String[] whiteList = {"127.0.0.1", "192.168.0.100"};

    @Override
    public void exceptionCaught(ChannelHandlerContext ctx, Throwable cause) throws Exception {
        ctx.fireExceptionCaught(cause);
    }

    @Override
    public void channelRead(ChannelHandlerContext ctx, Object msg) throws Exception {
        NettyMessage message = (NettyMessage) msg;
        Header header = message.getHeader();
        if (header != null && header.getType() == MessageType.LOGIN_REQ.value()) {
            String nodeIp = ctx.channel().remoteAddress().toString();
            NettyMessage response;
            //重复登录,拒绝
            if (nodeCheck.containsKey(nodeIp)) {
                response = buildResponse((byte) -1);
            } else {
                InetSocketAddress socketAddress = (InetSocketAddress) ctx.channel().remoteAddress();
                String ip = socketAddress.getAddress().getHostAddress();
                boolean isOk = false;
                for (String s : whiteList) {
                    if (s.equals(ip)) {
                        isOk = true;
                        break;
                    }
                }
                response = isOk ? buildResponse((byte) 0) : buildResponse((byte) -1);
                if (isOk) {
                    nodeCheck.put(nodeIp, true);
                }
            }
            log.info("The login response is : {}, body [{}]", response, response.getBody());
            ctx.writeAndFlush(response);
        } else {
            ctx.fireChannelRead(msg);
        }
    }

    private NettyMessage buildResponse(byte result) {
        NettyMessage message = new NettyMessage();
        Header header = new Header();
        header.setType(MessageType.LOGIN_RESP.value());
        message.setHeader(header);
        message.setBody(result);
        return message;
    }


}
