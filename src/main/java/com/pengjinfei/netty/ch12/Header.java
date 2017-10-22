package com.pengjinfei.netty.ch12;

import lombok.Data;

import java.util.HashMap;
import java.util.Map;

/**
 * Created on 10/8/17
 *
 * @author Pengjinfei
 */
@Data
public class Header {
    private int crcCode = 0xabef0101;
    private int length;
    private long sessionID;
    private byte type;
    private byte priority;
    private Map<String, Object> attachment = new HashMap<>();
}
