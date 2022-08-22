package com.akokko.proxy.cglib;

import org.springframework.cglib.proxy.Enhancer;
import org.springframework.cglib.proxy.MethodInterceptor;
import org.springframework.cglib.proxy.MethodProxy;

import java.lang.reflect.Method;

public class ProxyTest {

    public static void main(String[] args) {
        Target target = new Target();  //创建目标对象

        Advice advice = new Advice();  //创建增强对象

        Enhancer enhancer = new Enhancer();  //创建代理对象

        enhancer.setSuperclass(Target.class);  //设置父类

        //设置回调函数
        enhancer.setCallback(new MethodInterceptor() {
            @Override
            public Object intercept(Object proxy, Method method, Object[] args, MethodProxy methodProxy) throws Throwable {
                advice.before();  //前置增强

                Object invoke = method.invoke(target, args);  //使用方法

                advice.after();  //后置增强

                return invoke;  //返回方法
            }
        });

        Target proxy = (Target) enhancer.create();  //创建代理对象

        proxy.save();
    }

}
