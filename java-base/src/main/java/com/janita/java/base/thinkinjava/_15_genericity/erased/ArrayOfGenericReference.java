package com.janita.java.base.thinkinjava._15_genericity.erased;

import java.util.Arrays;

/**
 * 类说明：ArrayOfGenericReference
 *
 * @author zhucj
 * @since 20200528
 */
class Generic<T> {

    T a;

    Generic(T a) {
        this.a = a;
    }

    @Override
    public String toString() {
        return "Generic{" +
                "a=" + a +
                '}';
    }
}

public class ArrayOfGenericReference {

    Generic<Integer>[] integerGenerics = new Generic[3];

    Generic<String>[] stringGenerics = new Generic[4];

    public static void main(String[] args) {
        ArrayOfGenericReference reference = new ArrayOfGenericReference();
        reference.integerGenerics[0] = new Generic<>(1);
        reference.integerGenerics[1] = new Generic<>(2);
        reference.integerGenerics[2] = new Generic<>(3);

        reference.stringGenerics[0] = new Generic<>("str1");
        reference.stringGenerics[1] = new Generic<>("str2");
        reference.stringGenerics[2] = new Generic<>("str3");
        //可以放入一个与泛型不一样的数据
        reference.stringGenerics[3] = new Generic(new Generic<>(222222L));

        System.out.println(reference);
    }

    @Override
    public String toString() {
        return "ArrayOfGenericReference{" +
                "integerGenerics=" + Arrays.toString(integerGenerics) +
                ", stringGenerics=" + Arrays.toString(stringGenerics) +
                '}';
    }
}

class ArrayOfGeneric {

    static final int SIZE = 10;

    static Generic<Integer>[] integerGenerics;

    static Generic<String>[] stringGenerics;

    public static void main(String[] args) {
        //        ClassCastException
        integerGenerics = (Generic<Integer>[]) new Object[SIZE];
        System.out.println(integerGenerics.getClass().getSimpleName());
        integerGenerics = new Generic[SIZE];
        System.out.println(integerGenerics.getClass().getSimpleName());
    }
}
