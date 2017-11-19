package com.pengjinfei.netty.ch12;

import io.netty.bootstrap.ServerBootstrap;
import io.netty.channel.ChannelFuture;
import io.netty.channel.ChannelInitializer;
import io.netty.channel.ChannelOption;
import io.netty.channel.EventLoopGroup;
import io.netty.channel.nio.NioEventLoopGroup;
import io.netty.channel.socket.SocketChannel;
import io.netty.channel.socket.nio.NioServerSocketChannel;
import io.netty.handler.logging.LogLevel;
import io.netty.handler.logging.LoggingHandler;
import io.netty.handler.timeout.ReadTimeoutHandler;

/**
 * Created on 11/19/17
 *
 * @author Pengjinfei
 */
public class NettyServer {

    public void bind() throws Exception {
        EventLoopGroup b = new NioEventLoopGroup();
        EventLoopGroup w = new NioEventLoopGroup();
        ServerBootstrap bootstrap = new ServerBootstrap();
        bootstrap.group(b,w)
                .channel(NioServerSocketChannel.class)
                .option(ChannelOption.SO_BACKLOG, 100)
                .handler(new LoggingHandler(LogLevel.INFO))
                .childHandler(new ChannelInitializer<SocketChannel>() {
                    @Override
                    protected void initChannel(SocketChannel ch) throws Exception {
                        ch.pipeline().addLast(new NettyMessageDecoder(1024 * 1024, 0, 4))
                                .addLast(new NettyMessageEncoder())
                                .addLast(new ReadTimeoutHandler(50))
                                .addLast(new LoginAuthRespHandler())
                                .addLast(new HeartBeatRespHandler());
                    }
                });
        ChannelFuture future = bootstrap.bind(8080).sync();
        future.channel().closeFuture().sync();
    }

    public static void main(String[] args) throws Exception {
        new NettyServer().bind();
    }
}
