package javaapi;

import java.util.concurrent.CountDownLatch;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

/**
 * 类说明：
 * 一个CountDownLatch实例是不能重复使用的，也就是说它是一次性的，锁一经被打开就不能再关闭使用了，如果想重复使用，请考虑使用CyclicBarrier。
 *
 * @author zhucj
 * @since 20200423
 */
@SuppressWarnings("all")
public class CountDownLatchTest {

    /**
     * main
     *
     * @param args args
     * @throws InterruptedException InterruptedException
     */
    public static void main(String[] args) throws InterruptedException {
        /**
         * 开始的倒数锁
         */
        final CountDownLatch begin = new CountDownLatch(2);

        /**
         * 结束的倒数锁
         */
        final CountDownLatch end = new CountDownLatch(10);

        /**
         * 10名选手
         */
        final ExecutorService executor = Executors.newFixedThreadPool(10);

        for (int index = 0; index < 10; index++) {
            final int NO = index + 1;
            Runnable run = new Runnable() {
                @Override
                public void run() {
                    try {
                        //等待直到当前计数器为0的时候
                        begin.await();
                        Thread.sleep((long) (Math.random() * 10000));
                        System.out.println("No. " + NO + " 选手到达终点");
                    } catch (InterruptedException e) {
                        //empty
                    } finally {
                        //每一个选手到达终点，计数器 -1
                        end.countDown();
                    }
                }
            };
            executor.submit(run);
        }
        System.out.println("比赛开始 !");
        //倒计时，开始
        begin.countDown();//1
        begin.countDown();//0
        //等待end计数器到0，即所有选手都到的终点，比赛才结束
        end.await();
        System.out.println("所有选手到达终点，比赛结束");
        executor.shutdown();
    }
}
