package com.pengjinfei.netty.ch6;

import com.pengjinfei.netty.ch3.TimeClient;

/**
 * Created on 10/7/17
 *
 * @author Pengjinfei
 */
public class ProtostuffClient {

    public static void main(String[] args) throws Exception {
        new TimeClient(new ProtostuffClientInitializer()).connect();
    }
}
