package com.akokko.proxy.practise.jdk;

public class Advise {
    public void before() {
        System.out.println("前置增强方法。。。");
    }

    public void after() {
        System.out.println("后置增强方法。。。");
    }
}
