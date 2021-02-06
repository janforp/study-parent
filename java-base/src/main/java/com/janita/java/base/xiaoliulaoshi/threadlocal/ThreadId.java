package com.janita.java.base.xiaoliulaoshi.threadlocal;

import java.util.concurrent.atomic.AtomicInteger;

/**
 * ThreadId
 *
 * @author zhucj
 * @since 20210128
 */
public class ThreadId {

    // Atomic integer containing the next thread ID to be assigned
    private static final AtomicInteger nextId = new AtomicInteger(0);

    // Thread local variable containing each thread's ID
    private static final ThreadLocal<Integer> threadId = new ThreadLocal<Integer>() {
        @Override
        protected Integer initialValue() {
            return nextId.getAndIncrement();
        }
    };

    // Returns the current thread's unique ID, assigning it if necessary
    public static int get() {
        return threadId.get();
    }

    public static void main(String[] args) {
        System.out.println(Integer.toBinaryString(0x7fffffff));
        System.out.println(0x7fffffff);
        tttFor();
    }

    private static void tttFor() {
        for (int i = 0; i < 10; i++) {
            System.out.println(i);
        }
    }
}