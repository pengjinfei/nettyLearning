package com.pengjinfei.netty.ch1;

import lombok.experimental.Delegate;

import java.util.ArrayList;
import java.util.Collection;

/**
 * Created on 10/21/17
 *
 * @author Pengjinfei
 */
public class DelegatedStudentService implements StudentService {

    @Delegate(types = StudentService.class)
    private final Collection<String> collection = new ArrayList<>();
}
