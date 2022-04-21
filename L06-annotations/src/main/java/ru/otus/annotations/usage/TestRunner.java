package ru.otus.annotations.usage;

import java.lang.reflect.Constructor;
import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Optional;

import ru.otus.annotations.AfterTest;
import ru.otus.annotations.BeforeTest;
import ru.otus.annotations.Test;

public class TestRunner {

    static public void runTests(Class<?> clazz) {
        Optional<Method> before = Arrays.stream(clazz.getDeclaredMethods())
                .filter(x -> x.isAnnotationPresent(BeforeTest.class))
                .findFirst();
        Optional<Method> after= Arrays.stream(clazz.getDeclaredMethods())
                .filter(x -> x.isAnnotationPresent(AfterTest.class))
                .findFirst();
        List<Result> results = new ArrayList<>();
        for (Method declaredMethod : clazz.getDeclaredMethods()) {
            if (declaredMethod.isAnnotationPresent(Test.class)) {
                var result = runTest(clazz, before, after, declaredMethod);
                results.add(
                        result
                );
                System.out.println(
                        "Test  "
                                + result
                                + " "
                                + declaredMethod.getName());
            }
        }
        print(results);
    }

    static private void print(List<Result> results) {
        System.out.println(
                "Failed  "
                        + results
                        .stream()
                        .filter(x -> x.equals(Result.FAILED))
                        .count()
                        + " OK "
                        + results
                        .stream()
                        .filter(x -> x.equals(Result.OK))
                        .count());
    }

    static private Result runTest(
            Class<?> clazz,
            Optional<Method> before,
            Optional<Method> after,
                         Method test) {
        Object instance;
        try {
            var constructor = Arrays.stream(clazz.getDeclaredConstructors()).findFirst();
            if (constructor.isPresent()) {
                constructor.get().setAccessible(true);
                instance = constructor.get().newInstance();
            } else {
                return Result.FAILED;
            }
        } catch (Exception e) {
            return Result.FAILED;
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
            return Result.FAILED;
        } finally {
            try {
                if (after.isPresent()) {
                    after.get().invoke(instance);
                }
            } catch (Exception e) {
                return Result.FAILED;
            }
        }
    }
}
