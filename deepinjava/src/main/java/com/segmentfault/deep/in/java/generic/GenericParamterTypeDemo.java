package com.segmentfault.deep.in.java.generic;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;
import java.util.function.Consumer;

/**
 * GenericParamterTypeDemo
 *
 * @author zhucj
 * @since 20200917
 */
public class GenericParamterTypeDemo {

    public static void main(String[] args) {
        Container<String> a = new Container("string");
        //Error:Integer不是 CharSequence 的子类
        //Container<Integer> b = new Container(2);

        Container<StringBuffer> bufferContainer = new Container("sdfsfsf");
        System.out.println(bufferContainer.element);
        StringBuffer element = bufferContainer.element;
        //ava.lang.String cannot be cast to java.lang.StringBuffer
        //	at com.segmentfault.deep.in.java.generic.GenericParamterTypeDemo.main(GenericParamterTypeDemo.java:18)
        System.out.println(element.toString());

    }

    public static class Container<E extends CharSequence> {

        private E element;

        public Container(E e) {
            this.element = e;
        }
    }

    public static class C {

    }

    public interface I {

    }

    public interface I2 {

    }

    /**
     * 多界限泛型类型的 extends 第一个类型允许是具体类（也可以是接口）
     * 第二个或者更多参数类型必须是接口
     *
     * @param <T>
     */
    public static class Template<T extends C & I & I2> {

    }

    public static <E extends CharSequence> void addChar(Collection<E> collection, E e) {
        collection.add(e);
    }

    public static <C extends Collection<E>, E extends CharSequence> void add(C c, E e) {
        c.add(e);
    }

    public static <E> void add(Collection<E> collection, E e) {
        collection.add(e);
    }

    public static <C extends Iterable<E>, E> void forEach(C source, Consumer<E> consumer) {
        source.forEach(consumer);
    }

    static class Add {

        public static void main(String[] args) {
            //泛型擦除
            add(new ArrayList<>(), "你好");
            add(new ArrayList<>(), 1);

            addChar(new ArrayList<>(), "你好");
            //上限约束
            //addChar(new ArrayList<>(),1);
            //TODO  此处会调用哪一个 add 方法？
            add(new ArrayList<>(), "1");

            forEach(Arrays.asList("1", "2"), System.out::println);
        }
    }

}
