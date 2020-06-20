package com.janita.java.ssy.jdk8;

import java.util.Arrays;
import java.util.List;

/**
 * _5_Lambda
 *
 * @author zhucj
 * @since 20200623
 */
public class _5_Lambda {
}

class Test3 {
    public static void main(String[] args) {
        TheInterface i1 = () -> {
            //抽象方法的名称第lambda来说是不关心的
        };
        //通过上下文来断定一个lambda表达式表示的对象
        System.out.println(i1.getClass().getInterfaces()[0]);

        TheInterface2 i2 = () -> {
        };
        System.out.println(i2.getClass().getInterfaces()[0]);

        Runnable runnable = () -> {
        };
        new Thread(() -> {
            System.out.println("123");
        }).start();
        List<String> list = Arrays.asList("hello", "world", "hello world");
        list.forEach(item -> System.out.println(item.toUpperCase()));
        list.stream()
                .map(String::toUpperCase)//.map(item -> item.toUpperCase())
                .forEach(System.out::println);
    }
}

@FunctionalInterface
interface TheInterface {

    void myMethod();
}

@FunctionalInterface
interface TheInterface2 {

    void myMethod2();
}
