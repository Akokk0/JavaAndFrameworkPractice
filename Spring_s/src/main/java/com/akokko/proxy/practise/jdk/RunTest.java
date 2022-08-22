package com.akokko.proxy.practise.jdk;

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;
import java.lang.reflect.Proxy;

public class RunTest {
    public static void main(String[] args) {
        Advise advise = new Advise();

        Target target = new Target();

        TargetInterface targetInterface = (TargetInterface) Proxy.newProxyInstance(target.getClass().getClassLoader(), target.getClass().getInterfaces(), new InvocationHandler() {
            @Override
            public Object invoke(Object proxy, Method method, Object[] args) throws Throwable {
                advise.before();
                Object invoke = method.invoke(target, args);
                advise.after();
                return invoke;
            }
        });

        targetInterface.print();
    }
}
