package com.pengjinfei.netty.ch6;

import com.pengjinfei.netty.ch3.NIOServer;

/**
 * Created on 10/7/17
 *
 * @author Pengjinfei
 */
public class ProtostuffServer {

    public static void main(String[] args) throws Exception {
        new NIOServer(new ProtostuffServerInitializer()).bind();
    }
}
