package com.janita.java.base.thinkinjava._15_genericity;

/**
 * 类说明：Holder
 *
 * @author zhucj
 * @since 20200528
 */
public class Holder1 {

    private AutoMobile autoMobile;

    public Holder1(AutoMobile mobile) {
        this.autoMobile = mobile;
    }

    public AutoMobile get() {
        return autoMobile;
    }
}

class AutoMobile {

}

class Holder2 {

    private Object a;

    public Holder2(Object a) {
        this.a = a;
    }

    public void set(Object a) {
        this.a = a;
    }

    public Object get() {
        return a;
    }

    public static void main(String[] args) {
        Holder2 h2 = new Holder2(new AutoMobile());
        AutoMobile a = (AutoMobile) h2.get();
        h2.set("Not an Automobile");
        String s = (String) h2.get();
        h2.set(1);
        Integer x = (Integer) h2.get();
    }
}

class Holder3<T> {

    private T a;

    public Holder3(T a) {
        this.a = a;
    }

    public void set(T a) {
        this.a = a;
    }

    public T get() {
        return a;
    }

    private static class LaJiMobile extends AutoMobile {

    }

    public static void main(String[] args) {
        Holder3<AutoMobile> holder3 = new Holder3<>(new AutoMobile());
        AutoMobile autoMobile = holder3.get();
        holder3.set(new LaJiMobile());
    }
}
