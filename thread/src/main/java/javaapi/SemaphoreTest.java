package javaapi;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Semaphore;

/**
 * 类说明：下面的例子只允许5个线程同时进入执行acquire()和release()之间的代码：
 *
 * @author zhucj
 * @since 20200423
 */
@SuppressWarnings("all")
public class SemaphoreTest {

    /**
     * main
     *
     * @param args args
     */
    public static void main(String[] args) {

        ExecutorService exector = Executors.newCachedThreadPool();

        //只能5个线程同时访问
        final Semaphore semaphore = new Semaphore(5);
        //模拟20个客户端访问
        for (int i = 0; i < 20; i++) {
            final int NO = i;
            Runnable run = new Runnable() {
                @Override
                public void run() {
                    try {
                        semaphore.acquire();
                        System.out.println(NO + " 获取许可,进去处理事情");
                        Thread.sleep((long) (Math.random() * 10000));
                        //访问完后，释放
                        System.out.println(NO + " 处理完毕");
                        semaphore.release();
                    }catch (InterruptedException e){

                    }
                }
            };
            exector.submit(run);
        }
        exector.shutdown();
    }
}
