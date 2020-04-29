package com.janita.java.base.generics;

import java.util.List;
import java.util.Objects;

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

    public void doSth() {
        //Box<Fruit> box = new Box<Orange>(new Orange());
        Orange orange = new Orange();
        Apple apple = new Apple();
        Box<? extends Fruit> orangeBox = new Box<Orange>(orange);
        Box<? extends Fruit> appleBox = new Box<Apple>(apple);

        Fruit orangeBoxInstant = orangeBox.getInstant();
        //TODO ?
        //Orange oInst = orangeBox.getInstant();
        Fruit appleBoxInstant = appleBox.getInstant();
        //TODO ?
        //Apple appInst = appleBox.getInstant();
        Object fruit = appleBox.getInstant();

        //TODO ?
        //orangeBox.setInstant(orange);
        //appleBox.setInstant(apple);

        Fruit orangeFruit = new Orange();
        //TODO ?
        //orangeBox.setInstant(orangeFruit);
    }

    public void doSth2() {
        Box<Fruit> boxWithApple = new Box<>(new Apple());
        boxWithApple.setInstant(new Apple());
        Fruit instant = boxWithApple.getInstant();
        Apple appleInstant = (Apple) boxWithApple.getInstant();
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

class Fruit {

}

class Orange extends Fruit {

}

class Apple extends Fruit {

}
