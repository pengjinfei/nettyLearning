package com.pengjinfei.netty.ch12;

import lombok.Data;

import java.io.Serializable;

/**
 * Created on 10/8/17
 *
 * @author Pengjinfei
 */
@Data
public class NettyMessage implements Serializable{
    private Header header;
    private Object body;
}
