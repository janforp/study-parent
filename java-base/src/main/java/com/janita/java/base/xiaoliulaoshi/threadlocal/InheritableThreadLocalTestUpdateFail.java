package com.janita.java.base.xiaoliulaoshi.threadlocal;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

/**
 * InheritableThreadLocalTestUpdate
 *
 * @author zhucj
 * @since 20210729
 */
@SuppressWarnings("all")
public class InheritableThreadLocalTestUpdateFail {

    static final ThreadLocal<Integer> inheritableThreadLocal = new InheritableThreadLocal<Integer>();

    static ExecutorService pool = Executors.newFixedThreadPool(2);

    public static void main(String[] args) throws InterruptedException {
        for (int i = 0; i < 100; i++) {
            final int index = i;
            Thread.sleep(10);
            new Thread() {
                @Override
                public void run() {
                    inheritableThreadLocal.set(index);
                    pool.execute(new Runnable() {
                        @Override
                        public void run() {
                            System.out.println("子线程获取到的值为: " + inheritableThreadLocal.get());
                        }
                    });
                }
            }.start();
        }
    }
}