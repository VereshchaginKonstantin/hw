package ru.otus.annotations.usage;

import ru.otus.annotations.AfterTest;
import ru.otus.annotations.BeforeTest;
import ru.otus.annotations.Test;

public class TestAll {

    @BeforeTest
    public void befor() throws Exception {
        System.out.println("befor");
    }

    @Test
    public void test1() {
        System.out.println("test1");
    }

    @Test
    public void test2() throws Exception {
        System.out.println("test2");
        throw new Exception();
    }

    @Test
    public void test3() {
        System.out.println("test3");
    }

    @AfterTest
    public void after() throws Exception {
        System.out.println("after");
    }
}
