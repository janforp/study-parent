package com.janita.java.base.thinkinjava._15_genericity;

/**
 * 类说明：ThreeTuple
 *
 * @author zhucj
 * @since 20200528
 */
public class ThreeTuple<A,B,C> extends TwoTuple<A,B> {

    public final C third;

    public ThreeTuple(A a, B b, C c) {
        super(a, b);
        third = c;
    }

    @Override
    public String toString() {
        return "ThreeTuple{" +
                "third=" + third +
                ", first=" + first +
                ", second=" + second +
                '}';
    }
}
