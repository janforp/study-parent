package com.janita.java.base.xiaoliulaoshi.threadlocal;

import java.util.concurrent.Executor;
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

    private static Executor executor = Executors.newFixedThreadPool(5);

    public static void main(String[] args) {
        for (int i = 0; i < 2; i++) {
            final int ab = i;
            new Thread() {
                public void run() {
                    local.set("task____" + ab);
                    for (int i = 0; i < 3; i++) {
                        final int a = i;
                        executor.execute(new InheritableTask() {
                            @Override
                            public void runTask() {
                                System.out.println(Thread.currentThread().getName() + "==== " + ab + "get_" + a + ":" + local.get());
                            }
                        });
                    }
                }
            }.start();
        }
    }
}