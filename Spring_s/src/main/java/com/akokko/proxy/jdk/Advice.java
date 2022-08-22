package com.akokko.proxy.jdk;

public class Advice {
    public void before() {
        System.out.println("前置增强方法！！！");
    }

    public void after() {
        System.out.println("后置增强方法！！！");
    }
}
