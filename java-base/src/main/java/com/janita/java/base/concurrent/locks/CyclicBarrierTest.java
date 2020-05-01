package com.janita.java.base.concurrent.locks;

import java.util.concurrent.BrokenBarrierException;
import java.util.concurrent.CyclicBarrier;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

/**
 * 类说明：CyclicBarrierTest
 *
 * @author zhucj
 * @since 20200423
 */
public class CyclicBarrierTest {

    public static void main(String[] args) {

        ExecutorService threadPool = Executors.newCachedThreadPool();

        CyclicBarrier cyclicBarrier = new CyclicBarrier(3);

        for (int i = 0; i < 3; i++) {

            Runnable runnable = () -> {
                String name = Thread.currentThread().getName();

                try {
                    Thread.sleep((long)(Math.random()*10000));
                    System.out.println("线程 " + name + " 即将叨叨集合地点1，当前已经有 "
                            + (cyclicBarrier.getNumberWaiting() + 1)
                            + " 个线程已经到达地点1，" + (cyclicBarrier.getNumberWaiting() == 2
                            ? "都到齐了，触发去地点2" : "正在等候"));

                    //地点1
                    cyclicBarrier.await();

                    Thread.sleep((long)(Math.random()*10000));
                    System.out.println("线程 " + name + " 即将叨叨集合地点2，当前已经有 "
                            + (cyclicBarrier.getNumberWaiting() + 1)
                            + " 个线程已经到达地点2，" + (cyclicBarrier.getNumberWaiting() == 2
                            ? "都到齐了，触发去地点3" : "正在等候"));

                    //地点2
                    cyclicBarrier.await();

                    Thread.sleep((long)(Math.random()*10000));
                    System.out.println("线程 " + name + " 即将叨叨集合地点1，当前已经有 "
                            + (cyclicBarrier.getNumberWaiting() + 1)
                            + " 个线程已经到达地点3，" + (cyclicBarrier.getNumberWaiting() == 2
                            ? "都到齐了" : "都到齐了"));

                    //地点3
                    cyclicBarrier.await();

                } catch (InterruptedException | BrokenBarrierException e) {
                    e.printStackTrace();
                }
            };
            threadPool.execute(runnable);
        }
    }
}
