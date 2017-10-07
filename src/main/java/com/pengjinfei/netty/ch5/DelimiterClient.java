package com.pengjinfei.netty.ch5;

import com.pengjinfei.netty.ch3.NIOClient;

/**
 * Created on 10/6/17
 *
 * @author Pengjinfei
 */
public class DelimiterClient {

    public static void main(String[] args) throws Exception {
        new NIOClient(new DelimiterClientInitializer()).connect();
    }
}
