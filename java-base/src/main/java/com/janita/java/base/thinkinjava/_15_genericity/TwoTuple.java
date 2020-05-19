package com.janita.java.base.thinkinjava._15_genericity;

/**
 * 类说明：TwoTuple
 *
 * @author zhucj
 * @since 20200528
 */
public class TwoTuple<A, B> {

    /**
     * final 类型就没必要提供get啦，反正都不能修改
     */
    public final A first;

    public final B second;

    public TwoTuple(A a, B b) {
        this.first = a;
        this.second = b;
    }

    @Override
    public String toString() {
        return "TwoTuple{" +
                "first=" + first +
                ", second=" + second +
                '}';
    }
}
