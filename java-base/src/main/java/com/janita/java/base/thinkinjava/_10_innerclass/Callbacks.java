package com.janita.java.base.thinkinjava._10_innerclass;

/**
 * 类说明：闭包与回调
 *
 * @author zhucj
 * @since 20200423
 */
public class Callbacks {

    public static void main(String[] args) {

        Callee1 callee1 = new Callee1();
        Callee2 callee2 = new Callee2();
        MyIncrement.f(callee2);
        Caller caller1 = new Caller(callee1);
        Caller caller2 = new Caller(callee2.getCallbackReference());

        caller1.go();
        caller1.go();
        caller2.go();
        caller2.go();
    }
}

interface Incrementable {

    void increment();
}

class Callee1 implements Incrementable {

    private int i = 0;

    @Override
    public void increment() {
        i++;
        System.out.println(i);
    }
}

class MyIncrement {

    public void increment() {
        System.out.println("Other operation");
    }

    static void f(MyIncrement mi) {

        mi.increment();
    }
}

class Callee2 extends MyIncrement {

    private int i = 0;

    @Override
    public void increment() {
        super.increment();
        i++;
        System.out.println(i);
    }

    /**
     * 闭包：此处实现接口，但是返回的却是一个 Callee2 的钩子，用于回调
     *
     * 闭包是一个可调用的对象，它记录了一些信息，这些信息来自于创建它的作用域。
     */
    private class Closure implements Incrementable {

        @Override
        public void increment() {
            //但是返回的却是一个 Callee2 的钩子，用于回调
            Callee2.this.increment();
        }
    }

    Incrementable getCallbackReference() {
        return new Closure();
    }
}

class Caller {

    private final Incrementable callbackReference;

    Caller(Incrementable cbh) {
        this.callbackReference = cbh;
    }

    void go() {
        callbackReference.increment();
    }
}
