package com.pengjinfei.netty.ch6;

import io.netty.channel.ChannelHandler;
import io.netty.channel.ChannelHandlerAdapter;
import io.netty.channel.ChannelHandlerContext;
import lombok.extern.slf4j.Slf4j;

/**
 * Created on 10/7/17
 *
 * @author Pengjinfei
 */
@Slf4j
@ChannelHandler.Sharable
public class UserInfoServerHandler extends ChannelHandlerAdapter{
    @Override
    public void channelRead(ChannelHandlerContext ctx, Object msg) throws Exception {
        log.info("Server receive the msgpack message : [{}]", msg);
        ctx.writeAndFlush(msg);
    }

    @Override
    public void exceptionCaught(ChannelHandlerContext ctx, Throwable cause) throws Exception {
        log.error("Error occurred", cause);
        ctx.close();
    }
}
