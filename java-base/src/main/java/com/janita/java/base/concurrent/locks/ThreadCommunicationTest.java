package com.janita.java.base.concurrent.locks;

import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

/**
 * 类说明：ThreadCommunicationTest
 *
 * @author zhucj
 * @since 20200423
 */
public class ThreadCommunicationTest {

    /**
     * 传统的线程通信方式
     */
    static class TraditionalBusiness {

        public static void main(String[] args) {
            TraditionalBusiness business = new TraditionalBusiness();

            //新启线程
            new Thread(() -> {
                for (int i = 0; i < 50; i++) {
                    business.sub(i);
                }
            }).start();

            //在主线程
            for (int i = 0; i < 50; i++) {
                business.main(i);
            }
        }

        private boolean bShouldSub = true;

        public synchronized void sub(int i) {
            while (!bShouldSub) {
                try {
                    this.wait();
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
            for (int j = 0; j < 10; j++) {
                System.out.println("子线程 循环到 : " + j + ", 是第 " + i + " 次循环");
            }
            //子线程做了10次，然后让给主线程做
            bShouldSub = false;
            //TODO 如果不使用 synchronized ，则此处会报错
            this.notify();
        }

        public synchronized void main(int i) {
            while (bShouldSub) {
                try {
                    this.wait();
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
            for (int j = 0; j < 100; j++) {
                System.out.println("主线程 循环到 : " + j + ", 是第 " + i + " 次循环");
            }
            //主线程做了100次，然后让给子线程做
            bShouldSub = true;
            this.notify();
        }
    }

    /**
     * 使用 Condition 的线程通信方式
     */
    static class ConditionCommunication {

        public static void main(String[] args) {
            ConditionCommunication business = new ConditionCommunication();
            //新启线程
            new Thread(() -> {
                for (int i = 0; i < 50; i++) {
                    business.sub(i);
                }
            }).start();

            //在主线程
            for (int i = 0; i < 50; i++) {
                business.main(i);
            }
        }

        private final Lock lock = new ReentrantLock();

        /**
         *  condition 是在 Lock 之上的，就类似传统的通信是基于同步一样
         */
        private final Condition condition = lock.newCondition();

        private boolean bShouldSub = true;

        public void sub(int i) {
            lock.lock();
            try {
                while (!bShouldSub) {
                    try {
                        condition.await();
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                }
                for (int j = 0; j < 10; j++) {
                    System.out.println("子线程 循环到 : " + j + ", 是第 " + i + " 次循环");
                }
                bShouldSub = false;
                //发一个信号通知其他等待的线程
                condition.signal();
            } finally {
                lock.unlock();
            }
        }

        public void main(int i) {

            lock.lock();
            try {
                while (bShouldSub) {
                    try {
                        condition.await();
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                }
                for (int j = 0; j < 100; j++) {
                    System.out.println("主线程 循环到 : " + j + ", 是第 " + i + " 次循环");
                }
                bShouldSub = true;
                condition.signal();
            } finally {
                lock.unlock();
            }
        }
    }
}

