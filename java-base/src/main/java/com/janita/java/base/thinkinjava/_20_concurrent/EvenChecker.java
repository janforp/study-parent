package com.janita.java.base.thinkinjava._20_concurrent;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

/**
 * EvenChecker
 *
 * @author zhucj
 * @since 20200528
 */
public class EvenChecker implements Runnable {

    private IntGenerator generator;

    private final int id;

    public EvenChecker(IntGenerator generator, int ident) {
        this.generator = generator;
        this.id = ident;
    }

    @Override
    public void run() {
        while (!generator.isCanceled()) {
            //同时有很多线程在执行这个对象的这个方法
            int val = generator.next();
            if (val % 2 != 0) {
                System.out.println(val + " not even !");
                generator.cancel();
            }
        }
    }

    public static void test(IntGenerator generator, int count) {

        System.out.println("Press Control-C to exit");
        ExecutorService exec = Executors.newCachedThreadPool();
        for (int i = 0; i < count; i++) {
            exec.execute(new EvenChecker(generator, i));
        }
        exec.shutdown();
    }

    public static void test(IntGenerator generator) {
        test(generator, 10);
    }

}
