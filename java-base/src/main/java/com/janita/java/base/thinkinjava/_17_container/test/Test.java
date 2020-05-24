package com.janita.java.base.thinkinjava._17_container.test;

/**
 * 类说明：Test
 *
 * @author zhucj
 * @since 20200528
 */
public abstract class Test<C> {

    String name;

    public Test(String name) {
        this.name = name;
    }

    // Override this method for different tests.
    // Returns actual number of repetitions of test.
    public abstract int test(C container, TestParam tp);
}
