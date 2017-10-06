package com.pengjinfei.netty.ch5;

import com.pengjinfei.netty.ch3.TimeServer;

/**
 * Created on 10/6/17
 *
 * @author Pengjinfei
 */
public class FixedServer {

    public static void main(String[] args) throws Exception {
        new TimeServer(new FixedServerInitializer()).bind();
    }
}
