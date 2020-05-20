package com.janita.java.base.thinkinjava._15_genericity.erased;

/**
 * 类说明：CreatorGeneric
 *
 * @author zhucj
 * @since 20200528
 */
abstract class GenericWithCreate<T> {

    final T element;

    GenericWithCreate() {
        element = create();
    }

    protected abstract T create();
}

class Item {

}

class Creator extends GenericWithCreate<Item> {

    @Override
    protected Item create() {
        return new Item();
    }

    void f() {
        System.out.println(element.getClass().getSimpleName());
    }
}

public class CreatorGeneric {

    public static void main(String[] args) {
        GenericWithCreate<Item> create = new Creator();
        ((Creator) create).f();
    }
}
