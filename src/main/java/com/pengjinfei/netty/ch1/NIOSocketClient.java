package com.pengjinfei.netty.ch1;

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
        socketChannel.register(selector, SelectionKey.OP_READ);

        // 连接到server
        socketChannel.connect(addr);
        while (!socketChannel.finishConnect()) ;
        System.out.println("Connect to server.");
        String ss = "Server,how are you?";
        ByteBuffer buffer = ByteBuffer.wrap(ss.getBytes("UTF-8"));
        while (buffer.hasRemaining()) {
            socketChannel.write(buffer);
        }
        selector.select();
        Set<SelectionKey> keys = selector.selectedKeys();
        Iterator<SelectionKey> iter = keys.iterator();
        while (iter.hasNext()) {
            SelectionKey key = iter.next();
            iter.remove();
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
            }
        }
    }


}