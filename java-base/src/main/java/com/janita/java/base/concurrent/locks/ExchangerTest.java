package com.janita.java.base.concurrent.locks;

import java.util.concurrent.Exchanger;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

/**
 * 类说明：用于实现2个线程直接的数据交换,可以等待一定的时间
 *
 * @author zhucj
 * @since 20200423
 */
public class ExchangerTest {

    public static void main(String[] args) {

        ExecutorService threadPool = Executors.newCachedThreadPool();

        final Exchanger<String> exchanger = new Exchanger<>();

        threadPool.execute(() -> {
            try {
                String data = "钱";
                System.out.println("线程" + Thread.currentThread().getName() + "正在把数据 " + data +" 换出去");
                Thread.sleep((long)(Math.random()*10000));

                //交换
                String dataFromOtherThread = exchanger.exchange(data);
                System.out.println("线程" + Thread.currentThread().getName() + "换回的数据为 " + dataFromOtherThread);
            }catch (Exception e) {
                //ignore
            }
        });

        threadPool.execute(() -> {
            try {
                String data = "白粉";
                System.out.println("线程" + Thread.currentThread().getName() + "正在把数据 " + data +" 换出去");
                Thread.sleep((long)(Math.random()*10000));

                //交换
                String dataFromOtherThread = exchanger.exchange(data);
                System.out.println("线程" + Thread.currentThread().getName() + "换回的数据为 " + dataFromOtherThread);
            }catch (Exception e) {
                //ignore
            }
        });
    }
}
