package com.pengjinfei.netty.ch12;

import lombok.Data;

/**
 * Created on 10/8/17
 *
 * @author Pengjinfei
 */
@Data
public class NettyMessage {
    private Header header;
    private Object body;
}
