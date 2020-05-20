package com.janita.java.base.thinkinjava._15_genericity.erased;

/**
 * 类说明：Manipulation
 *
 * @author zhucj
 * @since 20200528
 */
public class Manipulation {

    public static void main(String[] args) {
        HasF hasF = new HasF();
        ErrorManipulator<HasF> manipulator = new ErrorManipulator<>(hasF);
        manipulator.manipulate();
    }
}

class ErrorManipulator<T> {

    private T obj;

    public ErrorManipulator(T t) {
        this.obj = t;
    }

    public void manipulate() {

        //C++ 的泛型是支持这样的语法的，因为java类型擦除的，编译的时候是无法指定泛型的具体类型的
        //obj.f();
    }
}

class Manipulator<T extends HasF> {

    private T obj;

    public Manipulator(T t) {
        this.obj = t;
    }

    public void manipulate() {
        //指定了泛型的上边界之后就可以明确指定泛型是有一个f()函数了
        //擦除到他的第一个边界（此处为 HasF），就好像在类的声明中使用了 HasF 替换了 T 一样
        obj.f();
    }
}
