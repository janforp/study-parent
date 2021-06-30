package com.janita.java.base.xiaoliulaoshi.threadlocal;

import java.util.concurrent.Executor;
import java.util.concurrent.Executors;
import java.util.concurrent.atomic.AtomicInteger;

/**
 * ThreadPoolTest
 *
 * @author zhucj
 * @since 20210629
 */
public class ThreadPoolTest {

    private static Executor executor = Executors.newFixedThreadPool(5);

    private static AtomicInteger atomicInteger = new AtomicInteger(0);

    private static ThreadLocal<Integer> threadLocal = new InheritableThreadLocal<Integer>() {
        @Override
        protected Integer initialValue() {
            return atomicInteger.addAndGet(1000);
        }
    };

    public static void main(String[] args) {
        threadLocal.set(1000);
        testInheritableThreadLocal();
    }

    private static void testInheritableThreadLocal() {
        executor.execute(() -> System.out.println(threadLocal.get()));
        executor.execute(() -> System.out.println(threadLocal.get()));
        executor.execute(() -> System.out.println(threadLocal.get()));
        executor.execute(() -> System.out.println(threadLocal.get()));
        executor.execute(() -> System.out.println(threadLocal.get()));
        executor.execute(() -> System.out.println(threadLocal.get()));
        executor.execute(() -> System.out.println(threadLocal.get()));
        executor.execute(() -> System.out.println(threadLocal.get()));
        executor.execute(() -> System.out.println(threadLocal.get()));
        executor.execute(() -> System.out.println(threadLocal.get()));
        executor.execute(() -> System.out.println(threadLocal.get()));
    }
}
