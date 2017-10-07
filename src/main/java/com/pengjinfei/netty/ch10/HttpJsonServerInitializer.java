package com.pengjinfei.netty.ch10;

import com.pengjinfei.netty.ch10.pojo.Address;
import com.pengjinfei.netty.ch10.pojo.Order;
import io.netty.channel.*;
import io.netty.channel.socket.SocketChannel;
import io.netty.handler.codec.http.*;
import io.netty.util.concurrent.Future;
import io.netty.util.concurrent.GenericFutureListener;
import lombok.extern.slf4j.Slf4j;

import java.util.ArrayList;
import java.util.List;

/**
 * Created on 10/7/17
 *
 * @author Pengjinfei
 */
@Slf4j
@ChannelHandler.Sharable
public class HttpJsonServerInitializer extends ChannelInitializer<SocketChannel>{
    @Override
    protected void initChannel(SocketChannel ch) throws Exception {
        ChannelPipeline pipeline = ch.pipeline();
        pipeline.addLast(new HttpRequestDecoder());
        pipeline.addLast(new HttpObjectAggregator(65536));
        pipeline.addLast(new FastjsonHttpJsonRequestDecoder(Order.class));
        pipeline.addLast(new HttpResponseEncoder());
        pipeline.addLast(new FastjsonHttpJsonResponseEncoder());
        pipeline.addLast(new SimpleChannelInboundHandler<HttpJsonRequest>() {
            @Override
            protected void messageReceived(final ChannelHandlerContext ctx, HttpJsonRequest msg) throws Exception {
                FullHttpRequest request = msg.getRequest();
                Order order = (Order) msg.getBody();
                log.info("Recieve request from client : {}",order);
                dobusiness(order);
                ChannelFuture future = ctx.writeAndFlush(new HttpJsonResponse(null, order));
                if (!HttpHeaderUtil.isKeepAlive(request)) {
                    future.addListener(new GenericFutureListener<Future<? super Void>>() {
                        @Override
                        public void operationComplete(Future<? super Void> future) throws Exception {
                            ctx.close();
                        }
                    });
                }
            }

            private void dobusiness(Order order) {
                order.getCustomer().setFirstName("狄");
                order.getCustomer().setLastName("仁杰");
                List<String> midNames = new ArrayList<String>();
                midNames.add("李元芳");
                order.getCustomer().setMiddleNames(midNames);
                Address address = order.getBillTo();
                address.setCity("洛阳");
                address.setCountry("大唐");
                address.setState("河南道");
                address.setPostCode("123456");
                order.setBillTo(address);
                order.setShipTo(address);
            }
            @Override
            public void exceptionCaught(ChannelHandlerContext ctx, Throwable cause) throws Exception {
                log.error("Error occurred.",cause);
                ctx.close();
            }
        });
    }
}
