package com.pengjinfei.netty.ch9;

import com.pengjinfei.netty.ch8.SubReqClientHandler;
import io.netty.channel.ChannelHandler;
import io.netty.channel.ChannelInitializer;
import io.netty.channel.ChannelPipeline;
import io.netty.channel.socket.SocketChannel;
import lombok.extern.slf4j.Slf4j;

/**
 * Created on 10/7/17
 *
 * @author Pengjinfei
 */
@Slf4j
@ChannelHandler.Sharable
public class MarshallingClientInitializer extends ChannelInitializer<SocketChannel> {
    @Override
    protected void initChannel(SocketChannel ch) throws Exception {
        ChannelPipeline pipeline = ch.pipeline();
        pipeline.addLast(MarshallingCodeCFactory.buildingMarshallingDecoder());
        pipeline.addLast(MarshallingCodeCFactory.buildingMarshallingEncoder());
        pipeline.addLast(new SubReqClientHandler());
    }
}
