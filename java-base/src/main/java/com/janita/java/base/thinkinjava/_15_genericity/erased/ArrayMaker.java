package com.janita.java.base.thinkinjava._15_genericity.erased;

import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * 类说明：ArrayMaker
 *
 * @author zhucj
 * @since 20200528
 */
public class ArrayMaker<T> {

    private Class<T> kind;

    public ArrayMaker(Class<T> kind) {
        this.kind = kind;
    }

    @SuppressWarnings("unchecked")
    T[] create(int size) {
        //即可 kind 被存储为 Class<T>,擦除也意味着他实际被存储为 Class，没有任何参数。因此
        //当你在使用的时候，列入创建数组，Array.newInstance实际上并未拥有kind所蕴含的类型信息，因此
        //不会产生具体的结果，所以必须转型
        return (T[]) Array.newInstance(kind, size);
    }

    public static void main(String[] args) {
        ArrayMaker<String> stringArrayMaker = new ArrayMaker<>(String.class);
        String[] strings = stringArrayMaker.create(9);
        System.out.println(Arrays.toString(strings));
    }
}

class ListMaker<T> {

    List<T> create() {
        //如果这样会有警告
        //        return new ArrayList();
        return new ArrayList<>();
    }

    public static void main(String[] args) {
        ListMaker<String> listMaker = new ListMaker<>();
        List<String> stringList = listMaker.create();
    }
}

class FilledListMaker<T> {

    List<T> create(T t, int n) {

        List<T> list = new ArrayList<>();
        for (int i = 0; i < n; i++) {
            list.add(t);
        }
        return list;
    }

    public static void main(String[] args) {

        FilledListMaker<String> maker = new FilledListMaker<>();
        List<String> stringList = maker.create("world", 5);
        System.out.println(stringList);
    }
}