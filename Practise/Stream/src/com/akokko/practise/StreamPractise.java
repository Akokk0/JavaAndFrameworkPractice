package com.akokko.practise;

import java.util.ArrayList;
import java.util.stream.Stream;

public class StreamPractise {
    public static void main(String[] args) {
        Stream<String> stream = Stream.of("张三丰", "张无忌", "张全蛋", "王二狗", "青蛙车", "ZZY");

        stream.filter((name) -> name.startsWith("张")).forEach((name) -> System.out.println(name));

        ArrayList<String> list = new ArrayList<>();
        list.add("1");
        list.add("2");
        list.add("3");
        list.add("4");
        list.add("5");
        list.add("6");
        list.add("7");
        list.add("8");
        list.add("9");
        list.forEach((name) -> System.out.println(name));
    }
}
