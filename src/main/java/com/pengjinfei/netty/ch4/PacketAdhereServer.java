package com.pengjinfei.netty.ch4;

import com.pengjinfei.netty.ch3.TimeServer;

/**
 * Created on 10/6/17
 *
 * @author Pengjinfei
 */
public class PacketAdhereServer {

    public static void main(String[] args) throws Exception {
        new TimeServer(new PacketAdhereServerHandler()).bind();
    }
}
