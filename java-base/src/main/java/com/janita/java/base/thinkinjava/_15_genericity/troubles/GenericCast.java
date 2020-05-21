package com.janita.java.base.thinkinjava._15_genericity.troubles;

import apple.laf.JRSUIConstants;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.util.List;

/**
 * 类说明：GenericCast
 *
 * @author zhucj
 * @since 20200528
 */
public class GenericCast {

    private static final int SIZE = 11;

    public static void main(String[] args) {
        FixedSizeStack<String> fixedSizeStack = new FixedSizeStack<>(SIZE);
        for (String s : "A B C D E F G H I J K".split(" ")) {
            fixedSizeStack.push(s);
        }

        for (int i = 0; i < SIZE; i++) {
            String pop = fixedSizeStack.pop();
            System.out.println(pop);
        }
    }
}

class FixedSizeStack<T> {

    private int index = 0;

    private Object[] storage;

    public FixedSizeStack(int size) {
        storage = new Object[size];
    }

    public void push(T item) {
        storage[index++] = item;
    }

    public T pop() {
        return (T) storage[--index];
    }
}

class NeedCasting {

    public void f(String[] args) throws IOException, ClassNotFoundException {

        ObjectInputStream objectInputStream = new ObjectInputStream(new FileInputStream(args[0]));
        List<FixedSizeStack> o = (List<FixedSizeStack>) objectInputStream.readObject();
    }
}

class ClassCasting {

    public void f(String[] args) throws IOException, ClassNotFoundException {
        ObjectInputStream objectInputStream = new ObjectInputStream(new FileInputStream(args[0]));
        List<FixedSizeStack> o = List.class.cast(objectInputStream.readObject());
    }
}