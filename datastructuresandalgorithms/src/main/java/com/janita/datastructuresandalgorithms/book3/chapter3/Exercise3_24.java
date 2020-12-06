package com.janita.datastructuresandalgorithms.book3.chapter3;

import org.junit.Assert;
import org.junit.Test;

import java.util.NoSuchElementException;

/**
 * Exercise3_21
 *
 * @author zhucj
 * @since 20201224
 */
public class Exercise3_24 {

    @Test
    public void test() {
        ShareStack<Integer> stack = new ShareStack<>(3);
        stack.push1(1);
        stack.push1(2);
        stack.push1(3);
        Assert.assertEquals(3, stack.size1());

        IndexOutOfBoundsException exception1 = null;
        try {
            stack.push1(4);
        } catch (IndexOutOfBoundsException e) {
            exception1 = e;
        }
        Assert.assertNotNull(exception1);

        stack.push2(11);
        stack.push2(12);
        stack.push2(13);
        Assert.assertEquals(3, stack.size2());

        IndexOutOfBoundsException exception2 = null;
        try {
            stack.push2(14);
        } catch (IndexOutOfBoundsException e) {
            exception2 = e;
        }
        Assert.assertNotNull(exception2);

        Assert.assertEquals(3, (int) stack.pop1());
        Assert.assertEquals(2, (int) stack.pop1());
        Assert.assertEquals(1, (int) stack.pop1());
        Assert.assertEquals(13, (int) stack.pop2());
        Assert.assertEquals(12, (int) stack.pop2());
        Assert.assertEquals(11, (int) stack.pop2());

        NoSuchElementException noSuchElementException1 = null;
        try {
            stack.pop1();
        } catch (NoSuchElementException e) {
            noSuchElementException1 = e;
        }
        Assert.assertNotNull(noSuchElementException1);

        NoSuchElementException noSuchElementException2 = null;
        try {
            stack.pop2();
        } catch (NoSuchElementException e) {
            noSuchElementException2 = e;
        }
        Assert.assertNotNull(noSuchElementException2);

        stack.push1(1);
        stack.push2(2);
        Assert.assertEquals(1, stack.size1());
        Assert.assertEquals(1, stack.size2());

        Assert.assertEquals(1, (int) stack.pop1());
        Assert.assertEquals(2, (int) stack.pop2());

    }
}

class ShareStack<T> {

    private T[] arr;

    private int capacity;

    private int theSize1;

    private int theSize2;

    public ShareStack(int capacity) {
        this.capacity = capacity;
        arr = (T[]) new Object[capacity * 2];
        theSize1 = theSize2 = 0;
    }

    public boolean isEmpty1() {
        return size1() == 0;
    }

    public int size1() {
        return theSize1;
    }

    public void push1(T x) {
        if (theSize1 == capacity) {
            throw new IndexOutOfBoundsException();
        }
        arr[theSize1] = x;
        theSize1++;
    }

    public T pop1() {
        if (isEmpty1()) {
            throw new NoSuchElementException();
        }
        T re = arr[theSize1 - 1];
        theSize1--;
        return re;
    }

    public boolean isEmpty2() {
        return size2() == 0;
    }

    public int size2() {
        return theSize2;
    }

    public void push2(T x) {
        if (theSize2 == capacity) {
            throw new IndexOutOfBoundsException();
        }
        arr[2 * capacity - theSize2 - 1] = x;
        theSize2++;
    }

    public T pop2() {
        if (isEmpty2()) {
            throw new NoSuchElementException();
        }
        T re = arr[2 * capacity - theSize2];
        theSize2--;
        return re;
    }
}
