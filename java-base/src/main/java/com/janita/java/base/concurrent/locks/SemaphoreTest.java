package com.janita.java.base.concurrent.locks;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Semaphore;

/**
 * 类说明：SemaphoreTest
 *
 * @author zhucj
 * @since 20200423
 */
public class SemaphoreTest {

    public static void main(String[] args) {

        ExecutorService threadPool = Executors.newCachedThreadPool();

        //如果是 1 则可以实现同步的效果，通过另外一个构造器，可以实现公平性
        Semaphore semaphore = new Semaphore(3);

        for (int i = 0; i < 10; i++) {
            Runnable runnable = () -> {
                try {
                    semaphore.acquire();
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                System.out.println("线程 " + Thread.currentThread().getName()
                        + " 进入，当前已经有 " + (3 - semaphore.availablePermits()) + " 个并发");
                try {
                    Thread.sleep((long) (Math.random() * 10000));
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                System.out.println("线程 " + Thread.currentThread().getName() + " 即将离开");
                semaphore.release();
                //下面代码有时候执行不准确，因为其没有和上面的代码合成原子单元
                System.out.println("线程" + Thread.currentThread().getName() +
                        "已离开，当前已有" + (3 - semaphore.availablePermits()) + "个并发");
            };
            threadPool.execute(runnable);
        }
    }
}

class SemaphoreTest2 {

    public static void main(String[] args) throws InterruptedException {
        //如果是 1 则可以实现同步的效果，通过另外一个构造器，可以实现公平性
        Semaphore semaphore = new Semaphore(3);
        semaphore.acquire();
        semaphore.acquire();
        semaphore.acquire();

        //此处会阻塞
        semaphore.acquire();

        semaphore.acquire();
        semaphore.release();
        semaphore.release();
        semaphore.release();
        semaphore.release();
    }
}
