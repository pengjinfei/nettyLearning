package com.pengjinfei.netty.ch12;

import io.netty.buffer.ByteBuf;
import org.nustaq.serialization.FSTConfiguration;

/**
 * Created on 11/19/17
 *
 * @author Pengjinfei
 */
public class FstSerializer {

    private static final byte[] LENGTH_PLACEHOLDER = new byte[4];

    private static FSTConfiguration conf = FSTConfiguration.createDefaultConfiguration();

    public static void encode(Object msg, ByteBuf out) throws Exception {
        int writerIndex = out.writerIndex();
        out.writeBytes(LENGTH_PLACEHOLDER);
        byte[] bytes = conf.asByteArray(msg);
        out.writeBytes(bytes);
        out.setInt(writerIndex, out.writerIndex() - writerIndex - 4);
    }

    public static Object decode(ByteBuf in) throws Exception {
        int size = in.readInt();
        byte[] bytes = new byte[size];
        in.readBytes(bytes, 0, size);
        return conf.asObject(bytes);
    }
}
