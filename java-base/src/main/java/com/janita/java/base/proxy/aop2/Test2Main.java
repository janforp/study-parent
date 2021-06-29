package com.janita.java.base.proxy.aop2;

import com.janita.java.base.proxy.aop1.Animal;
import com.janita.java.base.proxy.aop1.Cat;
import com.janita.java.base.proxy.aop1.JdkDynamicProxy;

/**
 * TestMain
 *
 * @author zhucj
 * @since 20210225
 */
public class Test2Main {

    public static void main(String[] args) {
        //1.创建被代理对象
        Cat cat = new Cat();

        //第一层代理
        JdkDynamicProxy proxy1 = new JdkDynamicProxy(cat);
        Animal proxyAnimal1 = (Animal) proxy1.getProxy();

        //第二层代理
        JdkDynamicProxy2 proxy2 = new JdkDynamicProxy2(proxyAnimal1);
        Animal proxyAnimal2 = (Animal) proxy2.getProxy();

        //被代理了2层的对象
        proxyAnimal2.eat();
    }
}