package ru.otus.hw;

import java.io.IOException;
import java.lang.reflect.InvocationTargetException;


class HomeWork {
    public static void main(String[] args) throws IOException, InvocationTargetException, NoSuchMethodException, InstantiationException, IllegalAccessException {
        new TestLogging().calculation(1, 2);
        MyClassInterface myClass = Ioc.createMyClass();
        myClass.calculation(1, 2);
        myClass.calculation(1);
    }
}
