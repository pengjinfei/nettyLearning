package com.pengjinfei.netty.ch9;

import com.pengjinfei.netty.ch3.TimeServer;

/**
 * Created on 10/7/17
 *
 * @author Pengjinfei
 */
public class MarshallingServer {

    public static void main(String[] args) throws Exception {
        new TimeServer(new MarshallingServerInitializer()).bind();
    }
}
