package com.pengjinfei.netty.ch6;

import io.netty.channel.*;
import io.netty.channel.socket.SocketChannel;
import io.netty.handler.codec.LengthFieldBasedFrameDecoder;
import io.netty.handler.codec.LengthFieldPrepender;
import lombok.extern.slf4j.Slf4j;

/**
 * Created on 10/7/17
 *
 * @author Pengjinfei
 */
@ChannelHandler.Sharable
@Slf4j
public class ProtostuffClientInitializer extends ChannelInitializer<SocketChannel> {
    @Override
    protected void initChannel(SocketChannel ch) throws Exception {
        ChannelPipeline pipeline = ch.pipeline();
        pipeline.addLast(new LengthFieldBasedFrameDecoder(65535, 0, 2, 0, 2));
        pipeline.addLast(new ProtostuffDecoder(UserInfo.class));
        pipeline.addLast(new LengthFieldPrepender(2));
        pipeline.addLast(new ProtostuffEncoder());
        pipeline.addLast(new UserInfoClientHandler(100));
    }
}
