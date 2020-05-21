package com.janita.java.base.thinkinjava._15_genericity.troubles;

/**
 * 类说明：SelfBounding
 *
 * 自限定
 *
 * class A extends SelfBounded<A> {}
 *
 * 他可以保证类型参数必须与正在被定义的类相同
 *
 * 自限定：这个类所用的类型将于使用这个参数的类具有相同的基类型
 *
 * @author zhucj
 * @since 20200528
 */
class SelfBounded2<T extends SelfBounded2<T>> {

    T element;

    SelfBounded2<T> set(T arg) {
        element = arg;
        return this;
    }

    T get() {
        return element;
    }
}

class A extends SelfBounded2<A> {

}

class B extends SelfBounded2<A> {

}

class C extends SelfBounded2<C> {

    C setAndGet(C arg) {
        set(arg);
        return get();
    }
}

class D {

}

//class E extends SelfBounded2<D> {}

class F extends SelfBounded {

}

public class SelfBounding {

    public static void main(String[] args) {

        A a = new A();
        a.set(new A());

        a = a.set(new A()).get();

        a = a.get();

        C c = new C();
        c = c.setAndGet(new C());
    }
}
