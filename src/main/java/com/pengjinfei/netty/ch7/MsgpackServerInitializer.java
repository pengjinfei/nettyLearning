package com.pengjinfei.netty.ch7;

import com.pengjinfei.netty.ch6.UserInfoServerHandler;
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
@Slf4j
@ChannelHandler.Sharable
public class MsgpackServerInitializer extends ChannelInitializer<SocketChannel> {
    @Override
    protected void initChannel(SocketChannel ch) throws Exception {
        ChannelPipeline pipeline = ch.pipeline();
        pipeline.addLast(new LengthFieldBasedFrameDecoder(65535, 0, 2, 0, 2));
        pipeline.addLast(new MsgpackDecoder());
        pipeline.addLast(new LengthFieldPrepender(2));
        pipeline.addLast(new MsgpackEncoder());
        pipeline.addLast(new UserInfoServerHandler());
    }
}
