package com.janita.java.base.concurrent;

import java.util.concurrent.SynchronousQueue;

/**
 * TransferQueueDemo01
 *
 * @author zhucj
 * @since 20210225
 */
@SuppressWarnings("all")
public class TransferQueueDemo01 {

    public static void main(String[] args) throws InterruptedException {

        /**
         * head(dummy) /tail 同一个节点
         */
        SynchronousQueue<Integer> queue = new SynchronousQueue<>(true);

        Thread thread1 = new Thread(() -> {
            try {
                queue.put(1);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        });
        thread1.start();

        /**
         * head(dummy) /tail 同一个节点
         */

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

        Thread thread3 = new Thread(() -> {
            try {
                System.out.println(queue.take());
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        });
        thread3.start();
    }
}

