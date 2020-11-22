package com.janita.datastructuresandalgorithms.bookofdjh.queue;

import com.janita.datastructuresandalgorithms.bookofdjh.queue.assist.ExceptionQueueEmpty;
import com.janita.datastructuresandalgorithms.bookofdjh.queue.assist.ExceptionQueueFull;

/**
 * LoopQueue
 *
 * @author zhucj
 * @since 20201126
 */
public class LoopQueue<E> implements Queue<E> {

    private E[] elements;

    private int front = 0;

    private int tail = 0;

    private int size = 0;

    @SuppressWarnings("unchecked")
    public LoopQueue(int capacity) {
        this.elements = (E[]) new Object[capacity + 1];
    }

    public LoopQueue() {
        this(3);
    }

    @Override
    public void enqueue(E obj) throws ExceptionQueueFull {
        if ((tail + 1) % elements.length == front) {
            throw new RuntimeException("满了");
        }
        elements[tail] = obj;
        tail = (tail + 1) % elements.length;
        size++;
    }

    @Override
    public E dequeue() throws ExceptionQueueEmpty {
        if (isEmpty()) {
            throw new RuntimeException("空的");
        }
        E element = elements[front];
        elements[front] = null;
        front = (front + 1) % elements.length;
        size--;
        return element;
    }

    @Override
    public int getSize() {
        return size;
    }

    @Override
    public boolean isEmpty() {
        return front == tail;
    }

    @Override
    public E front() throws ExceptionQueueEmpty {
        if (isEmpty()) {
            throw new RuntimeException("空的");
        }
        return elements[front];
    }

    @Override
    public void traversal() {

    }
}
