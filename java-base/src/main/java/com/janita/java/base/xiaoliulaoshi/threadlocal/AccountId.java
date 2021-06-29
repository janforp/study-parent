package com.janita.java.base.xiaoliulaoshi.threadlocal;

import java.util.concurrent.TimeUnit;
import java.util.concurrent.atomic.AtomicInteger;

/**
 * AccountId
 *
 * @author zhucj
 * @since 20210128
 */
public class AccountId {

    private static final AtomicInteger ACCOUNT_ID_GENERATOR = new AtomicInteger(0);

    private static final ThreadLocal<Integer> ACCOUNT_ID_CACHE
            = ThreadLocal.withInitial(() -> ACCOUNT_ID_GENERATOR.addAndGet(1000));

    public static int get() {
        return ACCOUNT_ID_CACHE.get();
    }

    public static void main(String[] args) throws InterruptedException {
        RunnableTask task = new RunnableTask();
        Thread t1 = new Thread(task, "线程1");
        t1.start();
        TimeUnit.MILLISECONDS.sleep(100);
        Thread t2 = new Thread(task, "线程2");
        t2.start();
        TimeUnit.MILLISECONDS.sleep(100);
        Thread t3 = new Thread(task, "线程3");
        t3.start();
        TimeUnit.MILLISECONDS.sleep(100);
    }

    static class RunnableTask implements Runnable {

        @Override
        public void run() {
            try {
                System.out.println("当前线程名称为：        "
                        + Thread.currentThread().getName()
                        + "，分配的线程ID为：      "
                        + ACCOUNT_ID_CACHE.get());
            } finally {
                ACCOUNT_ID_CACHE.remove();
            }
        }
    }
}