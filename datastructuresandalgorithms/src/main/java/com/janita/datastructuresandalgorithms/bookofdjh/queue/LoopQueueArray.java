package com.janita.datastructuresandalgorithms.bookofdjh.queue;

import com.janita.datastructuresandalgorithms.bookofdjh.queue.assist.ExceptionQueueEmpty;
import com.janita.datastructuresandalgorithms.bookofdjh.queue.assist.ExceptionQueueFull;

/**
 * QueueArray
 * TODO
 *
 * @author zhucj
 * @since 20201126
 */
public class LoopQueueArray<T> implements Queue<T> {

    private static final int DEFAULT_CAPACITY = 1024;

    private T[] elements;

    private int capacity;

    /**
     * 始终等于首元素在数组中的下标，即指向下次出队元素的位置
     */
    private int front = 0;

    /**
     * 始终等于末元素的下标加一，即指向下次入队元素的位置
     */
    private int rear = 0;

    public LoopQueueArray() {
        this(DEFAULT_CAPACITY);
    }

    @SuppressWarnings("unchecked")
    public LoopQueueArray(int capacity) {
        this.capacity = capacity;
        this.elements = (T[]) new Object[capacity];
    }

    @Override
    public int getSize() {
        return (capacity - front + rear) % capacity;
    }

    @Override
    public boolean isEmpty() {
        return rear == front;
    }

    @Override
    public T front() throws ExceptionQueueEmpty {
        if (isEmpty()) {
            throw new ExceptionQueueEmpty("空了");
        }
        return elements[front];
    }

    /**
     * 一开始，f = r = 0，此时队空。每次有对象入队时，将其存放于 Q[r]，然后 r 加一，以指向下一 单元
     *
     * @param obj
     * @throws ExceptionQueueFull
     */
    @Override
    public void enqueue(T obj) throws ExceptionQueueFull {
        if (getSize() == capacity - 1) {
            //牺牲一个空间
            throw new ExceptionQueueFull("满了");
        }
        elements[rear] = obj;
        rear = (rear + 1) % capacity;
    }

    /**
     * 对称地，每次有对象出队之后，也将 f 加一，指向新的队首元素
     *
     * @return
     * @throws ExceptionQueueEmpty
     */
    @Override
    public T dequeue() throws ExceptionQueueEmpty {
        if (isEmpty()) {
            throw new ExceptionQueueEmpty("空了");
        }
        T ele = elements[front];
        elements[front] = null;
        front = (front + 1) % capacity;
        return ele;
    }

    @Override
    public void traversal() {

    }
}
