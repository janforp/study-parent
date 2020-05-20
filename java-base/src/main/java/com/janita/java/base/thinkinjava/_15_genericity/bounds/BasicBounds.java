package com.janita.java.base.thinkinjava._15_genericity.bounds;

/**
 * 类说明：BasicBounds
 *
 * @author zhucj
 * @since 20200528
 */
interface HasColor {

    java.awt.Color getColor();
}

class Colored<T extends HasColor> {

    T item;

    Colored(T item) {
        this.item = item;
    }

    T getItem() {
        return item;
    }

    /**
     * 上边界允许你使用该方法的调用
     */
    java.awt.Color color() {
        return item.getColor();
    }
}

class Dimension {

    public int x, y, z;
}

//这样不行， class must be first, then interfaces
//class ErrorColoredDimension<T extends HasColor & Dimension> { }

class ColoredDimension<T extends Dimension & HasColor> {

    T item;

    ColoredDimension(T item) {
        this.item = item;
    }

    T getItem() {
        return item;
    }

    java.awt.Color color() {
        return item.getColor();
    }

    int getX() {
        return item.x;
    }

    int getY() {
        return item.y;
    }

    int getZ() {
        return item.z;
    }
}

interface Weight {

    int weight();
}

// As with inheritance, you can have only one
// concrete class but multiple interfaces:
class Solid<T extends Dimension & HasColor & Weight> {

    T item;

    Solid(T item) {
        this.item = item;
    }

    T getItem() {
        return item;
    }

    java.awt.Color color() {
        return item.getColor();
    }

    int getX() {
        return item.x;
    }

    int getY() {
        return item.y;
    }

    int getZ() {
        return item.z;
    }

    int weight() {
        return item.weight();
    }
}

class Bounded extends Dimension implements HasColor, Weight {

    public java.awt.Color getColor() {
        return null;
    }

    public int weight() {
        return 0;
    }
}

public class BasicBounds {

    public static void main(String[] args) {
        Bounded bounded = new Bounded();
        Solid<Bounded> solid = new Solid<>(bounded);
        solid.color();
        solid.getY();
        solid.weight();
    }
}
