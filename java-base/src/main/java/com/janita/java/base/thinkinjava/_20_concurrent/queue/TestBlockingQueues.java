package com.janita.java.base.thinkinjava._20_concurrent.queue;//: concurrency/TestBlockingQueues.java
// {RunByHand}

import com.janita.java.base.thinkinjava._20_concurrent.LiftOff;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.concurrent.ArrayBlockingQueue;
import java.util.concurrent.BlockingQueue;
import java.util.concurrent.LinkedBlockingQueue;
import java.util.concurrent.SynchronousQueue;

import static com.janita.java.base.thinkinjava.util.Print.print;

class LiftOffRunner implements Runnable {

    //任务
    private BlockingQueue<LiftOff> rockets;

    public LiftOffRunner(BlockingQueue<LiftOff> queue) {
        rockets = queue;
    }

    public void add(LiftOff lo) {
        try {
            rockets.put(lo);
        } catch (InterruptedException e) {
            print("Interrupted during put()");
        }
    }

    @Override
    public void run() {
        try {
            while (!Thread.interrupted()) {

                //如果没有元素了，会阻塞在此
                LiftOff rocket = rockets.take();
                rocket.run(); // Use this thread
            }
        } catch (InterruptedException e) {
            //t.interrupt()会抛出该异常
            print("Waking from take()");
        }
        print("Exiting LiftOffRunner");
    }
}

public class TestBlockingQueues {

    static void getKey() {
        try {
            // Compensate for Windows/Linux difference in the
            // length of the result produced by the Enter key:
            new BufferedReader(new InputStreamReader(System.in)).readLine();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    static void getKey(String message) {
        print(message);
        getKey();
    }

    static void test(String msg, BlockingQueue<LiftOff> queue) {
        print(msg);
        LiftOffRunner runner = new LiftOffRunner(queue);
        Thread t = new Thread(runner);
        t.start();
        for (int i = 0; i < 5; i++) {

            //添加5个元素
            runner.add(new LiftOff(5));
        }

        //阻塞：控制台会车即可返回
        getKey("Press 'Enter' (" + msg + ")");
        t.interrupt();
        print("Finished " + msg + " test");
    }

    public static void main(String[] args) {
        // Unlimited size
        test("LinkedBlockingQueue", new LinkedBlockingQueue<LiftOff>());

        // Fixed size
        test("ArrayBlockingQueue", new ArrayBlockingQueue<LiftOff>(3));

        // Size of 1
        test("SynchronousQueue", new SynchronousQueue<LiftOff>());
    }
}
