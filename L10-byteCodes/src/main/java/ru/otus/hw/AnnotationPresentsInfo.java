package ru.otus.hw;

import java.lang.reflect.Method;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

class AnnotationPresentsInfo {

    private final Class<?> myClass;
    private final Map<Method, Boolean> isAnnotationPresentFlags;

    public AnnotationPresentsInfo(Class<?> myClass) {
        // можно сразу инициализировать new ConcurrentHashMap<>()
        isAnnotationPresentFlags = new ConcurrentHashMap<>();
        this.myClass = myClass;
    }

    public Boolean isAnnotationPresent(Method method) {
        return isAnnotationPresentFlags
                .computeIfAbsent(method, m -> isAnnotationPresentCompute(m));
    }

    // вызывается один раз для method
    private Boolean isAnnotationPresentCompute(Method method) {
        try {
            return myClass
                    .getMethod(
                            method.getName(),
                            method.getParameterTypes())
                    .isAnnotationPresent(Log.class);
        } catch (NoSuchMethodException e) {
            return null;
        }
    }
}

