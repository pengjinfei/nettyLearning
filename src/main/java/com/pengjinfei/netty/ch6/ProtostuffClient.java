package com.pengjinfei.netty.ch6;

import com.pengjinfei.netty.ch3.NIOClient;

/**
 * Created on 10/7/17
 *
 * @author Pengjinfei
 */
public class ProtostuffClient {

    public static void main(String[] args) throws Exception {
        new NIOClient(new ProtostuffClientInitializer()).connect();
    }
}
