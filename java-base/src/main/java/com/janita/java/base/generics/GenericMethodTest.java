package com.janita.java.base.generics;

/**
 * 类说明：GenericMethodTest
 *
 * @author zhucj
 * @since 20200423
 */
public class GenericMethodTest {

    public static void main(String[] args) {
        GenericMethodTest methodTest = new GenericMethodTest();
        methodTest.func(1);
        methodTest.func("字符串");
    }

    public <T> void func(T x) {
        Class<?> xClass = x.getClass();
        String xClassName = xClass.getName();
        System.out.println(xClassName);
    }
}
