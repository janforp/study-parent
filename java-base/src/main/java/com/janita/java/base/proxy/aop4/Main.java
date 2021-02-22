package com.janita.java.base.proxy.aop4;

import com.janita.java.base.proxy.aop1.Animal;
import com.janita.java.base.proxy.aop1.Cat;

/**
 * Main
 *
 * @author zhucj
 * @since 20210225
 */
public class Main {

    public static void main(String[] args) {
        //创建被代理对象
        Cat cat = new Cat();

        //创建JdkDynamicProxy,用来创建代理对象
        JdkDynamicProxy proxy = new JdkDynamicProxy(cat);

        //添加拦截器
        proxy.addInterceptor(new OneMethodInterceptor());
        proxy.addInterceptor(new TwoMethodInterceptor());

        //获取代理对象
        Animal animal = (Animal) proxy.getProxy();

        //调用方法
        animal.eat();
    }
}
