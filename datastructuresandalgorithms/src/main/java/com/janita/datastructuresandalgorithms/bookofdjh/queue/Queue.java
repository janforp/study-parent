package com.janita.datastructuresandalgorithms.bookofdjh.queue;

import com.janita.datastructuresandalgorithms.bookofdjh.queue.assist.ExceptionQueueEmpty;
import com.janita.datastructuresandalgorithms.bookofdjh.queue.assist.ExceptionQueueFull;

/**
 * Queue
 *
 * FIFO
 *
 * @author zhucj
 * @since 20201126
 */
public interface Queue<T> {

    int getSize();//返回队列中元素数目

    boolean isEmpty();//判断队列是否为空

    T front()//取队首元素(但不删除)
            throws ExceptionQueueEmpty;

    void enqueue(T obj) throws ExceptionQueueFull;//入队

    T dequeue()//出队
            throws ExceptionQueueEmpty;

    void traversal();//遍历
}
