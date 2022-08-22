package com.akokko.proxy.practise.cglib;

public class Advice {
    public void before() {
        System.out.println("前置增强方法！！！");
    }

    public void after() {
        System.out.println("后置增强方法！！！");
    }
}
