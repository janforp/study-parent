package com.janita.java.base.thinkinjava._15_genericity.erased;

import java.lang.reflect.Array;

/**
 * 类说明：GenericArray
 *
 * @author zhucj
 * @since 20200528
 */
public class GenericArray<T> {

    /**
     * 他的运行时类型只能是 Object[]
     */
    private T[] array;

    @SuppressWarnings("unchecked")
    public GenericArray(int sz) {
        array = (T[]) new Object[sz];
    }

    public void put(int index, T item) {
        array[index] = item;
    }

    public T get(int index) {
        return array[index];
    }

    public T[] rep() {
        return array;
    }

    public static void main(String[] args) {

        GenericArray<Integer> genericArray = new GenericArray<>(10);
        genericArray.put(2, 2);

        Object[] objects = genericArray.rep();

        //ClassCastException !!!!!!!!!!!
        //T[] array = new T[] 这样是不行d
        Integer[] rep = genericArray.rep();
    }
}

class GenericArray2<T> {

    private Object[] array;

    public GenericArray2(int sz) {
        array = new Object[sz];
    }

    public void put(int index, T item) {
        array[index] = item;
    }

    @SuppressWarnings("unchecked")
    public T get(int index) {
        return (T) array[index];
    }

    @SuppressWarnings("unchecked")
    public T[] rep() {
        // Warning: unchecked cast
        return (T[]) array;
    }

    public static void main(String[] args) {
        GenericArray2<Integer> gai = new GenericArray2<>(10);
        for (int i = 0; i < 10; i++) {
            gai.put(i, i);
        }
        for (int i = 0; i < 10; i++) {
            Integer integer = gai.get(i);
            System.out.print(integer + " ");
        }
        System.out.println();
        try {
            Integer[] ia = gai.rep();
        } catch (Exception e) {
            //ClassCastException !!!!!!!!!!!
            System.out.println(e);
        }
    }
}

/**
 * 这个方案不会 ClassCastException
 *
 * @param <T>
 */
class GenericArrayWithTypeToken<T> {

    /**
     * 该数组类型确实会在创建空数组的时候就会变成相应的类型
     */
    private T[] array;

    @SuppressWarnings("unchecked")
    public GenericArrayWithTypeToken(Class<T> type, int sz) {
        //从擦除中恢复了类型
        array = (T[]) Array.newInstance(type, sz);
    }

    public void put(int index, T item) {
        array[index] = item;
    }

    public T get(int index) {
        return array[index];
    }

    public T[] rep() {
        return array;
    }

    public static void main(String[] args) {
        GenericArrayWithTypeToken<Integer> gai = new GenericArrayWithTypeToken<>(Integer.class, 10);
        Integer[] ia = gai.rep();
    }
}
