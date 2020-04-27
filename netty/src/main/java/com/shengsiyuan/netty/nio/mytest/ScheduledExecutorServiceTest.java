package com.shengsiyuan.netty.nio.mytest;

import java.time.LocalDateTime;
import java.util.Random;
import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.ScheduledFuture;
import java.util.concurrent.TimeUnit;

/**
 * 类说明：ScheduledExecutorServiceTest
 *
 * @author zhucj
 * @since 20200423
 */
public class ScheduledExecutorServiceTest {

    static class BeeperController {

        private final ScheduledExecutorService scheduler = Executors.newScheduledThreadPool(1);

        /**
         * 定时任务，没10s进行一次
         * 启动之后60s停止
         */
        public void beepForAnHour() {
            Runnable beeper = () -> {
                System.out.println("【喇叭1】时间：" + LocalDateTime.now() + "： beeper");
                if (new Random().nextInt() % 2 == 0) {
                    try {
                        Thread.sleep(20 * 1000);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                }
            };
            scheduler.scheduleAtFixedRate(beeper, 10, 10, TimeUnit.SECONDS);
            scheduler.schedule(() -> {
                System.out.println("【喇叭1】停止时间：" + LocalDateTime.now());
                scheduler.shutdown();
            }, 60, TimeUnit.SECONDS);
        }

        /**
         * 定时任务，没10s进行一次
         * 启动之后60s停止
         */
        public ScheduledFuture<?> beepForAnHourAndReturn() {
            Runnable beeper = () -> System.out.println("【喇叭2】时间：" + LocalDateTime.now() + "： beeper");
            scheduler.scheduleAtFixedRate(beeper, 10, 10, TimeUnit.SECONDS);
            return scheduler.schedule(() -> {
                System.out.println("【喇叭2】停止时间：" + LocalDateTime.now());
                scheduler.shutdown();
            }, 60, TimeUnit.SECONDS);
        }
    }

    public static void main(String[] args) throws InterruptedException {
        System.out.println("【喇叭1】启动时间：" + LocalDateTime.now());
        BeeperController controller = new BeeperController();
        controller.beepForAnHour();

//        System.out.println("【喇叭2】启动时间：" + LocalDateTime.now());
//        controller = new BeeperController();
//        ScheduledFuture<?> future = controller.beepForAnHourAndReturn();
//        long delay = future.getDelay(TimeUnit.SECONDS);
//        boolean cancelled = future.isCancelled();
//        boolean done = future.isDone();
//        System.out.println(delay + "," + cancelled + "," + done);
//
//        Thread.sleep(60 * 1000);
//
//        delay = future.getDelay(TimeUnit.SECONDS);
//        cancelled = future.isCancelled();
//        done = future.isDone();
//        System.out.println(delay + "," + cancelled + "," + done);
    }
}
