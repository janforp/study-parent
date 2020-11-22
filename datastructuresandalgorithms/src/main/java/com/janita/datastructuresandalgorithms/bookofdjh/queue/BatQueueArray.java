package com.janita.datastructuresandalgorithms.bookofdjh.queue;

import com.janita.datastructuresandalgorithms.bookofdjh.queue.assist.ExceptionQueueEmpty;
import com.janita.datastructuresandalgorithms.bookofdjh.queue.assist.ExceptionQueueFull;

/**
 * 顺序数组
 * 借助一个定长数组 Q 来存放对象，即可简单地实现队列。那么，为了符合 FIFO 准则，应该如 何表示和记录队列中各对象的次序呢?
 * 一种自然的办法就是仿照栈的实现，以 Q[0]作为队首，其它对象顺序往后存放。然而如此一来， 每次首元素出队之后，都需要将后续的所有元素向前顺移一个单元⎯⎯若队长为 n，这项工作需要 O(n)时间，因此效率很低。
 *
 * @author zhucj
 * @since 20201126
 */
public class BatQueueArray<T> implements Queue<T> {

    private static final int default_capacity = 1024;

    private Object[] elements;

    private int capacity;

    /**
     * 下次添加元素的时候的下标
     */
    private int lastInIndex = 0;

    public BatQueueArray() {
        this(default_capacity);
    }

    public BatQueueArray(int capacity) {
        this.capacity = capacity;
        this.elements = new Object[capacity];
    }

    @Override
    public int getSize() {
        return lastInIndex;
    }

    @Override
    public boolean isEmpty() {
        return getSize() == 0;
    }

    @Override
    public T front() throws ExceptionQueueEmpty {
        if (isEmpty()) {
            throw new ExceptionQueueEmpty("空了");
        }
        return (T) elements[0];
    }

    @Override
    public void enqueue(T obj) throws ExceptionQueueFull {
        if (lastInIndex >= capacity) {
            throw new ExceptionQueueFull("满了");
        }
        elements[lastInIndex++] = obj;
    }

    /**
     * 顺序数组
     * 借助一个定长数组 Q 来存放对象，即可简单地实现队列。那么，为了符合 FIFO 准则，应该如 何表示和记录队列中各对象的次序呢?
     * 一种自然的办法就是仿照栈的实现，以 Q[0]作为队首，其它对象顺序往后存放。然而如此一来， 每次首元素出队之后，都需要将后续的所有元素向前顺移一个单元⎯⎯若队长为 n，这项工作需要 O(n)时间，因此效率很低。
     *
     * @return
     * @throws ExceptionQueueEmpty
     */
    @Override
    public T dequeue() throws ExceptionQueueEmpty {
        if (isEmpty()) {
            throw new ExceptionQueueEmpty("空了");
        }
        T obj = (T) elements[0];
        for (int i = 1; i < elements.length; i++) {
            elements[i - 1] = elements[i];
        }
        lastInIndex--;
        return obj;
    }

    @Override
    public void traversal() {

    }
}
