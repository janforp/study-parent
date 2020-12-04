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
public class MyCircleArrayQueue<T> implements MyQueue<T> {

    private T[] theArray;

    private int currentSize;

    /**
     * 下一个出队元素下标
     */
    private int front;

    /**
     * 下个入队元素下标
     */
    private int back;

    public MyCircleArrayQueue() {
        theArray = (T[]) new Object[10];
        currentSize = 0;
        front = back = 0;
    }

    @Override
    public boolean enqueue(T x) {
        if (currentSize >= theArray.length) {
            throw new ArrayIndexOutOfBoundsException();
        }
        theArray[back] = x;
        currentSize++;
        back++;
        if (back > theArray.length - 1) {
            back = 0;
        }
        return true;
    }

    @Override
    public T dequeue() {
        if (isEmpty()) {
            throw new NoSuchElementException();
        }
        currentSize--;
        T value = theArray[front];
        front++;
        if (front >= theArray.length) {
            front = 0;
        }
        return value;
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
        MyQueue<Integer> queue = new MyCircleArrayQueue<>();
        queue.enqueue(1);
        Integer dequeue = queue.dequeue();
        Assert.assertEquals(1, (int) dequeue);
        Assert.assertTrue(queue.isEmpty());
        queue = new MyCircleArrayQueue<>();
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

    @Test
    public void test2() {
        MyQueue<Integer> queue = new MyCircleArrayQueue<>();
        queue.enqueue(1);
        queue.enqueue(2);
        queue.enqueue(3);
        queue.enqueue(4);
        queue.enqueue(5);
        queue.enqueue(6);
        queue.enqueue(7);
        queue.enqueue(8);
        queue.enqueue(9);
        queue.enqueue(10);
        Assert.assertEquals(10, queue.size());
        ArrayIndexOutOfBoundsException exception = null;
        try {
            queue.enqueue(11);
        } catch (ArrayIndexOutOfBoundsException e) {
            exception = e;
        }
        Assert.assertNotNull(exception);
        //出去一个即可进去一个
        Integer dequeue = queue.dequeue();
        Assert.assertEquals(1, (int) dequeue);
        Assert.assertEquals(9, queue.size());
        queue.enqueue(11);
        Assert.assertEquals(10, queue.size());

        Assert.assertEquals(2, (int) queue.dequeue());
        Assert.assertEquals(3, (int) queue.dequeue());
        Assert.assertEquals(4, (int) queue.dequeue());
        Assert.assertEquals(5, (int) queue.dequeue());
        Assert.assertEquals(6, (int) queue.dequeue());
        Assert.assertEquals(7, (int) queue.dequeue());
        Assert.assertEquals(8, (int) queue.dequeue());
        Assert.assertEquals(9, (int) queue.dequeue());
        Assert.assertEquals(10, (int) queue.dequeue());
        Assert.assertEquals(11, (int) queue.dequeue());
        Assert.assertEquals(0, (int) queue.size());
    }
}
