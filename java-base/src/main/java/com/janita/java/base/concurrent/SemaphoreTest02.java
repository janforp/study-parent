package com.janita.java.base.concurrent;

import java.util.concurrent.Semaphore;
import java.util.concurrent.TimeUnit;

public class SemaphoreTest02 {

    public static void main(String[] args) throws InterruptedException {
        final Semaphore semaphore = new Semaphore(2, true);

        Thread tA = new Thread(() -> {
            try {
                semaphore.acquire();
                System.out.println("线程A获取通行证成功");
                TimeUnit.SECONDS.sleep(10);
            } catch (InterruptedException e) {
                //ignore
            } finally {
                semaphore.release();
            }
        });
        tA.start();

        //确保线程A已经执行
        TimeUnit.MILLISECONDS.sleep(200);

        Thread tB = new Thread(() -> {
            try {
                semaphore.acquire(2);
                System.out.println("线程B获取通行证成功");
            } catch (InterruptedException e) {
                //ignore
            } finally {
                semaphore.release(2);
            }
        });
        tB.start();

        //确保线程B已经执行
        TimeUnit.MILLISECONDS.sleep(200);

        Thread tC = new Thread(() -> {
            try {
                semaphore.acquire();
                System.out.println("线程C获取通行证成功");
            } catch (InterruptedException e) {
                //ignore
            } finally {
                semaphore.release();
            }
        });
        tC.start();
    }
}
