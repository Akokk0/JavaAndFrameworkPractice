package com.akokko.jdbc;

public class test {
    public static void main(String[] args) {
        System.out.println(test.class.getClassLoader().getResource("jdbc.properties").getPath());
    }
}
