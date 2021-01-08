package com.janita.java.base.concurrent;

/**
 * ThreadLocalTest
 *
 * @author zhucj
 * @since 20210128
 */
public class ThreadLocalTest {

    /**
     * 可能的结果:
     *
     * 线程3
     * null
     * null
     *
     * 线程1
     * A
     * 1
     *
     * 线程2
     * B
     * 2
     *
     * @param args
     */
    public static void main(String[] args) {

        final ThreadLocal<String> threadLocal1 = new ThreadLocal<>();
        final ThreadLocal<Integer> threadLocal2 = new ThreadLocal<>();

        new Thread(() -> {
            threadLocal1.set("A");
            threadLocal2.set(1);
            try {
                Thread.sleep(1000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            System.out.println(Thread.currentThread().getName());
            System.out.println(threadLocal1.get());
            System.out.println(threadLocal2.get());
            System.out.println();
        }, "线程1").start();

        new Thread(() -> {
            threadLocal1.set("B");
            threadLocal2.set(2);
            try {
                Thread.sleep(1000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            System.out.println(Thread.currentThread().getName());
            System.out.println(threadLocal1.get());
            System.out.println(threadLocal2.get());
            System.out.println();
        }, "线程2").start();

        new Thread(() -> {
            System.out.println(Thread.currentThread().getName());
            System.out.println(threadLocal1.get());
            System.out.println(threadLocal2.get());
            System.out.println();
        }, "线程3").start();
    }
}
