package com.janita.java.base.thinkinjava.util;//: concurrency/Tester.java
// Framework to test performance of concurrency containers.

import com.janita.java.base.thinkinjava._16_array.Generated;
import com.janita.java.base.thinkinjava._16_array.RandomGenerator;

import java.util.concurrent.CountDownLatch;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public abstract class Tester<C> {

    /**
     * 重复的次数
     */
    public static int testReps = 10;

    /**
     * 测试次数
     */
    public static int testCycles = 1000;

    /**
     * 容器的容量
     */
    public static int containerSize = 1000;

    public abstract C containerInitializer();

    /**
     * 启动读取和写入任务
     */
    public abstract void startReadersAndWriters();

    public C testContainer;

    public String testId;

    public int nReaders;

    public int nWriters;

    public volatile long readResult = 0;

    /**
     * 读花费时间
     */
    public volatile long readTime = 0;

    /**
     * 写花费时间
     */
    public volatile long writeTime = 0;

    CountDownLatch endLatch;

    public static ExecutorService exec = Executors.newCachedThreadPool();

    public Integer[] writeData;

    public Tester(String testId, int nReaders, int nWriters) {
        this.testId = testId + " " + nReaders + "r " + nWriters + "w";
        this.nReaders = nReaders;
        this.nWriters = nWriters;
        writeData = Generated.array(Integer.class, new RandomGenerator.Integer(), containerSize);
        for (int i = 0; i < testReps; i++) {
            runTest();
            readTime = 0;
            writeTime = 0;
        }
    }

    void runTest() {
        endLatch = new CountDownLatch(nReaders + nWriters);
        testContainer = containerInitializer();
        startReadersAndWriters();
        try {
            endLatch.await();
        } catch (InterruptedException ex) {
            System.out.println("endLatch interrupted");
        }
        System.out.printf("%-27s %14d %14d\n", testId, readTime, writeTime);
        if (readTime != 0 && writeTime != 0) {
            System.out.printf("%-27s %14d\n", "readTime + writeTime =", readTime + writeTime);
        }
    }

    public abstract class TestTask implements Runnable {

        public abstract void test();

        public abstract void putResults();

        public long duration;

        @Override
        public void run() {
            long startTime = System.nanoTime();
            test();
            duration = System.nanoTime() - startTime;
            synchronized (Tester.this) {
                putResults();
            }
            endLatch.countDown();
        }
    }

    public static void initMain(String[] args) {
        if (args.length > 0) {
            testReps = new Integer(args[0]);
        }
        if (args.length > 1) {
            testCycles = new Integer(args[1]);
        }
        if (args.length > 2) {
            containerSize = new Integer(args[2]);
        }
        System.out.printf("%-27s %14s %14s\n", "Type", "Read time", "Write time");
    }
}
