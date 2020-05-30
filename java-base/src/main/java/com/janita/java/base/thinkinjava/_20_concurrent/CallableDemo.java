package com.janita.java.base.thinkinjava._20_concurrent;

import java.util.ArrayList;
import java.util.concurrent.Callable;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;

/**
 * CallableDemo
 *
 * @author zhucj
 * @since 20200528
 */
class TaskWithResult implements Callable<String> {

    private int id;

    public TaskWithResult(int id) {
        this.id = id;
    }

    @Override
    public String call() {
        return "result of TaskWithResult " + id;
    }
}

public class CallableDemo {

    public static void main(String[] args) {

        ExecutorService exec = Executors.newCachedThreadPool();
        ArrayList<Future<String>> results = new ArrayList<>();
        for (int i = 0; i < 10; i++) {
            results.add(exec.submit(new TaskWithResult(i)));
        }
        for (Future<String> future : results) {
            try {
                System.out.println(future.get());
            } catch (InterruptedException | ExecutionException e) {
                e.printStackTrace();
                System.out.println(e);
            } finally {
                exec.shutdown();
            }
        }
    }
}
