package com.janita.java.base.thinkinjava._20_concurrent;

import java.util.concurrent.TimeUnit;

import static com.janita.java.base.thinkinjava.util.Print.print;

/**
 * DaemonsDontRunFinally
 *
 * @author zhucj
 * @since 20200528
 */
class ADaemon implements Runnable {

    public void run() {
        try {
            print("Starting ADaemon");
            TimeUnit.SECONDS.sleep(1);
        } catch (InterruptedException e) {
            print("Exiting via InterruptedException");
        } finally {
            //后台线程不会执行finally
            print("This should always run?");
        }
    }
}

public class DaemonsDontRunFinally {

    public static void main(String[] args) throws Exception {
        Thread t = new Thread(new ADaemon());
        t.setDaemon(true);
        t.start();
        TimeUnit.MILLISECONDS.sleep(200);
    }
} /* Output:
Starting ADaemon
*///:~
