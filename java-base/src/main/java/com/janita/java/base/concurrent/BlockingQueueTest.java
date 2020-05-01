package com.janita.java.base.concurrent;

import java.util.concurrent.ArrayBlockingQueue;
import java.util.concurrent.BlockingQueue;

/**
 * 类说明：BlockingQueueTest
 *
 * @author zhucj
 * @since 20200423
 */
public class BlockingQueueTest {

    public static void main(String[] args) {

        final BlockingQueue<Integer> queue = new ArrayBlockingQueue<>(3);

        for (int i = 0; i < 2; i++) {
            new Thread(() -> {
                String name = Thread.currentThread().getName();
                while (true) {
                    try {
                        Thread.sleep(2000);
                        System.out.println(name + " 准备 放 数据");
                        queue.put(1);
                        System.out.println(name + " 已经 放 了数据，队列内目前有 " + queue.size() + " 个数据");
                    }catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                }
            }).start();
        }

        new Thread(() -> {
            String name = Thread.currentThread().getName();
            while (true) {
                try {
                    Thread.sleep(100);
                    System.out.println(name + " 准备 取 数据");
                    queue.take();
                    System.out.println(name + " 已经 取 走数据，队列目前有 " +  queue.size() + " 个数据");
                    System.out.println();
                }catch(InterruptedException e) {
                    e.printStackTrace();
                }
            }
        }).start();
    }
}
