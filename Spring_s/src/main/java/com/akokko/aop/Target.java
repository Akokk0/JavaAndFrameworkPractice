package com.akokko.aop;

import org.springframework.stereotype.Component;

public class Target implements TargetInterface {
    public void save() {
        System.out.println("save running!!!");
    }
}
