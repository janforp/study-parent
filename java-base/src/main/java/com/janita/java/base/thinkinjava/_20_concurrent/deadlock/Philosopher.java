package com.janita.java.base.thinkinjava._20_concurrent.deadlock;//: concurrency/Philosopher.java
// A dining philosopher

import java.util.Random;
import java.util.concurrent.TimeUnit;

import static com.janita.java.base.thinkinjava.util.Print.print;

public class Philosopher implements Runnable {

    private Chopstick left;

    private Chopstick right;

    //排序用
    private final int id;

    //类似思考思考
    private final int ponderFactor;

    private Random rand = new Random(47);


    private void pause() throws InterruptedException {
        if (ponderFactor == 0) {
            return;
        }
        TimeUnit.MILLISECONDS.sleep(rand.nextInt(ponderFactor * 250));
    }

    public Philosopher(Chopstick left, Chopstick right, int ident, int ponder) {
        this.left = left;
        this.right = right;
        id = ident;
        ponderFactor = ponder;
    }

    @Override
    public void run() {
        try {
            while (!Thread.interrupted()) {
                print(this + " " + "thinking");
                pause();
                // Philosopher becomes hungry
                print(this + " " + "grabbing right");
                right.take();
                print(this + " " + "grabbing left");
                left.take();
                print(this + " " + "eating");
                pause();
                right.drop();
                left.drop();
            }
        } catch (InterruptedException e) {
            print(this + " " + "exiting via interrupt");
        }
    }

    @Override
    public String toString() {
        return "Philosopher " + id;
    }
}