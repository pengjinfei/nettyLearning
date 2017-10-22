package com.pengjinfei.netty.ch1;

import lombok.Data;
import lombok.NonNull;
import lombok.RequiredArgsConstructor;
import lombok.experimental.Accessors;

/**
 * Created on 10/22/17
 *
 * @author Pengjinfei
 */
@Data
@RequiredArgsConstructor(staticName = "of")
@Accessors(chain = true)
public class StudentConstructorExample {

    @NonNull
    private String name;
    @NonNull
    private int id;
    private int age;
}
