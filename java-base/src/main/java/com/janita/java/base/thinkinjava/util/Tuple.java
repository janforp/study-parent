package com.janita.java.base.thinkinjava.util;

import com.janita.java.base.thinkinjava._15_genericity.FiveTuple;
import com.janita.java.base.thinkinjava._15_genericity.FourTuple;
import com.janita.java.base.thinkinjava._15_genericity.ThreeTuple;
import com.janita.java.base.thinkinjava._15_genericity.TwoTuple;

/**
 * 类说明：Tuple
 *
 * @author zhucj
 * @since 20200528
 */
public class Tuple {

    public static <A, B> TwoTuple<A, B> tuple(A a, B b) {
        return new TwoTuple<A, B>(a, b);
    }

    public static <A, B, C> ThreeTuple<A, B, C> tuple(A a, B b, C c) {
        return new ThreeTuple<A, B, C>(a, b, c);
    }

    public static <A, B, C, D> FourTuple<A, B, C, D> tuple(A a, B b, C c, D d) {
        return new FourTuple<A, B, C, D>(a, b, c, d);
    }

    public static <A, B, C, D, E> FiveTuple<A, B, C, D, E> tuple(A a, B b, C c, D d, E e) {
        return new FiveTuple<A, B, C, D, E>(a, b, c, d, e);
    }
}