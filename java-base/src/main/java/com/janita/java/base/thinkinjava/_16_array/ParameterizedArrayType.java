package com.janita.java.base.thinkinjava._16_array;

import java.util.ArrayList;
import java.util.List;

/**
 * 类说明：ParameterizedArrayType
 *
 * @author zhucj
 * @since 20200528
 */
class ClassParameter<T> {

    public T[] f(T[] arg) {
        return arg;
    }
}

class MethodParameter {

    public static <T> T[] f(T[] arg) {
        return arg;
    }
}

public class ParameterizedArrayType {

    public static void main(String[] args) {
        Integer[] ints = { 1, 2, 3, 4, 5 };
        Double[] doubles = { 1.1, 2.2, 3.3, 4.4, 5.5 };
        Integer[] ints2 = new ClassParameter<Integer>().f(ints);
        Double[] doubles2 = new ClassParameter<Double>().f(doubles);
        ints2 = MethodParameter.f(ints);
        doubles2 = MethodParameter.f(doubles);

        List<String>[] lists;

        //        lists=new List<String>[] {};
    }
}

class ArrayOfGenerics {

    @SuppressWarnings("unchecked")
    public static void main(String[] args) {
        List<String>[] ls;
        List[] la = new List[10];
        ls = (List<String>[]) la; // "Unchecked" warning
        ls[0] = new ArrayList<String>();
        // Compile-time checking produces an error:
        //! ls[1] = new ArrayList<Integer>();

        // The problem: List<String> is a subtype of Object
        Object[] objects = ls; // So assignment is OK
        // Compiles and runs without complaint:
        objects[1] = new ArrayList<Integer>();

        // However, if your needs are straightforward it is
        // possible to create an array of generics, albeit
        // with an "unchecked" warning:
        List<BerylliumSphere>[] spheres = (List<BerylliumSphere>[]) new List[10];
        for (int i = 0; i < spheres.length; i++) {
            spheres[i] = new ArrayList<BerylliumSphere>();
        }
    }
}

class ArrayOfGenericType<T> {

    T[] array; // OK

    @SuppressWarnings("unchecked")
    public ArrayOfGenericType(int size) {
        //array = new T[size]; // Illegal
        array = (T[]) new Object[size]; // "unchecked" Warning
    }

    // Illegal:
    //public <U> U[] makeArray() {
    //    return new U[10];
    //}
}