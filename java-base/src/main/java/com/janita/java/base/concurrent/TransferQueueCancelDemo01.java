package com.janita.java.base.concurrent;

import java.util.concurrent.SynchronousQueue;

/**
 * TransferQueueCancelDemo01
 *
 * @author zhucj
 * @since 20210225
 */
public class TransferQueueCancelDemo01 {

    public static void main(String[] args) throws InterruptedException {

        SynchronousQueue<Integer> queue = new SynchronousQueue<>(true);

        Thread thread1 = new Thread(() -> {
            try {
                queue.put(1);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        });
        thread1.start();

        Thread.sleep(1000);

        Thread thread2 = new Thread(() -> {
            try {
                queue.put(2);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        });
        thread2.start();

        Thread.sleep(1000);

        thread1.interrupt();
    }
}
