package com.pengjinfei.netty.ch6;

import io.netty.buffer.ByteBuf;
import io.netty.channel.ChannelHandlerContext;
import io.netty.handler.codec.MessageToMessageDecoder;

import java.util.List;

/**
 * Created on 10/7/17
 *
 * @author Pengjinfei
 */
public class ProtostuffDecoder extends MessageToMessageDecoder<ByteBuf> {

    private Class<?> originClass;

    public ProtostuffDecoder(Class<?> originClass) {
        this.originClass = originClass;
    }

    @Override
    protected void decode(ChannelHandlerContext ctx, ByteBuf msg, List<Object> out) throws Exception {
        int len = msg.readableBytes();
        byte[] array = new byte[len];
        msg.getBytes(msg.readerIndex(), array, 0, len);
        out.add(ProtostuffSerializer.deserialize(array, originClass));
    }
}
