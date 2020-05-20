package com.janita.java.base.thinkinjava._15_genericity.coffee;

/**
 * 类说明：Coffee
 *
 * @author zhucj
 * @since 20200528
 */
public class Coffee {

    private static long counter = 0;

    private final long id = counter++;

    @Override
    public String toString() {
        return getClass().getSimpleName() + " " + id;
    }
}
