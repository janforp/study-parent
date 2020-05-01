package com.janita.java.base.concurrent;

import java.util.concurrent.ArrayBlockingQueue;
import java.util.concurrent.BlockingQueue;

/**
 * 类说明：LogTest
 *
 * @author zhucj
 * @since 20200423
 */
public class LogTest {

    public static void main(String[] args) throws InterruptedException {
        BlockingQueue<String> queue = new ArrayBlockingQueue<>(4);
        for (int i = 0; i < 4; i++) {
            new Thread(() -> {
                while (true) {
                    try {
                        String log = queue.take();
                        parseLog(log);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }

                }
            }).start();
        }

        System.out.println("begin:"+(System.currentTimeMillis()/1000));
        for (int i = 0; i < 16; i++) {
            final String log = " + " + (i + 1);
            queue.put(log);
        }
    }

    private static void parseLog(String log) {
        System.out.println(log + " ：" + (System.currentTimeMillis()/1000));
        try {
            Thread.sleep(1000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
}
