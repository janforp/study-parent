package com.janita.java.base.thinkinjava._15_genericity.erased;

/**
 * 类说明：SimpleHolder
 *
 * @author zhucj
 * @since 20200528
 */
public class SimpleHolder {

    //下面2个的字节码是一样的
}

class RowSimpleHolder {

    private Object obj;

    public void set(Object obj) {
        this.obj = obj;
    }

    public Object get() {
        return obj;
    }

    public static void main(String[] args) {
        RowSimpleHolder holder = new RowSimpleHolder();
        holder.set("Item");
        String s = (String) holder.get();
    }
}

class GenericSimpleHolder<T> {

    private T obj;

    public void set(T obj) {
        this.obj = obj;
    }

    public T get() {
        return obj;
    }

    public static void main(String[] args) {
        GenericSimpleHolder<String> holder = new GenericSimpleHolder<String>();
        holder.set("Item");
        String s = holder.get();
    }
}
