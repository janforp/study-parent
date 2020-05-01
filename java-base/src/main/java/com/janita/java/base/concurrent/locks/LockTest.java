package com.janita.java.base.concurrent.locks;

import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

/**
 * 类说明：LockTest
 *
 * @author zhucj
 * @since 20200423
 */
public class LockTest {

    public static void main(String[] args) {
        new LockTest().init();
    }

    private void init() {
        final Output output = new Output();
        new Thread(() -> {
            while (true) {
                try {
                    Thread.sleep(10);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                output.outputWithLock("Jay Chou");
            }
        }).start();

        new Thread(() -> {
            while (true) {
                try {
                    Thread.sleep(10);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                output.outputWithLock("Jack Chou");
            }
        }).start();
    }

    static class Output {

        Lock lock = new ReentrantLock();

        public void outputWithLock(String name) {
            int length = name.length();
            lock.lock();
            try {
                for (int i = 0; i < length; i++) {
                    System.out.print(name.charAt(i));
                }
                System.out.println();
            } finally {
                lock.unlock();
            }
        }

        public void unsafeOutput(String name) {
            int length = name.length();
            for (int i = 0; i < length; i++) {
                System.out.print(name.charAt(i));
            }
            System.out.println();
        }
    }
}