package com.janita.java.base.thinkinjava._15_genericity.bounds;

/**
 * 类说明：InheritBounds
 *
 * @author zhucj
 * @since 20200528
 */
class HoldItem<T> {

    T item;

    HoldItem(T item) {
        this.item = item;
    }

    T getItem() {
        return item;
    }
}

class Colored2<T extends HasColor> extends HoldItem<T> {

    Colored2(T item) {
        super(item);
    }

    java.awt.Color color() {
        return item.getColor();
    }
}

class ColoredDimension2<T extends Dimension & HasColor> extends Colored2<T> {

    ColoredDimension2(T item) {
        super(item);
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

class Solid2<T extends Dimension & HasColor & Weight> extends ColoredDimension2<T> {

    Solid2(T item) {
        super(item);
    }

    int weight() {
        return item.weight();
    }
}

public class InheritBounds {

    public static void main(String[] args) {
        Bounded bounded = new Bounded();
        Solid2<Bounded> solid2 = new Solid2<>(bounded);
        solid2.color();
        solid2.getY();
        solid2.weight();
    }
}
