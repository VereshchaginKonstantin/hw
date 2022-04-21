package ru.otus.annotations.usage;

import ru.otus.annotations.AfterTest;
import ru.otus.annotations.BeforeTest;
import ru.otus.annotations.Test;

public class TestAll {

    @BeforeTest
    public void befor() {

    }

    @Test
    public void test1() {

    }

    @Test
    public void test2() throws Exception {
        throw new Exception();
    }

    @AfterTest
    public void after() {

    }
}
