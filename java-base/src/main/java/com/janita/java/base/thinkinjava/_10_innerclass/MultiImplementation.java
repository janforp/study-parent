package com.janita.java.base.thinkinjava._10_innerclass;

/**
 * 类说明：MultiImplementation
 *
 * @author zhucj
 * @since 20200423
 */
public class MultiImplementation {

    static void takesD(D d) {
    }

    static void takesE(E e) {
    }

    public static void main(String[] args) {
        Z z = new Z();
        takesD(z);
        takesE(z.makeE());
    }
}

class D {

}

abstract class E {

}

/**
 * 间接实现多重继承
 */
class Z extends D {

    E makeE() {
        return new E() {
        };
    }
}