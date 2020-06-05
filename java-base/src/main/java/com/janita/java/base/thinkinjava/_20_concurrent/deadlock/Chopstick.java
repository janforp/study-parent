package com.janita.java.base.thinkinjava._20_concurrent.deadlock;//: concurrency/Chopstick.java
// Chopsticks for dining philosophers.

public class Chopstick {

    //该筷子是被占用
    private boolean taken = false;

    //试图占用筷子
    public synchronized void take() throws InterruptedException {
        while (taken) {
            wait();
        }
        taken = true;
    }

    //该筷子使用完毕
    public synchronized void drop() {
        taken = false;
        notifyAll();
    }
}
