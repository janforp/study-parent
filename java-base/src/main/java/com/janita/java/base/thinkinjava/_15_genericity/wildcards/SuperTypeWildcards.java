package com.janita.java.base.thinkinjava._15_genericity.wildcards;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * 类说明：SuperTypeWildcards
 *
 * @author zhucj
 * @since 20200528
 */
public class SuperTypeWildcards {

    static void writeTo(List<? super Apple> apples) {
        apples.add(new Apple());
        apples.add(new Jonathan());
        // apples.add(new Fruit()); // Error
    }
}

class GenericWriting {

    static <T> void writeExact(List<T> list, T item) {
        list.add(item);
    }

    static List<Apple> apples = new ArrayList<>();

    static List<Fruit> fruit = new ArrayList<>();

    static void f1() {
        writeExact(apples, new Apple());
        writeExact(fruit, new Apple()); // Error:
        // Incompatible types: found Fruit, required Apple
    }

    static <T> void writeWithWildcard(List<? super T> list, T item) {
        list.add(item);
    }

    static void f2() {
        writeWithWildcard(apples, new Apple());
        writeWithWildcard(fruit, new Apple());
    }

    public static void main(String[] args) {
        f1();
        f2();
    }
}

class GenericReading {

    static <T> T readExact(List<T> list) {
        return list.get(0);
    }

    static List<Apple> apples = Arrays.asList(new Apple());

    static List<Fruit> fruit = Arrays.asList(new Fruit());

    // A static method adapts to each call:
    static void f1() {
        Apple a = readExact(apples);
        Fruit f = readExact(fruit);
        f = readExact(apples);
    }

    // If, however, you have a class, then its type is
    // established when the class is instantiated:
    static class Reader<T> {

        T readExact(List<T> list) {
            return list.get(0);
        }
    }

    static void f2() {
        Reader<Fruit> fruitReader = new Reader<>();
        Fruit f = fruitReader.readExact(fruit);
        // Fruit a = fruitReader.readExact(apples); // Error:
        // readExact(List<Fruit>) cannot be
        // applied to (List<Apple>).
    }

    static class CovariantReader<T> {

        T readCovariant(List<? extends T> list) {
            return list.get(0);
        }
    }

    static void f3() {
        CovariantReader<Fruit> fruitReader = new CovariantReader<>();
        Fruit f = fruitReader.readCovariant(fruit);
        Fruit a = fruitReader.readCovariant(apples);
    }

    public static void main(String[] args) {
        f1();
        f2();
        f3();
    }
}