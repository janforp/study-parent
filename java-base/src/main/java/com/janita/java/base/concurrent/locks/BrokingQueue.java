package com.janita.java.base.concurrent.locks;

/**
 * BrokingQueue
 *
 * @author zhucj
 * @since 20210225
 */
public interface BrokingQueue<T> {

    void put(T element) throws InterruptedException;

    T take() throws InterruptedException;
}
