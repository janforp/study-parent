package com.janita.java.base.thinkinjava._20_concurrent;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;

/**
 * SleepingTask
 *
 * @author zhucj
 * @since 20200528
 */
public class SleepingTask extends LiftOff {

    @Override
    public void run() {
        while (countDown-- > 0) {
            System.out.println(status());
            try {
                TimeUnit.MILLISECONDS.sleep(100);
            } catch (InterruptedException e) {
                System.out.println(e);
            }
        }
    }

    public static void main(String[] args) {
        ExecutorService exec = Executors.newCachedThreadPool();
        for (int i = 0; i < 5; i++) {
            exec.execute(new SleepingTask());
        }
        exec.shutdown();
    }
}
