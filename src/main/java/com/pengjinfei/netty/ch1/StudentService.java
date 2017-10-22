package com.pengjinfei.netty.ch1;

/**
 * Created on 10/21/17
 *
 * @author Pengjinfei
 */
public interface StudentService {

    boolean add(String name);

    boolean remove(Object student);

    int size();
}
