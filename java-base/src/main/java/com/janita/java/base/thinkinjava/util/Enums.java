package com.janita.java.base.thinkinjava.util;

import java.util.Random;

/**
 * Enums
 *
 * @author zhucj
 * @since 20200528
 */
public class Enums {

    private static Random rand = new Random(47);

    public static <T extends Enum<T>> T random(Class<T> ec) {
        T[] enumConstants = ec.getEnumConstants();
        return random(enumConstants);
    }

    public static <T> T random(T[] values) {
        return values[rand.nextInt(values.length)];
    }
}