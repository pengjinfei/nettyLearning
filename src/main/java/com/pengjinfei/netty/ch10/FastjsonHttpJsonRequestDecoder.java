package com.pengjinfei.netty.ch10;

import com.alibaba.fastjson.JSON;
import io.netty.buffer.ByteBuf;
import io.netty.channel.ChannelHandler;
import io.netty.handler.codec.http.FullHttpRequest;
import io.netty.util.CharsetUtil;

/**
 * Created on 10/7/17
 *
 * @author Pengjinfei
 */
@ChannelHandler.Sharable
public class FastjsonHttpJsonRequestDecoder extends AbstractHttpJsonDecoder<FullHttpRequest> {

    private Class<?> aClass;

    public FastjsonHttpJsonRequestDecoder(Class<?> aClass) {
        this.aClass = aClass;
    }

    @Override
    Object generate(FullHttpRequest msg, Object object) {
        return new HttpJsonRequest(msg,object);
    }

    @Override
    Object getBoby(ByteBuf byteBuf) {
        return JSON.parseObject(byteBuf.toString(CharsetUtil.UTF_8), aClass);
    }
}
