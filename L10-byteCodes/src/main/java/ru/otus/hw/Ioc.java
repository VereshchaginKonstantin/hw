package ru.otus.hw;

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;
import java.lang.reflect.Proxy;
import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;
import java.util.stream.Collectors;


class Ioc {

    private Ioc() {
    }

    static MyClassInterface createMyClass() {
        InvocationHandler handler = new DemoInvocationHandler(new TestLogging());
        return (MyClassInterface) Proxy.newProxyInstance(Ioc.class.getClassLoader(),
                new Class<?>[]{MyClassInterface.class}, handler);
    }

    static class DemoInvocationHandler implements InvocationHandler {
        private final MyClassInterface myClass;
        private final Map<Method, Boolean> isAnnotationPresentFlags = new HashMap<>();


        DemoInvocationHandler(MyClassInterface myClass) {
            this.myClass = myClass;
        }

        @Override
        public Object invoke(Object proxy, Method method, Object[] args) throws Throwable {
            if (isAnnotationPresent(method)) {
                System.out.println("executed method: " +
                        method + ", param:" +
                        Arrays.stream(args)
                                .map(Object::toString)
                                .collect(Collectors.toList()));
            }
            return method.invoke(myClass, args);
        }

        private Boolean isAnnotationPresent(Method method) {
            return isAnnotationPresentFlags
                    .computeIfAbsent(method, m ->  isAnnotationPresentCompute(m));
        }

        private Boolean isAnnotationPresentCompute(Method method) {
            try {
                return myClass
                        .getClass()
                        .getMethod(
                                method.getName(),
                                method.getParameterTypes())
                        .isAnnotationPresent(Log.class);
            } catch (NoSuchMethodException e) {
                return null;
            }
        }

        @Override
        public String toString() {
            return "DemoInvocationHandler{" +
                    "myClass=" + myClass +
                    '}';
        }
    }
}
