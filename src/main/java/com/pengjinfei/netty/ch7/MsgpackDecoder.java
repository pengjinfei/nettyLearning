package com.pengjinfei.netty.ch7;

import io.netty.buffer.ByteBuf;
import io.netty.channel.ChannelHandlerContext;
import io.netty.handler.codec.MessageToMessageDecoder;
import org.msgpack.MessagePack;

import java.util.List;

/**
 * Created on 10/6/17
 *
 * @author Pengjinfei
 */
public class MsgpackDecoder extends MessageToMessageDecoder<ByteBuf> {
    @Override
    protected void decode(ChannelHandlerContext channelHandlerContext, ByteBuf byteBuf, List<Object> list) throws Exception {
        int len = byteBuf.readableBytes();
        byte[] array = new byte[len];
        byteBuf.getBytes(byteBuf.readerIndex(), array, 0, len);
        MessagePack pack = new MessagePack();
        list.add(pack.read(array));
    }
}
