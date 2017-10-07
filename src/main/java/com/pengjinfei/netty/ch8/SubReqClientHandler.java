package com.pengjinfei.netty.ch8;

import io.netty.channel.ChannelHandler;
import io.netty.channel.ChannelHandlerAdapter;
import io.netty.channel.ChannelHandlerContext;
import lombok.extern.slf4j.Slf4j;

import java.util.ArrayList;
import java.util.List;

/**
 * Created on 10/7/17
 *
 * @author Pengjinfei
 */
@Slf4j
@ChannelHandler.Sharable
public class SubReqClientHandler extends ChannelHandlerAdapter {

    @Override
    public void channelRead(ChannelHandlerContext ctx, Object msg) throws Exception {
        log.info("Receive server response : [{}]", msg);
    }

    @Override
    public void channelActive(ChannelHandlerContext ctx) throws Exception {
        for (int i = 0; i < 10; i++) {
            ctx.write(subscribeReq(i));
        }
        ctx.flush();
    }

    private SubscribeReqProto.SubscribeReq subscribeReq(int i) {
        SubscribeReqProto.SubscribeReq.Builder builder = SubscribeReqProto.SubscribeReq.newBuilder();
        builder.setSubReqID(i);
        builder.setUserName("pjf");
        builder.setProductName("Netty Book For Protobuff");
        List<String> address = new ArrayList<>();
        address.add("Beijing");
        address.add("Shenzhen");
        builder.addAllAddress(address);
        return builder.build();
    }

    @Override
    public void exceptionCaught(ChannelHandlerContext ctx, Throwable cause) throws Exception {
        log.error("Error occurred.",cause);
        ctx.close();
    }

    @Override
    public void channelReadComplete(ChannelHandlerContext ctx) throws Exception {
        ctx.flush();
    }
}
