package com.janita.java.ssy.jdk8;

/**
 * _14_DefaultMethod
 * java约定：实现类的优先级 > 接口的优先级
 *
 * @author zhucj
 * @since 20200723
 */
public class _14_DefaultMethod {
}

interface MyInterface1 {

    default void myMethod() {
        System.out.println("myMethod");
    }
}

class MyInterface1Impl implements MyInterface1 {

    @Override
    public void myMethod() {
        System.out.println("MyInterface1Impl");
    }
}

interface MyInterface2 {

    default void myMethod() {
        System.out.println("myMethod2");
    }
}

class MyClass implements MyInterface1 {

    public static void main(String[] args) {
        MyClass myClass = new MyClass();
        myClass.myMethod();
    }
}

class MyClass2 implements MyInterface1, MyInterface2 {

    @Override
    public void myMethod() {
        //使用某个接口的，而不是直接赋值代码
        MyInterface2.super.myMethod();
    }

    public static void main(String[] args) {
        MyClass2 myClass = new MyClass2();
        myClass.myMethod();
    }
}

class MyClass3 extends MyInterface1Impl implements MyInterface2 {
    public static void main(String[] args) {
        MyClass3 myClass = new MyClass3();
        //java约定：实现类的优先级 > 接口的优先级
        myClass.myMethod();
    }
}