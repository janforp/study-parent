package com.janita.java.base.thinkinjava._15_genericity.troubles;

/**
 * 类说明：SelfBoundingMethods
 *
 * @author zhucj
 * @since 20200528
 */
public class SelfBoundingMethods {

    /**
     * 这样定义可以防止这个方法被应用于除上述形式的自限定参数之外的任何事务上
     * 也就是说这个方法的参数必须是 SelfBounded2 类型的
     *
     * @param arg
     * @param <T>
     * @return
     */
    static <T extends SelfBounded2<T>> T f(T arg) {
        return arg.set(arg).get();
    }

    public static void main(String[] args) {
        A a = f(new A());
        a.get();
    }
}