package com.janita.java.base.thinkinjava._20_concurrent;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;

import static com.janita.java.base.thinkinjava.util.Print.print;

/**
 * DaemonFromFactory
 *
 * @author zhucj
 * @since 20200528
 */
public class DaemonFromFactory implements Runnable {

    @Override
    public void run() {
        try {
            while (true) {
                TimeUnit.MILLISECONDS.sleep(100);
                print(Thread.currentThread() + " " + this);
            }
        } catch (InterruptedException e) {
            print("Interrupted");
        }
    }

    public static void main(String[] args) throws Exception {
        ExecutorService exec = Executors.newCachedThreadPool(new DaemonThreadFactory());
        for (int i = 0; i < 10; i++) {
            DaemonFromFactory runTask = new DaemonFromFactory();
            exec.execute(runTask);
        }
        print("All daemons started");
        TimeUnit.MILLISECONDS.sleep(500); // Run for a while
    }
}
