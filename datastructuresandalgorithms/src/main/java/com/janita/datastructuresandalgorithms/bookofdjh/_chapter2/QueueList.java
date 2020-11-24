package com.janita.datastructuresandalgorithms.bookofdjh._chapter2;

import com.janita.datastructuresandalgorithms.bookofdjh.queue.Queue;
import com.janita.datastructuresandalgorithms.bookofdjh.queue.assist.ExceptionQueueEmpty;
import com.janita.datastructuresandalgorithms.bookofdjh.queue.assist.ExceptionQueueFull;

/**
 * QueueList
 *
 * @author zhucj
 * @since 20201126
 */
public class QueueList<E> implements Queue<E> {

    private Node<E> head;

    private Node<E> tail;

    private int size;

    public QueueList() {
        head = tail = null;
        size = 0;
    }

    @Override
    public int getSize() {
        return size;
    }

    @Override
    public boolean isEmpty() {
        return size == 0;
    }

    @Override
    public E front() throws ExceptionQueueEmpty {
        return null;
    }

    @Override
    public void enqueue(E obj) throws ExceptionQueueFull {

    }

    @Override
    public E dequeue() throws ExceptionQueueEmpty {
        return null;
    }

    @Override
    public void traversal() {

    }
}
