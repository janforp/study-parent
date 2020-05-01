package com.janita.java.base.concurrent.locks;

import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

/**
 * 类说明：ThreeConditionCommunication
 *
 * 线程1 执行完 线程2 执行完 线程3 执行完 线程1 执行，如此循环
 *
 * @author zhucj
 * @since 20200423
 */
public class ThreeConditionCommunication {

    public static void main(String[] args) {

        ThreeThreadTask task = new ThreeThreadTask();

        new Thread(() -> {
            while (true) {
                try {
                    task.runTask1();
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        }).start();

        new Thread(() -> {
            while (true) {
                try {
                    task.runTask2();
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        }).start();

        new Thread(() -> {
            while (true) {
                try {
                    task.runTask3();
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        }).start();
    }

}

class ThreeThreadTask {

    private final Lock lock = new ReentrantLock();

    private final Condition condition1_2 = lock.newCondition();

    private final Condition condition2_3 = lock.newCondition();

    private final Condition condition3_1 = lock.newCondition();

    private boolean run1 = true;

    private boolean run2 = false;

    private boolean run3 = false;

    public void runTask1() throws InterruptedException {
        lock.lock();
        try {
            while (!run1) {
                try {
                    condition3_1.await();
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
            Thread.sleep(1000);
            System.out.println(" 执行 1 执行 ");
            run1 = false;
            run2 = true;
            condition1_2.signal();
        } finally {
            lock.unlock();
        }
    }

    public void runTask2() throws InterruptedException {
        lock.lock();
        try {
            while (!run2) {
                try {
                    condition1_2.await();
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
            Thread.sleep(1000);
            System.out.println(" 执行 2 执行 ");
            run2 = false;
            run3 = true;
            condition2_3.signal();
        } finally {
            lock.unlock();
        }
    }

    public void runTask3() throws InterruptedException {
        lock.lock();
        try {
            while (!run3) {
                try {
                    condition2_3.await();
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
            Thread.sleep(1000);
            System.out.println(" 执行 3 执行 ");
            run3 = false;
            run1 = true;
            condition3_1.signal();
        } finally {
            lock.unlock();
        }
    }
}
