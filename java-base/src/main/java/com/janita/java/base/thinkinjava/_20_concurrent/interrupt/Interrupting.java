package com.janita.java.base.thinkinjava._20_concurrent.interrupt;//: concurrency/Interrupting.java
// Interrupting a blocked thread.

import java.io.IOException;
import java.io.InputStream;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;
import java.util.concurrent.TimeUnit;

import static com.janita.java.base.thinkinjava.util.Print.print;

class SleepBlocked implements Runnable {

    @Override
    public void run() {
        try {
            TimeUnit.SECONDS.sleep(100);
        } catch (InterruptedException e) {
            print("SleepBlocked-InterruptedException");
        }
        print("Exiting SleepBlocked.run()");
    }
}

class IOBlocked implements Runnable {

    private InputStream in;

    public IOBlocked(InputStream is) {
        in = is;
    }

    @Override
    public void run() {
        try {
            print("IOBlocked-Waiting for read():");
            in.read();
        } catch (IOException e) {
            if (Thread.currentThread().isInterrupted()) {
                print("IOBlocked-Interrupted from blocked I/O");
            } else {
                throw new RuntimeException(e);
            }
        }

        //无法退出，该行不会执行
        print("Exiting IOBlocked.run()");
    }
}

class SynchronizedBlocked implements Runnable {

    public synchronized void f() {
        while (true) // Never releases lock
        {
            //该方法永远不返回，所以永远不释放锁
            Thread.yield();
        }
    }

    public SynchronizedBlocked() {
        // Lock acquired by this thread
        //这个线程先拿到了锁
        new Thread(this::f).start();
    }

    @Override
    public void run() {
        print("SynchronizedBlocked-Trying to call f()");
        //main线程永远都拿不到锁
        f();
        //无法退出，该行不会执行
        print("Exiting SynchronizedBlocked.run()");
    }
}

public class Interrupting {

    private static ExecutorService exec = Executors.newCachedThreadPool();

    static void test(Runnable r) throws InterruptedException {
        Future<?> f = exec.submit(r);
        TimeUnit.MILLISECONDS.sleep(100);
        print("Interrupting " + r.getClass().getName());

        //该方法会试图调用线程的 interrupt() 方法
        f.cancel(true); // Interrupts if running
        print("Interrupt sent to " + r.getClass().getName());
    }

    /**
     * cancel也就是 阻塞线程的 interrupt() 方法不是都有效
     *
     * @param args
     * @throws Exception
     */
    public static void main(String[] args) throws Exception {
        //3个test方法一个一个的执行，比较直观，容易理解
        test(new SleepBlocked());//可中断
        test(new IOBlocked(System.in));//不可中断
        test(new SynchronizedBlocked());//不可中断
        TimeUnit.SECONDS.sleep(3);
        print("Aborting with System.exit(0)");
        System.exit(0); // ... since last 2 interrupts failed
    }
} /* Output: (95% match)
Interrupting SleepBlocked
InterruptedException
Exiting SleepBlocked.run()
Interrupt sent to SleepBlocked
Waiting for read():
Interrupting IOBlocked
Interrupt sent to IOBlocked
Trying to call f()
Interrupting SynchronizedBlocked
Interrupt sent to SynchronizedBlocked
Aborting with System.exit(0)
*///:~
