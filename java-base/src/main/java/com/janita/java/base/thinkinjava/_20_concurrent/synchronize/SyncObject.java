package com.janita.java.base.thinkinjava._20_concurrent.synchronize;

import static com.janita.java.base.thinkinjava.util.Print.print;

/**
 * 2个方法的同步使用的不是同一个锁，因此两个同步是互相独立的
 */
class DualSynch {

    private Object syncObject = new Object();

    //在当前对象（this）上的锁
    public synchronized void f() {
        for (int i = 0; i < 5; i++) {
            print("f()");
            Thread.yield();
        }
    }

    public void g() {
        //指定对象的锁
        synchronized (syncObject) {
            for (int i = 0; i < 5; i++) {
                print("g()");
                Thread.yield();
            }
        }
    }
}

public class SyncObject {

    public static void main(String[] args) {
        final DualSynch ds = new DualSynch();
        new Thread(ds::f).start();
        ds.g();
    }
}