package com.janita.datastructuresandalgorithms.bookofdjh.chapter3;

/**
 * getSize() O(1)
 * isEmpty() O(1)
 * getAtRank() O(1)
 * replaceAtRank() O(1)
 * insertAtRank() O(n)
 * removeAtRank() O(n)
 *
 * 其中插入(删除)操作之所以需要耗费线性时间，是因为需要将操作位置的所有后继元素向后 (向前)顺次移动。这样的效率实在难以令人满意，因此我们将在后面讨论其它的实现。
 *
 * @author zhucj
 * @since 20201126
 */
public class ArrayVector<E> implements Vector<E> {

    private int capacity = 1024;

    private int size;

    private E[] elements;

    @SuppressWarnings("unchecked")
    public ArrayVector() {
        this.elements = (E[]) new Object[capacity];
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
    public E getAtRank(int r) {
        if (r < 0 || r >= size) {
            throw new ArrayIndexOutOfBoundsException("越界了");
        }
        return elements[r];
    }

    @Override
    public E replaceAtRank(int r, E obj) {
        if (r < 0 || r >= size) {
            throw new ArrayIndexOutOfBoundsException("越界了");
        }
        E old = elements[r];
        elements[r] = obj;
        return old;
    }

    /**
     * O(n)
     *
     * @param r
     * @param obj
     * @return
     */
    @Override
    public E insertAtRank(int r, E obj) {
        if (r < 0 || r > size) {
            throw new ArrayIndexOutOfBoundsException("越界了");
        }
        if (size >= capacity) {
            throw new RuntimeException("满了");
        }
        //后续元素顺次后移
        for (int i = size; i > r; i--) {
            elements[size] = elements[size - 1];
        }
        elements[r] = obj;
        size++;
        return obj;
    }

    /**
     * O(n)
     *
     * @param r
     * @return
     */
    @Override
    public E removeAtRank(int r) {
        if (r < 0 || r >= capacity) {
            throw new ArrayIndexOutOfBoundsException("越界了");
        }
        E e = elements[r];
        for (int i = r; i < size; i++) {
            elements[r] = elements[r + 1];
        }
        size--;
        return e;
    }
}
