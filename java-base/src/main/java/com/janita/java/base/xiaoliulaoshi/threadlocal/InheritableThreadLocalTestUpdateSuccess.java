package com.janita.java.base.xiaoliulaoshi.threadlocal;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

/**
 * InheritableThreadLocalTestUpdateSuccess
 *
 * @author zhucj
 * @since 20210729
 */
@SuppressWarnings("all")
public class InheritableThreadLocalTestUpdateSuccess {

    static final ThreadLocal<Integer> inheritableThreadLocal = new InheritableThreadLocal<Integer>();

    static ExecutorService pool = Executors.newFixedThreadPool(2);

    static class MainThread extends Thread {

        private int index;

        public MainThread(int index) {
            this.index = index;
        }

        @Override
        public void run() {
            inheritableThreadLocal.set(index);
            pool.execute(new Runnable() {
                // 这里的 run 方法是由线程池中的线程执行的
                @Override
                public void run() {
                    System.out.println("child inheritableThreadLocal: " + inheritableThreadLocal.get());
                }
            });
        }
    }

    public static void main(String[] args) throws InterruptedException {
        for (int i = 0; i < 100; i++) {
            Thread.sleep(10);
            new InheritableThreadLocalTestUpdateFail.MainThread(i).start();
        }
    }
}
