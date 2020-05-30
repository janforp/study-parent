package com.janita.java.base.thinkinjava._20_concurrent;

/**
 * EvenGenerator
 *
 * @author zhucj
 * @since 20200528
 */
public class EvenGenerator extends IntGenerator {

    private int currentEvenValue = 0;

    @Override
    public int next() {
        ++currentEvenValue;//不是原子操作
        ++currentEvenValue;
        return currentEvenValue;
    }

    public static void main(String[] args) {
        IntGenerator evenGenerator = new EvenGenerator();
        EvenChecker.test(evenGenerator);
    }
}

class SynchronizedEvenGenerator extends IntGenerator {

    private int currentEvenValue = 0;

    @Override
    public synchronized int next() {
        ++currentEvenValue;//不是原子操作
        Thread.yield();//就算主动让出CPU也不会有并发问题
        ++currentEvenValue;
        return currentEvenValue;
    }

    public static void main(String[] args) {
        IntGenerator evenGenerator = new SynchronizedEvenGenerator();
        EvenChecker.test(evenGenerator);
    }
}
