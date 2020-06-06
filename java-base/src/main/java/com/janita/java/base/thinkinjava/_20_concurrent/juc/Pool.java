package com.janita.java.base.thinkinjava._20_concurrent.juc;//: concurrency/Pool.java
// Using a Semaphore inside a Pool, to restrict
// the number of tasks that can use a resource.

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.Semaphore;

public class Pool<T> {

    private int size;

    private List<T> items = new ArrayList<T>();

    private volatile boolean[] checkedOut;

    private Semaphore available;

    /**
     * 实例化
     * 1.size
     * 2.checkedOut
     * 3.Semaphore available
     * 4.items填充对象
     *
     * @param classObject
     * @param size
     */
    public Pool(Class<T> classObject, int size) {
        this.size = size;
        checkedOut = new boolean[size];
        available = new Semaphore(size, true);
        // Load pool with objects that can be checked out:
        for (int i = 0; i < size; ++i) {
            try {
                // Assumes a default constructor:
                items.add(classObject.newInstance());
            } catch (Exception e) {
                throw new RuntimeException(e);
            }
        }
    }

    /**
     * 获取一个对象
     *
     * @return
     * @throws InterruptedException
     */
    public T checkOut() throws InterruptedException {
        //阻塞方法
        available.acquire();
        return this.getItem();
    }

    /**
     * 归还对象
     *
     * @param x
     */
    public void checkIn(T x) {
        if (this.releaseItem(x)) {
            available.release();
        }
    }

    /**
     * 获取一个对象
     *
     * @return
     */
    private synchronized T getItem() {
        for (int i = 0; i < size; ++i) {
            if (!checkedOut[i]) {
                checkedOut[i] = true;
                return items.get(i);
            }
        }
        return null; // Semaphore prevents reaching here
    }

    private synchronized boolean releaseItem(T item) {
        int index = items.indexOf(item);
        if (index == -1) {
            return false; // Not in the list
        }
        if (checkedOut[index]) {
            checkedOut[index] = false;
            return true;
        }
        return false; // Wasn't checked out
    }
} ///:~
