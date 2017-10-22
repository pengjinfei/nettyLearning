package com.pengjinfei.netty.ch1;

import lombok.Builder;
import lombok.Data;
import lombok.Singular;

import java.util.Set;

/**
 * Created on 10/22/17
 *
 * @author Pengjinfei
 */
@Builder
@Data
public class StudentBuilderExample {

    private String name;
    private int id;

    @Singular
    private Set<String> nickNames;

}
