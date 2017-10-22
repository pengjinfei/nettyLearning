package com.pengjinfei.netty.ch1;

import lombok.experimental.Delegate;

import java.util.ArrayList;
import java.util.Collection;

/**
 * Created on 10/21/17
 *
 * @author Pengjinfei
 */
public class ExcludeDelegatedStudentService implements StudentService{

    @Delegate(excludes = StudentService.class)
    private final Collection<String> collection = new ArrayList<>();


    @Override
    public boolean add(String name) {
        return false;
    }

    @Override
    public boolean remove(Object student) {
        return false;
    }

    @Override
    public int size() {
        return 0;
    }
}
