package ru.otus.annotations.usage;


import ru.otus.annotations.ImmutableEmail;

public class TestDemo {
    public static void main(String[] args) {
        Class<?> clazz = EmailUse.class;
        TestRunner runner = new TestRunner();
        runner.runTests(clazz);
    }

    @ImmutableEmail
    private static class EmailUse {

    }

    private static class EmailUseChild extends EmailUse {

    }

}
