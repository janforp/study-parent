package com.janita.java.base.xiaoliulaoshi.threadlocal;

import com.alibaba.ttl.TransmittableThreadLocal;
import com.alibaba.ttl.threadpool.TtlExecutors;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

/**
 * TransmitTableThreadLocalTest
 *
 * @author zhucj
 * @since 20210729
 */
public class TransmitTableThreadLocalTest {

    static TransmittableThreadLocal<String> threadLocal = new TransmittableThreadLocal<>();

    static ExecutorService pool = TtlExecutors.getTtlExecutorService(Executors.newFixedThreadPool(5));

    public static void main(String[] args) {

        for (int i = 0; i < 100; i++) {
            int index = i;
            pool.execute(new Thread(() -> {
                //传递值
                threadLocal.set("java " + index);
                pool.execute(new Thread(
                        () -> {
                            //获取值
                            System.out.println(Thread.currentThread().getName() + " 获取修改后的值:" + threadLocal.get());
                        }
                ));
            }));
        }

        try {
            Thread.sleep(1000);
            pool.shutdown();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

    }
}
