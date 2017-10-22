package com.pengjinfei.netty.ch1;

import lombok.SneakyThrows;
import lombok.experimental.UtilityClass;

import java.io.UnsupportedEncodingException;

/**
 * Created on 10/22/17
 *
 * @author Pengjinfei
 */
@UtilityClass
public class SneakyThrowsExaple {

    @SneakyThrows(UnsupportedEncodingException.class)
    public String utf8ToString(byte[] bytes) {
        return new String(bytes, "UTF-8");
    }

    @SneakyThrows
    public void run() {
        throw new Throwable("test");
    }
}
