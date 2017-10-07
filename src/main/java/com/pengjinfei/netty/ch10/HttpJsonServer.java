package com.pengjinfei.netty.ch10;

import com.pengjinfei.netty.ch3.NIOServer;

/**
 * Created on 10/7/17
 *
 * @author Pengjinfei
 */
public class HttpJsonServer {

    public static void main(String[] args) throws Exception {
        new NIOServer(new HttpJsonServerInitializer()).bind();
    }
}
