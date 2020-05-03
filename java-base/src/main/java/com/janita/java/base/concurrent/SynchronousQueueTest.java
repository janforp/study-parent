package com.janita.java.base.concurrent;

import java.util.concurrent.BlockingQueue;
import java.util.concurrent.Semaphore;
import java.util.concurrent.SynchronousQueue;

/**
 * 类说明：10个线程，按日志的顺序从 0 到 9 的顺序消费这些消息，至于是哪个线程消费的不关心，只需要按顺序消费
 *
 * @author zhucj
 * @since 20200423
 */
public class SynchronousQueueTest {

    public static void main(String[] args) throws InterruptedException {

        final Semaphore semaphore = new Semaphore(1);

        BlockingQueue<String> queue = new SynchronousQueue<>();

        for (int i = 0; i < 10; i++) {
            new Thread(() -> {
                try {
                    semaphore.acquire();
                    String input = queue.take();
                    String output = TestDo.doSome(input);
                    System.out.println(Thread.currentThread().getName() + ":" + output);
                    semaphore.release();
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }).start();
        }

        System.out.println("begin:" + (System.currentTimeMillis() / 1000));
        //这行不能改动
        for (int i = 0; i < 10; i++) {
            //这行不能改动
            String input = i + "";
            //SynchronousQueue 如果没人 take 无法 put
            queue.put(input);
        }
    }

    static class TestDo {

        public static String doSome(String input) {

            try {

                Thread.sleep(1000);

            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            return input + ":" + (System.currentTimeMillis() / 1000);
        }
    }

}