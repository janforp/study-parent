package com.shengsiyuan.netty.decorator;

public class DecoratorTest {

    public static void main(String[] args) {
        Component component = new ConcreteComponent();
        component.doSomething();

        System.out.println("---------------");

        component = new ConcreteDecorator1(new ConcreteDecorator2(new ConcreteComponent()));
        component.doSomething();

        System.out.println("---------------");

        component = new ConcreteDecorator1(new ConcreteDecorator1(new ConcreteComponent()));
        component.doSomething();

        System.out.println("---------------");

        component = new ConcreteDecorator2(new ConcreteDecorator1(new ConcreteComponent()));
        component.doSomething();

        System.out.println("---------------");

        component = new ConcreteDecorator2(new ConcreteDecorator2(new ConcreteComponent()));
        component.doSomething();

        //具体怎么使用完全交给了客户端，灵活得很
    }
}
