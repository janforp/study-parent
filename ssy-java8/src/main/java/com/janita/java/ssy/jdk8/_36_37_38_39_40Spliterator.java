package com.janita.java.ssy.jdk8;

import java.util.function.Consumer;
import java.util.function.IntConsumer;

/**
 * _36_Spliterator
 *
 * @author zhucj
 * @since 20200917
 */
public class _36_37_38_39_40Spliterator {

    public static void main(String[] args) {
        test1();
    }

    private static void test1() {
        Consumer<Integer> intConsumer = System.out::println;
        System.out.println(intConsumer instanceof IntConsumer);

        IntConsumer accept = intConsumer::accept;
        System.out.println(accept instanceof IntConsumer);
    }
}
