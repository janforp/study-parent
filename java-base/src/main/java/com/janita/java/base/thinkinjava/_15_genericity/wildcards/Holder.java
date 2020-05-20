package com.janita.java.base.thinkinjava._15_genericity.wildcards;

/**
 * 类说明：Holder
 *
 * @author zhucj
 * @since 20200528
 */
public class Holder<T> {

    private T value;

    public Holder() {
    }

    public Holder(T val) {
        value = val;
    }

    public void set(T val) {
        value = val;
    }

    public T get() {
        return value;
    }

    @Override
    public boolean equals(Object obj) {
        return value.equals(obj);
    }

    public static void main(String[] args) {
        Holder<Apple> appleHolder = new Holder<Apple>(new Apple());
        Apple d = appleHolder.get();
        appleHolder.set(d);
        // Holder<Fruit> Fruit = appleHolder; // Cannot upcast
        Holder<? extends Fruit> fruit = appleHolder; // OK
        Fruit p = fruit.get();
        d = (Apple) fruit.get(); // Returns 'Object'
        try {
            Orange c = (Orange) fruit.get(); // No warning
        } catch (Exception e) {
            System.out.println(e);
        }
        // fruit.set(new appleHolder()); // Cannot call set()
        // fruit.set(new Fruit()); // Cannot call set()
        System.out.println(fruit.equals(d)); // OK
    }
}
