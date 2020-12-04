package com.janita.datastructuresandalgorithms.book3.chapter3.queue;

/**
 * MyQueue
 *
 * @author zhucj
 * @since 20201224
 */
public interface MyQueue<T> {

    /**
     * 入队
     *
     * @param x
     * @return
     */
    boolean enqueue(T x);

    /**
     * 出队
     *
     * @return
     */
    T dequeue();

    /**
     * 是否为空
     *
     * @return
     */
    boolean isEmpty();

    /**
     * 规模
     *
     * @return
     */
    int size();
}