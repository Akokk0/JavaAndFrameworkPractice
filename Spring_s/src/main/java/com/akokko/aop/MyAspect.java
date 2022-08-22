package com.akokko.aop;

import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;

@Aspect
public class MyAspect {

    @Before("execution(* com.akokko.aop.*.*(..))")
    public void before() {
        System.out.println("前置增强方法");
    }
}
