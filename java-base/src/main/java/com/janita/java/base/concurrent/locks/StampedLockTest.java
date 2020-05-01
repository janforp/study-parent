package com.janita.java.base.concurrent.locks;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.locks.StampedLock;

/**
 * 类说明：StampedLockTest
 *
 * @author zhucj
 * @since 20200423
 */
public class StampedLockTest {

    public static void main(String[] args) {

        Point point = new Point();
        ExecutorService threadPool = Executors.newFixedThreadPool(10);

        for (int i = 0; i < 5; i++) {
            Runnable runnable = () -> point.move(10D, 10D);
            threadPool.submit(runnable);
        }

        for (int i = 0; i < 10; i++) {
            Runnable runnable = () -> {
                double distanceFromOrigin = point.distanceFromOrigin();
                System.out.println(distanceFromOrigin);
            };
            threadPool.submit(runnable);
        }
    }
}

class Point {

    private double x, y;

    private final StampedLock stampedLock = new StampedLock();

    void move(double deltaX, double deltaY) {
        long stamp = stampedLock.writeLock();
        try {
            x = x + deltaX;
            y = y + deltaY;
        } finally {
            stampedLock.unlockWrite(stamp);
        }
    }

    double distanceFromOrigin() {
        long stamp = stampedLock.tryOptimisticRead();
        double currentX = x, currentY = y;
        if (!stampedLock.validate(stamp)) {
            stamp = stampedLock.readLock();
            try {
                currentX = x;
                currentY = y;
            } finally {
                stampedLock.unlockRead(stamp);
            }
        }
        return Math.sqrt(currentX * currentX + currentY * currentY);
    }
}
