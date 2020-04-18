package com.shengsiyuan.netty.decorator;

/**
 * 装饰角色
 * 持有一个具体角色的引用
 *
 * 并且实现抽象构建
 */
public class Decorator implements Component{

    private Component component;

    public Decorator(Component component) {
        this.component = component;
    }

    @Override
    public void doSomething() {
        component.doSomething();
    }
}
