package com.pengjinfei.netty.ch10;

import com.alibaba.fastjson.JSON;
import io.netty.buffer.ByteBuf;
import io.netty.buffer.Unpooled;
import io.netty.channel.ChannelHandler;
import io.netty.handler.codec.http.*;
import io.netty.util.CharsetUtil;
import org.springframework.http.MediaType;

import java.net.InetAddress;

import static io.netty.handler.codec.http.HttpHeaderNames.*;
import static io.netty.handler.codec.http.HttpHeaderValues.*;

/**
 * Created on 10/7/17
 *
 * @author Pengjinfei
 */
@ChannelHandler.Sharable
public class FastjsonHttpJsonRequestEncoder extends AbstractHttpJsonEncoder<HttpJsonRequest>{

    @Override
    Object generate(HttpJsonRequest msg,ByteBuf body) throws Exception{
        FullHttpRequest request = msg.getRequest();
        HttpHeaders headers;
        if (request == null) {
            request = new DefaultFullHttpRequest(HttpVersion.HTTP_1_1,
                    HttpMethod.GET, "/do", body);
            headers = request.headers();
            headers.set(HOST, InetAddress.getLocalHost()
                    .getHostAddress());
            headers.set(CONNECTION, CLOSE);
            headers.set(ACCEPT_ENCODING,
                    GZIP.toString() + ','
                            + DEFLATE.toString());
            //headers.set(ACCEPT_LANGUAGE, "zh");
            headers.set(USER_AGENT,
                    "Netty xml Http Client side");
            headers.set(ACCEPT, MediaType.APPLICATION_JSON_UTF8_VALUE);
            headers.setInt(CONTENT_LENGTH, body.readableBytes());
        } else {
            headers=request.headers();
        }
        headers.setInt(CONTENT_LENGTH, body.readableBytes());
        return request;
    }

    @Override
    ByteBuf getByteBufBody(Object object) {
        String s = JSON.toJSONString(object);
        return Unpooled.copiedBuffer(s, CharsetUtil.UTF_8);
    }
}
