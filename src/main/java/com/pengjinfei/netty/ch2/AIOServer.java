package com.pengjinfei.netty.ch2;

import java.io.IOException;
import java.net.InetSocketAddress;
import java.nio.ByteBuffer;
import java.nio.channels.AsynchronousServerSocketChannel;
import java.nio.channels.AsynchronousSocketChannel;
import java.nio.channels.CompletionHandler;

/**
 * Created on 10/5/17
 *
 * @author Pengjinfei
 */
public class AIOServer {

    public static void main(String[] args) throws Exception {
        final AsynchronousServerSocketChannel channel = AsynchronousServerSocketChannel.open();
        channel.bind(new InetSocketAddress(8080));
            channel.accept(channel, new CompletionHandler<AsynchronousSocketChannel, AsynchronousServerSocketChannel>() {
                @Override
                public void completed(final AsynchronousSocketChannel socketChannel, AsynchronousServerSocketChannel serverChannel) {
                    serverChannel.accept(channel,this);
                    ByteBuffer buffer = ByteBuffer.allocate(1024);
                    socketChannel.read(buffer, buffer, new CompletionHandler<Integer, ByteBuffer>() {
                        @Override
                        public void completed(Integer result, ByteBuffer attachment) {
                            attachment.flip();
                            byte[] body = new byte[attachment.remaining()];
                            attachment.get(body);
                            System.out.println("Recieved msg from client: "+new String(body));
                            ByteBuffer msg = ByteBuffer.wrap("Hello!NIO!".getBytes());
                            socketChannel.write(msg, msg, new CompletionHandler<Integer, ByteBuffer>() {
                                @Override
                                public void completed(Integer result, ByteBuffer buffer) {
                                    if (buffer.hasRemaining()) {
                                        socketChannel.write(buffer, buffer, this);
                                    }
                                }

                                @Override
                                public void failed(Throwable exc, ByteBuffer attachment) {
                                    try {
                                        socketChannel.close();
                                    } catch (IOException ignored) {
                                    }
                                }
                            });
                        }

                        @Override
                        public void failed(Throwable exc, ByteBuffer attachment) {
                            try {
                                socketChannel.close();
                            } catch (IOException ignored) {
                            }
                        }
                    });
                }

                @Override
                public void failed(Throwable exc, AsynchronousServerSocketChannel attachment) {

                }
            });
            Thread.currentThread().join();
        }

}
