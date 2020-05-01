package com.janita.java.base.concurrent;

import java.util.concurrent.ArrayBlockingQueue;
import java.util.concurrent.BlockingQueue;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

/**
 * 类说明：使用阻塞队列的 put / take 的阻塞特性来实现2个线程交替执行
 *
 * @author zhucj
 * @since 20200423
 */
public class BlockingQueueCommunication {

    public static void main(String[] args) {

        ExecutorService threadPool = Executors.newFixedThreadPool(2);

        BlockingQueueCommunicationHelper helper = new BlockingQueueCommunicationHelper();
        Runnable leftThread = () -> {
            while (true) {
                try {
                    helper.leftTask();
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        };

        Runnable rightThread = () -> {
            while (true) {
                try {
                    helper.rightTask();
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        };

        threadPool.execute(leftThread);
        threadPool.execute(rightThread);
    }
}

class BlockingQueueCommunicationHelper {

    final BlockingQueue<Integer> left = new ArrayBlockingQueue<>(1);

    final BlockingQueue<Integer> right = new ArrayBlockingQueue<Integer>(1);

    //匿名构造方法，每次实例化对象的时候都会调用
    {
        try {
            //初始化的时候，left空，right满
            right.put(1);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    ;

    public void leftTask() throws InterruptedException {
        //首次，left 空，可以执行
        left.put(1);
        Thread.sleep(1000);
        for (int i = 0; i < 10; i++) {
            System.out.println("执行 left 任务 " + (i + 1));
        }
        //执行完毕之后，通知 right
        right.take();
    }

    public void rightTask() throws InterruptedException {
        right.put(1);
        Thread.sleep(1000);
        for (int i = 0; i < 5; i++) {
            System.out.println("执行 right 任务 " + (i + 1));
        }
        //执行完毕之后，通知 left
        left.take();
    }
}
