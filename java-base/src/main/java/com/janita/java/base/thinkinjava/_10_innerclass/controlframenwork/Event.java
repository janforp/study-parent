package com.janita.java.base.thinkinjava._10_innerclass.controlframenwork;

/**
 * 类说明：Event
 *
 * @author zhucj
 * @since 20200423
 */
public abstract class Event {

    private long eventTime;

    protected final long delayTime;

    public Event(long delayTime) {
        this.delayTime = delayTime;
        start();
    }

    public void start() {
        eventTime = System.nanoTime() + delayTime;
    }

    public boolean ready() {
        return System.nanoTime() > eventTime;
    }

    public abstract void action();
}
