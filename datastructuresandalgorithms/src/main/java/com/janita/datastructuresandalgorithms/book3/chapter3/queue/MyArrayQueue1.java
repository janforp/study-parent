package com.janita.datastructuresandalgorithms.book3.chapter3.queue;

import org.junit.Assert;
import org.junit.Test;

import java.util.NoSuchElementException;

/**
 * MyArrayQueue1
 *
 * @author zhucj
 * @since 20201224
 */
@SuppressWarnings("all")
public class MyArrayQueue1<T> implements MyQueue<T> {

    private T[] theArray;

    private int currentSize;

    private int front;

    private int back;

    public MyArrayQueue1() {
        theArray = (T[]) new Object[10];
        currentSize = 0;
        front = back = 0;
    }

    @Override
    public boolean enqueue(T x) {
        if (currentSize >= theArray.length) {
            throw new ArrayIndexOutOfBoundsException();
        }
        if (back >= theArray.length - 1) {
            throw new ArrayIndexOutOfBoundsException();
        }
        theArray[back++] = x;
        currentSize++;
        return true;
    }

    @Override
    public T dequeue() {
        if (isEmpty()) {
            throw new NoSuchElementException();
        }
        if (front + 1 > back) {
            throw new ArrayIndexOutOfBoundsException();
        }
        currentSize--;
        return theArray[front++];
    }

    @Override
    public boolean isEmpty() {
        return currentSize == 0;
    }

    @Override
    public int size() {
        return currentSize;
    }

    @Test
    public void test1() {
        MyQueue<Integer> queue = new MyArrayQueue1<>();
        queue.enqueue(1);
        Integer dequeue = queue.dequeue();
        Assert.assertEquals(1, (int) dequeue);
        Assert.assertTrue(queue.isEmpty());
        queue = new MyArrayQueue1<>();
        queue.enqueue(2);
        queue.enqueue(3);
        queue.enqueue(4);
        Assert.assertEquals(3, queue.size());
        Assert.assertEquals(2, (int) queue.dequeue());
        Assert.assertEquals(3, (int) queue.dequeue());
        Assert.assertEquals(4, (int) queue.dequeue());
        NoSuchElementException exception = null;
        try {
            queue.dequeue();
        } catch (NoSuchElementException e) {
            exception = e;
        }
        Assert.assertNotNull(exception);
    }
}
