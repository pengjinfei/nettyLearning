package com.pengjinfei.netty.ch6;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.msgpack.annotation.Message;

import java.io.Serializable;

/**
 * Created on 10/6/17
 *
 * @author Pengjinfei
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
@Message
public class UserInfo implements Serializable {

    private String userName;
    private int userID;
}
