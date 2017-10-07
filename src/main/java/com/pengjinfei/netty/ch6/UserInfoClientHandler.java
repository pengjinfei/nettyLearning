package com.pengjinfei.netty.ch6;

import io.netty.channel.ChannelHandler;
import io.netty.channel.ChannelHandlerAdapter;
import io.netty.channel.ChannelHandlerContext;
import lombok.extern.slf4j.Slf4j;

/**
 * Created on 10/7/17
 *
 * @author Pengjinfei
 */
@ChannelHandler.Sharable
@Slf4j
public class UserInfoClientHandler extends ChannelHandlerAdapter{
    private final int sendNumber;

    public UserInfoClientHandler(int sendNumber) {
        this.sendNumber = sendNumber;
    }

    @Override
    public void channelReadComplete(ChannelHandlerContext ctx) throws Exception {
        ctx.flush();
    }

    @Override
    public void channelRead(ChannelHandlerContext ctx, Object msg) throws Exception {
        log.info("Client receive the msgpack message : [{}] ",msg);
    }

    @Override
    public void channelActive(ChannelHandlerContext ctx) throws Exception {
        UserInfo[] userInfos = getUserInfos();
        for (UserInfo userInfo : userInfos) {
            ctx.write(userInfo);
        }
        ctx.flush();
    }

    private UserInfo[] getUserInfos() {
        UserInfo[] infos = new UserInfo[sendNumber];
        for (int i = 0; i < sendNumber; i++) {
            infos[i] = new UserInfo("ABCD ---> " + i, i);
        }
        return infos;
    }
}
