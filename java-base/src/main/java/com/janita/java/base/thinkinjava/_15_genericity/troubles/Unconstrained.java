package com.janita.java.base.thinkinjava._15_genericity.troubles;

/**
 * 类说明：Unconstrained
 *
 * @author zhucj
 * @since 20200528
 */
public class Unconstrained {

    public static void main(String[] args) {

        BasicOther b = new BasicOther();
        BasicOther b2 = new BasicOther();

        b.set(new Other());
        Other other = b.get();
        b.f();
    }
}

class Other{}

class BasicOther extends BasicHolder<Other> {}
