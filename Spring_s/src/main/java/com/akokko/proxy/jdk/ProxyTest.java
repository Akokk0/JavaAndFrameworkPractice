package com.akokko.proxy.jdk;

import org.springframework.cglib.proxy.Enhancer;
import org.springframework.cglib.proxy.MethodInterceptor;
import org.springframework.cglib.proxy.MethodProxy;

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;
import java.lang.reflect.Proxy;

public class ProxyTest {

    public static void main(String[] args) {

        Advice advice = new Advice();  //创建增强对象

        Target target = new Target();  //创建目标对象

        //创建代理对象
        TargetInterface targetInterface = (TargetInterface) Proxy.newProxyInstance(target.getClass().getClassLoader(), target.getClass().getInterfaces(), new InvocationHandler() {
            @Override
            public Object invoke(Object proxy, Method method, Object[] args) throws Throwable {

                advice.before();  //使用前置增强方法

                Object invoke = method.invoke(target, args);  //方法执行

                advice.after();  //使用后置增强方法

                return invoke;  //返回增强对象

            }
        });

        targetInterface.save();  //使用代理对象调用代理后方法
    }
}
