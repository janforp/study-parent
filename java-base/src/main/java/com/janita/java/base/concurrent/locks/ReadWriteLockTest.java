package com.janita.java.base.concurrent.locks;

import java.util.Random;
import java.util.concurrent.locks.ReadWriteLock;
import java.util.concurrent.locks.ReentrantReadWriteLock;

/**
 * 类说明：ReadWriteLockTest
 *
 * @author zhucj
 * @since 20200423
 */
public class ReadWriteLockTest {

    public static void main(String[] args) {
        ReadWriteHelper helper = new ReadWriteHelper();
        for (int i = 0; i < 3; i++) {
            new Thread(() -> {
                while(true){
                    helper.get();
                }
            }).start();

            new Thread(() -> {
                while(true){
                    helper.put(new Random().nextInt(10000));
                }
            }).start();
        }
    }
}

class ReadWriteHelper {

    /**
     * 共享数据，只能有一个线程同时写，有多个线程同时读
     */
    private Object data = null;

    ReadWriteLock rwl = new ReentrantReadWriteLock();

    public void get() {
        rwl.readLock().lock();
        try {
            System.out.println(Thread.currentThread().getName() + " 线程准备 读 数据");
            Thread.sleep((long)(Math.random() * 1000));
            System.out.println(Thread.currentThread().getName() + "线程读到的数据 = " + data);
        }catch (InterruptedException e) {
            e.printStackTrace();
        }finally {
            rwl.readLock().unlock();
        }
    }

    public void put(Object data) {
        rwl.writeLock().lock();
        try {
            System.out.println(Thread.currentThread().getName() + " 线程准备 写 数据");
            Thread.sleep((long)(Math.random() * 1000));
            this.data = data;
            System.out.println(Thread.currentThread().getName() + "线程 写 入的数据 = " + data);
        }catch (InterruptedException e) {
            e.printStackTrace();
        }finally {
            rwl.writeLock().unlock();
        }
    }
}
