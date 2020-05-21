package com.janita.java.base.thinkinjava._15_genericity.troubles;

/**
 * 类说明：BasicHolder
 *
 * CRG的本质：基类用导出类替代其参数
 *
 * @author zhucj
 * @since 20200528
 */
public class BasicHolder<T> {

    T element;

    public void set(T element) {
        this.element = element;
    }

    public T get() {
        return element;
    }

    void f() {
        System.out.println(element.getClass().getSimpleName());
    }
}

class SubType extends BasicHolder<SubType> {

}

class CRGWithBasicHolder {

    public static void main(String[] args) {
        SubType st1 = new SubType();
        SubType st2 = new SubType();
        st1.set(st2);
        SubType st3 = st1.get();
        System.out.println(st3 instanceof BasicHolder);
        st1.f();

        st3.set(st1);
        st3.f();
    }
}
