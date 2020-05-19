package com.janita.java.base.thinkinjava._15_genericity;

/**
 * 类说明：FourTuple
 *
 * @author zhucj
 * @since 20200528
 */
public class FourTuple<A, B, C, D> extends ThreeTuple<A, B, C> {

    public final D fourth;

    public FourTuple(A a, B b, C c, D d) {
        super(a, b, c);
        fourth = d;
    }

    @Override
    public String toString() {
        return "(" + first + ", " + second + ", " +
                third + ", " + fourth + ")";
    }
}