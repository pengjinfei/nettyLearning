package com.pengjinfei.netty.ch1;

import lombok.val;
import org.junit.Test;

import java.util.ArrayList;

public class StudentTest {

    @Test
    public void testAccessor() {
        //fluent chain
        StudentAccessor accessor = new StudentAccessor().id("1").name("pjf");
        System.out.println(accessor);
        System.out.println(accessor.id());
    }

    @Test
    public void testDelegate() {
        StudentService studentService = new DelegatedStudentService();
        studentService.add("good");
        System.out.println(studentService.size());

        ExcludeDelegatedStudentService service = new ExcludeDelegatedStudentService();
        service.add("good");
        System.out.println(service.size());
    }

    @Test
    public void testBuilder() {
        StudentBuilderExample build = StudentBuilderExample.builder().id(1).name("hello").nickName("welcome").nickName("hi").build();
        System.out.println(build);
    }

    @Test
    public void testConstructor() {
        StudentConstructorExample student = StudentConstructorExample.of("pjf", 1).setAge(20);
        System.out.println(student);
    }

    @Test
    public void testSneakyThrows() {
        SneakyThrowsExaple.run();
    }

    @Test
    public void testWither() {
        WitherExample example = new WitherExample("pjf", "1");
        System.out.println(example);
        WitherExample withAge = example.withId("ccy");
        System.out.println(withAge);
    }

    @Test
    public void testVal() {
        val exampl = new ArrayList<String>();
        exampl.add("hello");
        System.out.println(exampl);
    }
}