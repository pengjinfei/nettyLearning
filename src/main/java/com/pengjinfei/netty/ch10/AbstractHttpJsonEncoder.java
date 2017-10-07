package com.pengjinfei.netty.ch10;

import io.netty.buffer.ByteBuf;
import io.netty.channel.ChannelHandlerContext;
import io.netty.handler.codec.MessageToMessageEncoder;

import java.util.List;

/**
 * Created on 10/7/17
 *
 * @author Pengjinfei
 */
public abstract class AbstractHttpJsonEncoder<T extends BodyHoder> extends MessageToMessageEncoder<T> {

    @Override
    protected void encode(ChannelHandlerContext ctx, T msg, List<Object> out) throws Exception {
        ByteBuf byteBuf = getByteBufBody(msg.getBody());
        out.add(generate(msg,byteBuf));
    }

    abstract Object generate(T msg,ByteBuf byteBuf) throws Exception;

    abstract ByteBuf getByteBufBody(Object object);
}
