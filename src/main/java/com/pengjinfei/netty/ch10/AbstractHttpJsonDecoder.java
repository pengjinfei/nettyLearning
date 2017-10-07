package com.pengjinfei.netty.ch10;

import io.netty.buffer.ByteBuf;
import io.netty.buffer.ByteBufHolder;
import io.netty.channel.ChannelHandlerContext;
import io.netty.handler.codec.MessageToMessageDecoder;

import java.util.List;

/**
 * Created on 10/7/17
 *
 * @author Pengjinfei
 */
public abstract class AbstractHttpJsonDecoder<T extends ByteBufHolder> extends MessageToMessageDecoder<T> {
    @Override
    protected void decode(ChannelHandlerContext ctx, T msg, List<Object> out) throws Exception {
        Object content = getBoby(msg.content());
        out.add(generate(msg, content));
    }

    abstract Object generate(T msg, Object object);


    abstract Object getBoby(ByteBuf byteBuf);
}
