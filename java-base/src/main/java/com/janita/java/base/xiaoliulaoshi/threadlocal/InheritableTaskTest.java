package com.janita.java.base.xiaoliulaoshi.threadlocal;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

/**
 * InheritableTaskTest
 *
 * @author zhucj
 * @since 20210729
 */
@SuppressWarnings("all")
public class InheritableTaskTest {

    private static InheritableThreadLocal<String> local = new InheritableThreadLocal<>();

    private static ExecutorService executor = Executors.newFixedThreadPool(2);

    public static void main(String[] args) throws InterruptedException {
        for (int i = 0; i < 10; i++) {
            final int outIndex = i;
            Thread.sleep(10);
            new Thread() {
                public void run() {
                    local.set("task____" + outIndex);
                    for (int i = 0; i < 3; i++) {
                        final int innerIndex = i;
                        executor.execute(new TaskInExecutorPool() {
                            @Override
                            public void runTask() {
                                System.out.println(Thread.currentThread().getName() + " outIndex ==== " + outIndex + "get_innerIndex" + innerIndex + ":" + local.get());
                            }
                        });
                    }
                }
            }.start();
        }
        executor.shutdown();
    }
}