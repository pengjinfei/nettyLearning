package com.pengjinfei.netty.ch7;

import io.netty.channel.*;
import io.netty.channel.socket.SocketChannel;
import io.netty.handler.codec.LengthFieldBasedFrameDecoder;
import io.netty.handler.codec.LengthFieldPrepender;
import lombok.extern.slf4j.Slf4j;

/**
 * Created on 10/6/17
 *
 * @author Pengjinfei
 */
@ChannelHandler.Sharable
@Slf4j
public class MsgpackClientInitializer extends ChannelInitializer<SocketChannel> {
    @Override
    protected void initChannel(SocketChannel socketChannel) throws Exception {
        ChannelPipeline pipeline = socketChannel.pipeline();
        pipeline.addLast(new LengthFieldBasedFrameDecoder(65535, 0, 2, 0, 2));
        pipeline.addLast(new MsgpackDecoder());
        pipeline.addLast(new LengthFieldPrepender(2));
        pipeline.addLast(new MsgpackEncoder());
        pipeline.addLast(new ChannelHandlerAdapter() {
            private final int sendNumber = 100;

            @Override
            public void channelReadComplete(ChannelHandlerContext ctx) throws Exception {
                ctx.flush();
            }

            @Override
            public void channelRead(ChannelHandlerContext ctx, Object msg) throws Exception {
                log.info("Client receive the msgpack message : [{}] ",msg);
            }

            @Override
            public void channelActive(ChannelHandlerContext ctx) throws Exception {
                UserInfo[] userInfos = getUserInfos();
                for (UserInfo userInfo : userInfos) {
                    ctx.write(userInfo);
                }
                ctx.flush();
            }

            private UserInfo[] getUserInfos() {
                UserInfo[] infos = new UserInfo[sendNumber];
                for (int i = 0; i < sendNumber; i++) {
                    infos[i] = new UserInfo("ABCD ---> " + i, i);
                }
                return infos;
            }
        });
    }
}
