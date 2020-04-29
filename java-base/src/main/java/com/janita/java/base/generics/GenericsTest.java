package com.janita.java.base.generics;

import java.util.List;

/**
 * 类说明：GenericsTest
 *
 * @author zhucj
 * @since 20200423
 */
public class GenericsTest {

    public static void main(String[] args) {
        System.out.println(1);
    }

    public void doSomething(List<?> list) {
        //list.add(1);
    }

    public void doRightSth() {
        Box<? extends Fruit> orangeBox = new Box<Orange>(new Orange());
        Box<? extends Fruit> appleBox = new Box<Apple>(new Apple());
        Fruit orangeBoxInstant = orangeBox.getInstant();
        Fruit appleBoxInstant = appleBox.getInstant();
        Object fruit = appleBox.getInstant();
        Fruit orangeFruit = new Orange();
    }

    public void doWrongSth() {
        //TODO ?
//        Box<Fruit> box = new Box<Orange>(new Orange());
//        Box<? extends Fruit> orangeBox = new Box<Orange>(new Orange());
//        Box<? extends Fruit> appleBox = new Box<Apple>(new Apple());
//        Orange oInst = orangeBox.getInstant();
//        Apple appInst = appleBox.getInstant();
//        orangeBox.setInstant(new Orange());
//        appleBox.setInstant(new Apple());
//        orangeBox.setInstant(new Orange());
//
//        //上边界（Upper Bounded Wildcards）
//        Box<? extends Fruit> box1 = new Box<Vegetables>(new Cabbage());
//        Box<? extends Vegetables> box2 = new Box<Cabbage>(new Cabbage());
//        Vegetables instant = box2.getInstant();

    }

    /**
     * 通常的做法
     */
    public void doNormalSth() {
        Box<Fruit> boxWithApple = new Box<>(new Apple());
        boxWithApple.setInstant(new Apple());
        Fruit instant = boxWithApple.getInstant();
        Apple appleInstant = (Apple) boxWithApple.getInstant();
    }

    public void downBounded(List<? super Vegetables> list) {
        list.add(new Cabbage());
    }
}

class Box<T> {

    private T instant;

    public void setInstant(T instant) {
        this.instant = instant;
    }

    public T getInstant() {
        return instant;
    }

    public Box(T instant) {
        this.instant = instant;
    }
}

class Food {

}

class Fruit extends Food {

}

class Orange extends Fruit {

}

class Apple extends Fruit {

}

class Vegetables extends Food {

}

class Cabbage extends Vegetables {

}