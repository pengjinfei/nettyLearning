package com.pengjinfei.netty.ch4;

import com.pengjinfei.netty.ch3.TimeClient;

/**
 * Created on 10/6/17
 *
 * @author Pengjinfei
 */
public class PacketAdhereClient {

    public static void main(String[] args) throws Exception {
        new TimeClient(new PacketAdhereClientHandler()).connect();
    }
}
