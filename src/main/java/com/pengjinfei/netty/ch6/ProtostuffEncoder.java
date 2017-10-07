package com.pengjinfei.netty.ch6;

import io.netty.buffer.ByteBuf;
import io.netty.channel.ChannelHandlerContext;
import io.netty.handler.codec.MessageToByteEncoder;

/**
 * Created on 10/7/17
 *
 * @author Pengjinfei
 */
public class ProtostuffEncoder extends MessageToByteEncoder<Object> {
    @Override
    protected void encode(ChannelHandlerContext ctx, Object msg, ByteBuf out) throws Exception {
        out.writeBytes(ProtostuffSerializer.serialize(msg));
    }
}
