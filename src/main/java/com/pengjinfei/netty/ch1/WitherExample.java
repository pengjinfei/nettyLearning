package com.pengjinfei.netty.ch1;

import lombok.AccessLevel;
import lombok.NonNull;
import lombok.ToString;
import lombok.experimental.Wither;

/**
 * Created on 10/22/17
 *
 * @author Pengjinfei
 */
@ToString
public class WitherExample {
    @Wither
    private final String id;
    @Wither(AccessLevel.PROTECTED) @NonNull private final String name;

    public WitherExample(String name, String id) {
        if (name == null) throw new NullPointerException();
        this.name = name;
        this.id = id;
    }
}
