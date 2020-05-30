package com.janita.java.base.thinkinjava._20_concurrent;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

/**
 * ExceptionThread
 *
 * @author zhucj
 * @since 20200528
 */
public class ExceptionThread implements Runnable{

    @Override
    public void run() {
        throw new RuntimeException();
    }

    public static void main(String[] args) {
        ExecutorService executorService = Executors.newCachedThreadPool();
        executorService.execute(new ExceptionThread());
    }
}

class NaiveExceptionHandling {

    public static void main(String[] args) {
        try {
            ExecutorService executorService = Executors.newCachedThreadPool();
            executorService.execute(new ExceptionThread());
        }catch (Exception e) {
            //在其他线程中抛出了异常，此处无法捕获
            System.out.println("Exception has been handled!");
        }
    }
}