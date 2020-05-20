package com.janita.java.base.thinkinjava._15_genericity.erased;

/**
 * 类说明：FactoryConstraint
 *
 * @author zhucj
 * @since 20200528
 */
interface FactoryI<T> {

    T create();
}

class Foo2<T> {

    public T t;

    //该方法必须要一个工厂的实现或者子类
    public <F extends FactoryI<T>> Foo2(F factory) {
        t = factory.create();
    }
}

//具体的工厂
class IntegerFactory implements FactoryI<Integer> {

    @Override
    public Integer create() {
        return 10;
    }
}

//具体的工厂
class Widget {

    public static class Factory implements FactoryI<Widget> {

        @Override
        public Widget create() {
            return new Widget();
        }
    }

    @Override
    public String toString() {
        return "Widget";
    }
}

public class FactoryConstraint {

    public static void main(String[] args) {
        System.out.println(new Foo2<>(new IntegerFactory()).t);
        ;
        System.out.println(new Foo2<>(new Widget.Factory()).t);
        ;
    }
}


