package com.pengjinfei.netty.ch2;

import java.io.IOException;
import java.net.InetSocketAddress;
import java.nio.ByteBuffer;
import java.nio.channels.SelectionKey;
import java.nio.channels.Selector;
import java.nio.channels.SocketChannel;
import java.util.Iterator;
import java.util.Set;

/**
 * Created on 10/4/17
 *
 * @author Pengjinfei
 */
public class NIOSocketClient {

    public static void main(String[] args) throws IOException {
        InetSocketAddress addr = new InetSocketAddress(8080);
        SocketChannel socketChannel = SocketChannel.open();

        Selector selector = Selector.open();
        socketChannel.configureBlocking(false);
        boolean connect = socketChannel.connect(addr);
        String ss = "Server,how are you?";
        ByteBuffer msg = ByteBuffer.wrap(ss.getBytes("UTF-8"));
        if (connect) {
            System.out.println("Connect to server.");
            socketChannel.register(selector, SelectionKey.OP_WRITE, msg);
        } else {
            socketChannel.register(selector, SelectionKey.OP_CONNECT);
        }
        outer:
        for (; ; ) {
            selector.select();
            Set<SelectionKey> keys = selector.selectedKeys();
            Iterator<SelectionKey> iter = keys.iterator();
            while (iter.hasNext()) {
                SelectionKey key = iter.next();
                iter.remove();
                SocketChannel channel = (SocketChannel) key.channel();
                if (key.isConnectable()) {
                    if (channel.finishConnect()) {
                        socketChannel.register(selector, SelectionKey.OP_WRITE, msg);
                    }
                }
                if (key.isWritable()) {
                    key.interestOps(key.interestOps() & ~SelectionKey.OP_WRITE);
                    key.interestOps(key.interestOps() | SelectionKey.OP_READ);
                    ByteBuffer attachment = (ByteBuffer) key.attachment();
                    while (attachment.hasRemaining()) {
                        channel.write(msg);
                    }
                }
                if (key.isReadable()) {
                    ByteBuffer buf = ByteBuffer.allocate(8);
                    while (socketChannel.read(buf) > 0) {
                        buf.flip();
                        byte[] bytes = new byte[buf.limit()];
                        buf.get(bytes);
                        buf.clear();
                        System.out.println("Receive from server:"
                                + new String(bytes, "UTF-8"));
                    }
                    socketChannel.close();
                    break outer;
                }
            }
        }

    }


}