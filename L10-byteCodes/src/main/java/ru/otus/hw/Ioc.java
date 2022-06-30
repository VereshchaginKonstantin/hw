package ru.otus.hw;

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;
import java.lang.reflect.Proxy;
import java.util.Arrays;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;
import java.util.stream.Collectors;


class Ioc {

    private Ioc() {
    }

    static MyClassInterface createMyClass() {
        InvocationHandler handler = new DemoInvocationHandler(new TestLogging());
        return (MyClassInterface) Proxy.newProxyInstance(Ioc.class.getClassLoader(),
                new Class<?>[]{MyClassInterface.class}, handler);
    }

    static class AnnotationPresentsInfoStorage {
        private final static Map<Class<?>, AnnotationPresentsInfo> annotationPresentsInfos
                = new ConcurrentHashMap<>();

        public static Boolean isAnnotationPresent(Class<?> clazz, Method method) {
            return annotationPresentsInfos
                    .computeIfAbsent(clazz,
                            c -> createAnnotationPresentsInfos(c))
                    .isAnnotationPresent(method);
        }

        private static AnnotationPresentsInfo createAnnotationPresentsInfos(Class<?> clazz) {
            return new AnnotationPresentsInfo(clazz);
        }

    }

    static class DemoInvocationHandler implements InvocationHandler {
        private final MyClassInterface myClass;
        // вызывается один раз
        private final static Class<MyClassInterface> myClassT = MyClassInterface.class;

        DemoInvocationHandler(MyClassInterface myClass) {
            this.myClass = myClass;
        }

        @Override
        public Object invoke(Object proxy, Method method, Object[] args) throws Throwable {
            if (AnnotationPresentsInfoStorage.isAnnotationPresent(myClassT, method)) {
                System.out.println("executed method: " +
                        method + ", param:" +
                        Arrays.stream(args)
                                .map(Object::toString)
                                .collect(Collectors.toList()));
            }
            return method.invoke(myClass, args);
        }




        @Override
        public String toString() {
            return "DemoInvocationHandler{" +
                    "myClass=" + myClass +
                    '}';
        }
    }
}
