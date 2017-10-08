package com.pengjinfei.netty.ch11;

import com.pengjinfei.netty.ch3.NIOServer;

/**
 * Created on 10/8/17
 *
 * @author Pengjinfei
 */
public class WebSocketServer {

    public static void main(String[] args) throws Exception {
        new NIOServer(new WebSocketChannelInitializer()).bind();
    }
}
