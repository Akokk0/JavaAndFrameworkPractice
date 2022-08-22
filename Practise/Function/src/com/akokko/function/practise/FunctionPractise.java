package com.akokko.function.practise;

import java.util.function.Consumer;
import java.util.function.Predicate;

public class FunctionPractise {
    public static void main(String[] args) {
        String[] array = { "迪丽热巴,女", "古力娜扎,女", "马尔扎哈,男" };
            splitString(array, (String massage) -> {
                System.out.print("姓名：" + massage.split(",")[0]);
            }, (s) -> {
                System.out.println("。性别：" + s.split(",")[1] + "。");
            });
    }

    public static void splitString(String[] arr, Consumer<String> con1, Consumer<String> con2) {
        for (String s : arr) {
            con1.andThen(con2).accept(s);
        }
    }

}
