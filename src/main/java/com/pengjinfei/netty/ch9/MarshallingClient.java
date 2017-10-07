package com.pengjinfei.netty.ch9;

import com.pengjinfei.netty.ch3.TimeClient;

/**
 * Created on 10/7/17
 *
 * @author Pengjinfei
 */
public class MarshallingClient {

    public static void main(String[] args) throws Exception {
        new TimeClient(new MarshallingClientInitializer()).connect();
    }
}
