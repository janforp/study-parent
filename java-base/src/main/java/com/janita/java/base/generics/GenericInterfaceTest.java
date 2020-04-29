package com.janita.java.base.generics;

import java.util.Random;
import java.util.UUID;

/**
 * 类说明：genericInterfaceTest
 *
 * @author zhucj
 * @since 20200423
 */
public class GenericInterfaceTest {

    public static void main(String[] args) {

        NumberGenerator.test();

        StringGenerator.test();

    }
}

interface Generator<T> {

    T next();
}

class NumberGenerator implements Generator<Integer> {

    @Override
    public Integer next() {
        return new Random().nextInt(10);
    }

    static void test() {
        Generator<Integer> generator = new NumberGenerator();
        Integer next = generator.next();
        System.out.println(next);
        Generator numberGenerator = new NumberGenerator();
        Object nextNumber = numberGenerator.next();
        System.out.println(nextNumber instanceof Integer);
        System.out.println(nextNumber);
    }
}

class StringGenerator implements Generator<String> {

    @Override
    public String next() {
        return UUID.randomUUID().toString();
    }

    static void test() {
        Generator<String> generator = new StringGenerator();
        String next = generator.next();
        System.out.println(next);
        Generator numberGenerator = new StringGenerator();
        Object nextNumber = numberGenerator.next();
        System.out.println(nextNumber instanceof String);
        System.out.println(nextNumber);
    }
}



