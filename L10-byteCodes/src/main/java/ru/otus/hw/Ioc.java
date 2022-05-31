package ru.otus.hw;

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;
import java.lang.reflect.Proxy;
import java.util.Arrays;
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

        DemoInvocationHandler(MyClassInterface myClass) {
            this.myClass = myClass;
        }

        @Override
        public Object invoke(Object proxy, Method method, Object[] args) throws Throwable {
            if (isAnnotationPresent(method)) {
                System.out.println("executed method: " +
                        method + ", param:" +
                        Arrays.stream(args).map(x -> x.toString())
                                .collect(Collectors.toList()));
            }
            return method.invoke(myClass, args);
        }

        private boolean isAnnotationPresent(Method method) throws NoSuchMethodException {
            return myClass
                    .getClass()
                    .getMethod(
                            method.getName(),
                            method.getParameterTypes())
                    .isAnnotationPresent(Log.class);
        }

        @Override
        public String toString() {
            return "DemoInvocationHandler{" +
                    "myClass=" + myClass +
                    '}';
        }
    }
}
