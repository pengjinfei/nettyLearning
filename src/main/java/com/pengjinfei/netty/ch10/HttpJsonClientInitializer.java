package com.pengjinfei.netty.ch10;

import com.pengjinfei.netty.ch10.pojo.Order;
import com.pengjinfei.netty.ch10.pojo.OrderFactory;
import io.netty.channel.*;
import io.netty.channel.socket.SocketChannel;
import io.netty.handler.codec.http.HttpObjectAggregator;
import io.netty.handler.codec.http.HttpRequestEncoder;
import io.netty.handler.codec.http.HttpResponseDecoder;
import lombok.extern.slf4j.Slf4j;

/**
 * Created on 10/7/17
 *
 * @author Pengjinfei
 */
@ChannelHandler.Sharable
@Slf4j
public class HttpJsonClientInitializer extends ChannelInitializer<SocketChannel> {
    @Override
    protected void initChannel(SocketChannel ch) throws Exception {
        ChannelPipeline pipeline = ch.pipeline();
        pipeline.addLast(new HttpResponseDecoder());
        pipeline.addLast(new HttpObjectAggregator(65536));
        pipeline.addLast(new FastjsonHttpJsonResponseDecoder(Order.class));
        pipeline.addLast(new HttpRequestEncoder());
        pipeline.addLast(new FastjsonHttpJsonRequestEncoder());
        pipeline.addLast(new SimpleChannelInboundHandler<HttpJsonResponse>(){
            @Override
            protected void messageReceived(ChannelHandlerContext ctx, HttpJsonResponse msg) throws Exception {
                log.info("Recieve response headers : {}",msg.getResponse().headers().names());
                log.info("Recieve response body : {}",msg.getBody());
            }

            @Override
            public void channelActive(ChannelHandlerContext ctx) throws Exception {
                HttpJsonRequest request = new HttpJsonRequest(null, OrderFactory.create(123));
                ctx.writeAndFlush(request);
            }

            @Override
            public void exceptionCaught(ChannelHandlerContext ctx, Throwable cause) throws Exception {
                log.error("Error occurred.",cause);
                ctx.close();
            }

        });
    }
}
