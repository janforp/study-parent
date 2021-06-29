package com.janita.java.base.xiaoliulaoshi.threadlocal;

import java.util.concurrent.TimeUnit;

/**
 * InheritableThreadLocalTest
 *
 * @author zhucj
 * @since 20210629
 */
public class InheritableThreadLocalTest {

    private static ThreadLocal<String> threadLocal = new InheritableThreadLocal<>();

    public static void main(String[] args) throws InterruptedException {
        threadLocal.set("mainVal");
        new Thread(() -> System.out.println("子线程中的本地变量为:" + threadLocal.get())).start();
        TimeUnit.MILLISECONDS.sleep(1000);
        System.out.println("main线程中的本地变量为:" + threadLocal.get());
    }
}
