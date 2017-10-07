package com.pengjinfei.netty.ch10;

import io.netty.handler.codec.http.FullHttpRequest;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * Created on 10/7/17
 *
 * @author Pengjinfei
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
public class HttpJsonRequest implements BodyHoder {
    private FullHttpRequest request;
    private Object body;
}
