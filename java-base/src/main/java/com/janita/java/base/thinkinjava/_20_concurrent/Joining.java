package com.janita.java.base.thinkinjava._20_concurrent;

import static com.janita.java.base.thinkinjava.util.Print.print;

/**
 * Joining
 *
 * @author zhucj
 * @since 20200528
 */
class Sleeper extends Thread {

    private int duration;

    public Sleeper(String name, int sleepTime) {
        super(name);
        duration = sleepTime;
        start();
    }

    @Override
    public void run() {
        try {
            //如果当前线程被中断了，则会抛出异常
            sleep(duration);
        } catch (InterruptedException e) {
            //当另一个线程在该线程上调用 interrupt() 时，将给该线程一个中断标志，表明该线程已经被中断，然而，异常被捕获时候将清理这个标志
            print(getName() + " was interrupted. " + "isInterrupted(): " + isInterrupted());
            return;
        }
        print(getName() + " has awakened");
    }
}

class Joiner extends Thread {

    private Sleeper sleeper;

    public Joiner(String name, Sleeper sleeper) {
        super(name);
        this.sleeper = sleeper;
        start();
    }

    @Override
    public void run() {
        try {
            //sleeper 线程被中断之后就会马上返回
            //否则当前线程必须要等待 sleeper 线程结束后才能继续往下执行
            sleeper.join();
        } catch (InterruptedException e) {
            print("Interrupted");
        }
        print(getName() + " join completed");
    }
}

public class Joining {

    public static void main(String[] args) {
        joinInterrupt();
//        joinNotInterrupt();
    }

    private static void joinInterrupt() {
        Sleeper grumpy = new Sleeper("Grumpy", 1500);
        Joiner doc = new Joiner("Doc", grumpy);
        grumpy.interrupt();
    }

    private static void joinNotInterrupt() {
        Sleeper sleepy = new Sleeper("Sleepy", 1500);
        Joiner dopey = new Joiner("Dopey", sleepy);
    }
}
/* Output:
Grumpy was interrupted. isInterrupted(): false
Doc join completed
Sleepy has awakened
Dopey join completed
*///:~
