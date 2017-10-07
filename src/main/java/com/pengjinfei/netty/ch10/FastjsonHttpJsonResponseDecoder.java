package com.pengjinfei.netty.ch10;

import com.alibaba.fastjson.JSON;
import io.netty.buffer.ByteBuf;
import io.netty.channel.ChannelHandler;
import io.netty.handler.codec.http.FullHttpResponse;
import io.netty.util.CharsetUtil;

/**
 * Created on 10/7/17
 *
 * @author Pengjinfei
 */
@ChannelHandler.Sharable
public class FastjsonHttpJsonResponseDecoder extends AbstractHttpJsonDecoder<FullHttpResponse> {

    private Class<?> aClass;

    public FastjsonHttpJsonResponseDecoder(Class<?> aClass) {
        this.aClass = aClass;
    }

    @Override
    Object generate(FullHttpResponse msg, Object object) {
        return new HttpJsonResponse(msg,object);
    }

    @Override
    Object getBoby(ByteBuf byteBuf) {
        return JSON.parseObject(byteBuf.toString(CharsetUtil.UTF_8), aClass);
    }
}
