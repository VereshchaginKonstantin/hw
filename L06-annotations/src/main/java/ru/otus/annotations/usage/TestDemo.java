package ru.otus.annotations.usage;


import ru.otus.annotations.AfterTest;
import ru.otus.annotations.BeforeTest;
import ru.otus.annotations.Test;

public class TestDemo {
    public static void main(String[] args) {
        Class<?> clazz = TestAll.class;
        TestRunner runner = new TestRunner();
        runner.runTests(clazz);
    }
}
