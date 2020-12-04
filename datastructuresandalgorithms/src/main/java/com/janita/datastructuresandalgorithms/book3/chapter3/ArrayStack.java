package com.janita.datastructuresandalgorithms.book3.chapter3;

import com.sun.tools.javac.util.Pair;
import org.junit.Assert;
import org.junit.Test;

import java.util.NoSuchElementException;

/**
 * ArrayStack
 *
 * @author zhucj
 * @since 20201224
 */
@SuppressWarnings("all")
public class ArrayStack<T> {

    private static final int DEFAULT_CAPACITY = 2;

    private int topOfStack;

    private T[] theArray;

    public ArrayStack() {
        theArray = (T[]) new Object[DEFAULT_CAPACITY];
        topOfStack = -1;
    }

    public boolean push(T x) {
        extendArray();
        theArray[++topOfStack] = x;
        return true;
    }

    public T pop() {
        if (topOfStack == -1) {
            throw new NoSuchElementException();
        }
        T item = theArray[topOfStack];
        theArray[topOfStack] = null;
        topOfStack--;
        return item;
    }

    private void extendArray() {
        int length = theArray.length;
        //空间还够添加至少一个元素,则不扩容
        if (topOfStack < length - 1) {
            return;
        }
        T[] oldArray = theArray;
        theArray = (T[]) new Object[length * 2];
        for (int i = 0; i < oldArray.length; i++) {
            theArray[i] = oldArray[i];
        }
    }

    @Test
    public void test() {
        ArrayStack<Integer> stack = new ArrayStack<>();
        NoSuchElementException e = null;
        try {
            Integer pop = stack.pop();
        } catch (NoSuchElementException exception) {
            e = exception;
        }
        Assert.assertNotNull(e);
        stack.push(1);
        Integer pop = stack.pop();
        Assert.assertEquals(1, (int) pop);
        stack.push(2);
        stack.push(3);
        stack.push(4);
        stack.push(5);
        pop = stack.pop();
        Assert.assertEquals(5, (int) pop);
        stack.pop();
        stack.pop();
        pop = stack.pop();
        Assert.assertEquals(2, (int) pop);
        //抛出异常
        stack.push(2);
        stack.push(3);
        stack.push(4);
        stack.push(5);
        stack.push(2);
        stack.push(3);
        stack.push(4);
        stack.push(5);
        stack.push(2);
        stack.push(3);
        stack.push(4);
        stack.push(5);
    }

    @Test
    public void bracketTest() {
        String brackets = "[()]";

    }

    private static final Pair<String, String> yuan = Pair.of("(", ")");

    private static final Pair<String, String> hua = Pair.of("{", "}");

    private static final Pair<String, String> zhong = Pair.of("[", "]");

    private static boolean isLeft(String bracket) {
        if (yuan.fst.equals(bracket)) {
            return true;
        }
        if (yuan.fst.equals(bracket)) {
            return true;
        }
        if (yuan.fst.equals(bracket)) {
            return true;
        }
        return false;
    }
}