package com.pengjinfei.netty.ch10;

import com.alibaba.fastjson.JSON;
import io.netty.buffer.ByteBuf;
import io.netty.buffer.Unpooled;
import io.netty.channel.ChannelHandler;
import io.netty.handler.codec.http.*;
import io.netty.util.CharsetUtil;
import org.springframework.http.MediaType;

/**
 * Created on 10/7/17
 *
 * @author Pengjinfei
 */
@ChannelHandler.Sharable
public class FastjsonHttpJsonResponseEncoder extends AbstractHttpJsonEncoder<HttpJsonResponse> {
    @Override
    Object generate(HttpJsonResponse msg, ByteBuf byteBuf) throws Exception {
        FullHttpResponse response = msg.getResponse();
        if (response == null) {
            response = new DefaultFullHttpResponse(HttpVersion.HTTP_1_1, HttpResponseStatus.OK, byteBuf);
        } else {
            response = new DefaultFullHttpResponse(msg.getResponse().protocolVersion(), msg.getResponse().status(), byteBuf);
        }
        response.headers().set(HttpHeaderNames.CONTENT_TYPE, MediaType.APPLICATION_JSON_UTF8_VALUE);
        response.headers().setInt(HttpHeaderNames.CONTENT_LENGTH, byteBuf.readableBytes());
        return response;
    }

    @Override
    ByteBuf getByteBufBody(Object object) {
        String s = JSON.toJSONString(object);
        return Unpooled.copiedBuffer(s, CharsetUtil.UTF_8);
    }
}
