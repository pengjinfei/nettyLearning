package com.pengjinfei.netty.ch8;

import com.pengjinfei.netty.ch3.NIOServer;

/**
 * Created on 10/6/17
 *
 * @author Pengjinfei
 */
public class SubReqServer {

    public static void main(String[] args) throws Exception {
        new NIOServer(new SubReqServerInitializer()).bind();
    }
}
