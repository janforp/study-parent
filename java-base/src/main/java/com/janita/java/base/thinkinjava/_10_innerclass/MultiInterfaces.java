package com.janita.java.base.thinkinjava._10_innerclass;

/**
 * 类说明：MultiInterfaces
 *
 * @author zhucj
 * @since 20200423
 */
public class MultiInterfaces {

    static void takesA(A a) {
    }

    static void takesB(B b) {
    }

    public static void main(String[] args) {
        X x = new X();
        Y y = new Y();
        takesA(x);
        takesA(y);
        takesB(x);

        //此处的使用比较巧妙
        takesB(y.makeB());
    }
}

interface A {

}

interface B {

}

class X implements A, B {


}

/**
 * 间接实现多重继承，如果 A ， B 是抽象类或具体类就更有说明性了，因为如果是接口的话，接口是天然支持多重实现的
 */
class Y implements A {

    B makeB() {
        return new B() {
        };
    }
}