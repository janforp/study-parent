package com.janita.java.base.concurrent.locks;

import java.util.concurrent.CountDownLatch;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

/**
 * 类说明：CountdownLatchTest
 *
 * @author zhucj
 * @since 20200423
 */
public class CountdownLatchTest {

    public static void main(String[] args) throws InterruptedException {

        ExecutorService threadPool = Executors.newCachedThreadPool();

        //该计数器下命令之后子线程才开始
        CountDownLatch orderLatch = new CountDownLatch(1);
        //子线程完成一个就减去一个
        CountDownLatch answerLatch = new CountDownLatch(3);

        for (int i = 0; i < 3; i++) {
            Runnable runnable = () -> {

                try {
                    System.out.println("线程" + Thread.currentThread().getName() + "正准备接受命令");
                    //等待 orderLatch 计数器归零之后该线程才往下走
                    orderLatch.await();

                    Thread.sleep((long)(Math.random()*10000));
                    System.out.println("线程" + Thread.currentThread().getName() + "回应命令处理结果");
                }catch (Exception e) {
                    e.printStackTrace();
                }finally {
                    //完成
                    answerLatch.countDown();
                }
            };
            threadPool.execute(runnable);
        }

        Thread.sleep((long)(Math.random()*10000));
        System.out.println("线程" + Thread.currentThread().getName() + "即将发布命令");

        //主线程，发布命令
        orderLatch.countDown();

        System.out.println("线程" + Thread.currentThread().getName() + "已发送命令，正在等待结果");

        //等待所有线程都完成
        answerLatch.await();

        System.out.println("线程" + Thread.currentThread().getName() + "已收到所有响应结果");

        //所有任务都完成了，线程池也可以关了
        threadPool.shutdown();
    }
}
