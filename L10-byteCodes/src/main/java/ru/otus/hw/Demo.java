package ru.otus.hw;

import java.io.IOException;
import java.lang.reflect.InvocationTargetException;

class Demo {
    public static void main(String[] args) throws IOException, InvocationTargetException, NoSuchMethodException, InstantiationException, IllegalAccessException {
        new TestLogging().calculation(6);
    }
}
