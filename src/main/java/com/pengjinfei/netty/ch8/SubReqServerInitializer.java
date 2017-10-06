package com.pengjinfei.netty.ch8;

import io.netty.channel.*;
import io.netty.channel.socket.SocketChannel;
import io.netty.handler.codec.protobuf.ProtobufDecoder;
import io.netty.handler.codec.protobuf.ProtobufEncoder;
import io.netty.handler.codec.protobuf.ProtobufVarint32FrameDecoder;
import io.netty.handler.codec.protobuf.ProtobufVarint32LengthFieldPrepender;
import lombok.extern.slf4j.Slf4j;

/**
 * Created on 10/6/17
 *
 * @author Pengjinfei
 */
@ChannelHandler.Sharable
@Slf4j
public class SubReqServerInitializer extends ChannelInitializer<SocketChannel> {
    @Override
    protected void initChannel(SocketChannel ch) throws Exception {
        ChannelPipeline pipeline = ch.pipeline();
        pipeline.addLast(new ProtobufVarint32FrameDecoder());
        pipeline.addLast(new ProtobufDecoder(SubscribeReqProto.SubscribeReq.getDefaultInstance()));
        pipeline.addLast(new ProtobufVarint32LengthFieldPrepender());
        pipeline.addLast(new ProtobufEncoder());
        pipeline.addLast(new ChannelHandlerAdapter() {
            @Override
            public void exceptionCaught(ChannelHandlerContext ctx, Throwable cause) throws Exception {
                log.error("Error occurred.",cause);
                ctx.close();
            }

            @Override
            public void channelRead(ChannelHandlerContext ctx, Object msg) throws Exception {
                SubscribeReqProto.SubscribeReq req = (SubscribeReqProto.SubscribeReq) msg;
                if ("pjf".equalsIgnoreCase(req.getUserName())) {
                    log.info("Service accept client subscribe req : [{}]",req);
                    ctx.writeAndFlush(resp(req.getSubReqID()));
                }
            }

            private SubscribeRespProto.SubscribeResp resp(int reqID) {
                SubscribeRespProto.SubscribeResp.Builder builder = SubscribeRespProto.SubscribeResp.newBuilder();
                builder.setSubRespID(reqID);
                builder.setRespCode(0);
                builder.setDesc("Netty book order succeed, 3 days later, sent to the designated address");
                return builder.build();
            }
        });
    }
}
