package com.janita.java.base.thinkinjava._20_concurrent;

import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

/**
 * ExplicitCriticalSection
 *
 * @author zhucj
 * @since 20200528
 */

class ExplicitPairManager1 extends PairManager {

    private Lock lock = new ReentrantLock();

    public synchronized void increment() {
        lock.lock();
        try {
            p.incrementX();
            p.incrementY();
            store(getPair());
        } finally {
            lock.unlock();
        }
    }
}

// Use a critical section:
class ExplicitPairManager2 extends PairManager {

    private Lock lock = new ReentrantLock();

    public void increment() {
        Pair temp;
        lock.lock();
        try {
            p.incrementX();
            p.incrementY();
            temp = getPair();
        } finally {
            lock.unlock();
        }
        //临界区之外
        store(temp);
    }
}

/**
 * TODO 为什么我的会报错？
 * Exception in thread "pool-1-thread-4" com.janita.java.base.thinkinjava._20_concurrent.Pair$PairValuesNotEqualException: Pair values not equal: x: 4, y: 3
 * at com.janita.java.base.thinkinjava._20_concurrent.Pair.checkState(CriticalSection.java:61)
 * at com.janita.java.base.thinkinjava._20_concurrent.PairChecker.run(CriticalSection.java:148)
 * at java.util.concurrent.ThreadPoolExecutor.runWorker(ThreadPoolExecutor.java:1149)
 * at java.util.concurrent.ThreadPoolExecutor$Worker.run(ThreadPoolExecutor.java:624)
 * at java.lang.Thread.run(Thread.java:748)
 * pm1: Pair: x: 17, y: 17 checkCounter = 3
 * pm2: Pair: x: 18, y: 18 checkCounter = 8005923
 */
class ExplicitCriticalSection {

    public static void main(String[] args) {
        PairManager pairManager1 = new ExplicitPairManager1();
        PairManager pairManager2 = new ExplicitPairManager2();
        CriticalSection.testApproaches(pairManager1, pairManager2);
    }
}