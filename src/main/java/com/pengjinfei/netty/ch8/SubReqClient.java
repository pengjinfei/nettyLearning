package com.pengjinfei.netty.ch8;

import com.pengjinfei.netty.ch3.NIOClient;

/**
 * Created on 10/6/17
 *
 * @author Pengjinfei
 */
public class SubReqClient {

    public static void main(String[] args) throws Exception {
        new NIOClient(new SubReqClientInitializer()).connect();
    }
}
