package com.janita.java.base.xiaoliulaoshi.threadlocal;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

/**
 * InheritableThreadLocalTestUpdateSuccess
 *
 * @author zhucj
 * @since 20210729
 */
public class InheritableThreadLocalTestUpdateSuccess {

    static final ThreadLocal<Integer> inheritableThreadLocal = new InheritableThreadLocal<Integer>();

    static ExecutorService pool = Executors.newFixedThreadPool(2);

    static class MainThread extends InheritableTask {

        private int index;

        public MainThread(int index) {
            this.index = index;
        }

        @Override
        public void runTask() {
            pool.execute(new InheritableTask() {
                @Override
                public void runTask() {
                    System.out.println("child inheritableThreadLocal: " + inheritableThreadLocal.get());
                }
            });
        }
    }

    public static void main(String[] args) throws InterruptedException {
        for (int i = 0; i < 100; i++) {
            inheritableThreadLocal.set(i);
            Thread.sleep(10);
            new InheritableThreadLocalTestUpdateFail.MainThread(i).start();
        }
    }
}
