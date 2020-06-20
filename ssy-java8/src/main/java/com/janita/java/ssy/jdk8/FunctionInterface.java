package com.janita.java.ssy.jdk8;

import java.util.Arrays;
import java.util.List;

/**
 * FunctionInterface
 *
 * video 4:04_深入函数式接口与方法引用
 *
 * @author zhucj
 * @since 20200623
 */
@FunctionalInterface
public interface FunctionInterface {

    void test();

    //Object 的方法可以
    @Override
    int hashCode();

    @Override
    boolean equals(Object obj);

    @Override
    String toString();

    //不能添加
    //int doElse();
}

class TestFi{

    public void myTest(FunctionInterface functionInterface) {
        System.out.println(1);
        functionInterface.test();
        System.out.println(2);
    }

    public static void main(String[] args) {
        TestFi fi = new TestFi();
        fi.myTest(() -> {
            System.out.println("myTest");
        });

        System.out.println("------");

        FunctionInterface functionInterface = new FunctionInterface() {

            @Override
            public void test() {
                System.out.println("hello");
            }
        };
        System.out.println(functionInterface.getClass());
        System.out.println(functionInterface.getClass().getSuperclass());
        System.out.println(functionInterface.getClass().getInterfaces()[0]);
    }
}

class Test1 {

    public static void main(String[] args) {
        List<Integer> list = Arrays.asList(1, 2, 3, 4, 5);
        //method reference
        list.forEach(System.out::println);
    }
}
