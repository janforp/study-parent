package com.janita.java.base.xiaoliulaoshi.threadlocal;

import java.util.UUID;
import java.util.concurrent.TimeUnit;

/**
 * AccountId
 *
 * @author zhucj
 * @since 20210128
 */
public class TraceId {

    private static final ThreadLocal<String> TRACE_ID_CONTEXT
            = ThreadLocal.withInitial(() -> UUID.randomUUID().toString().replace("-", ""));

    static class RunnableTask implements Runnable {

        @Override
        public void run() {
            try {
                // 校验登陆
                checkLogin();
                // 处理业务
                doBusiness();
                // 打印日志
                log();
            } finally {
                TRACE_ID_CONTEXT.remove();
            }
        }
    }

    private static boolean checkLogin() {
        System.out.println("登陆成功,traceId =  " + TRACE_ID_CONTEXT.get());
        return true;
    }

    private static boolean doBusiness() {
        System.out.println("处理业务成功,traceId =  " + TRACE_ID_CONTEXT.get());
        return true;
    }

    private static boolean log() {
        System.out.println("=========================== 本次请求结束， 打印日志,traceId =  " + TRACE_ID_CONTEXT.get());
        System.out.println();
        return true;
    }

    static class Test {

        public static void main(String[] args) throws InterruptedException {
            RunnableTask task = new RunnableTask();

            Thread t1 = new Thread(task, "线程1");
            t1.start();
            TimeUnit.MILLISECONDS.sleep(1000);

            Thread t2 = new Thread(task, "线程2");
            t2.start();
            TimeUnit.MILLISECONDS.sleep(1000);

            Thread t3 = new Thread(task, "线程3");
            t3.start();
            TimeUnit.MILLISECONDS.sleep(1000);
        }
    }
}