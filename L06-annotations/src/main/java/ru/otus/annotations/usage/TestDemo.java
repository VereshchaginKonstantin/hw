package ru.otus.annotations.usage;


public class TestDemo {
    public static void main(String[] args) {
        Class<?> clazz = TestAll.class;
        TestRunner.runTests(clazz);
    }
}
