package com.pengjinfei.netty.ch10;

import io.netty.handler.codec.http.FullHttpResponse;
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
public class HttpJsonResponse implements BodyHoder{
    private FullHttpResponse response;
    private Object body;
}
