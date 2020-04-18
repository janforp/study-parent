package com.shengsiyuan.netty.decorator;

/**
 * 具体构建角色，类似 FileInputStream
 */
public class ConcreteComponent implements Component{

    @Override
    public void doSomething() {
        System.out.println("基本功能A");
    }
}
