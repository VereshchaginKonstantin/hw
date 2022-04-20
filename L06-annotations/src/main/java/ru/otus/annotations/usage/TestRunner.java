package ru.otus.annotations.usage;

import java.lang.reflect.Constructor;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Optional;

import ru.otus.annotations.AfterTest;
import ru.otus.annotations.BeforeTest;
import ru.otus.annotations.Test;

public class TestRunner {

    public void runTests(Class<?> clazz) {
        Optional<Method> before = Arrays.stream(clazz.getDeclaredMethods())
                .filter(x -> x.isAnnotationPresent(BeforeTest.class))
                .findFirst();
        Optional<Method> after= Arrays.stream(clazz.getDeclaredMethods())
                .filter(x -> x.isAnnotationPresent(AfterTest.class))
                .findFirst();
        List<Result> results = new ArrayList<>();
        for (Method declaredMethod : clazz.getDeclaredMethods()) {
            if (declaredMethod.isAnnotationPresent(Test.class)) {
                results.add(
                        runTest(clazz, before, after, declaredMethod)
                );
            }
        }
        print(results);
    }

    private void print(List<Result> results) {
    }

    private Result runTest(
            Class<?> clazz,
            Optional<Method> before,
            Optional<Method> after,
                         Method test) {
        Constructor<?> constructor;
        Object instance;
        try {
            constructor = clazz.getConstructor();
            instance = constructor.newInstance();
        } catch (Exception e) {
            return Result.EXCEPTION;
        }
        try {
            if (before.isPresent()) {
                before.get().invoke(instance);
            }
            try {
                test.invoke(instance);
            } catch (Exception e) {
                return Result.FAILED;
            }
            return Result.OK;
        } catch (Exception e) {
            return Result.EXCEPTION;
        } finally {
            try {
                if (after.isPresent()) {
                    after.get().invoke(instance);
                }
            } catch (Exception e) {
                return Result.EXCEPTION;
            }
        }
    }
}
