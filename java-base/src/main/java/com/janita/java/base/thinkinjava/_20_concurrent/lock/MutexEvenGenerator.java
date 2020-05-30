package com.janita.java.base.thinkinjava._20_concurrent.lock;

import com.janita.java.base.thinkinjava._20_concurrent.EvenChecker;
import com.janita.java.base.thinkinjava._20_concurrent.EvenGenerator;
import com.janita.java.base.thinkinjava._20_concurrent.IntGenerator;

import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

/**
 * MutexEvenGenerator
 *
 * @author zhucj
 * @since 20200528
 */
public class MutexEvenGenerator extends IntGenerator {

    private int currentEvenValue = 0;

    private Lock lock = new ReentrantLock();

    @Override
    public int next() {
        lock.lock();
        try {
            ++currentEvenValue;//不是原子操作
            ++currentEvenValue;
            return currentEvenValue;
        } finally {
            lock.unlock();
        }
    }

    public static void main(String[] args) {
        IntGenerator evenGenerator = new MutexEvenGenerator();
        EvenChecker.test(evenGenerator);
    }
}
