package com.janita.datastructuresandalgorithms.bookofdjh.chapter3;

import org.omg.CORBA.Object;

/**
 * getSize() O(1)
 * isEmpty() O(1)
 * getAtRank() O(1)
 * replaceAtRank() O(1)
 * insertAtRank() O(n)
 * removeAtRank() O(n)
 *
 * 自动扩充
 *
 * @author zhucj
 * @since 20201126
 */
public class ExtArrayVector<E> implements Vector<E> {

    private int capacity = 8;

    private int size;

    private E[] elements;

    @SuppressWarnings("unchecked")
    public ExtArrayVector() {
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
    @SuppressWarnings("all")
    public E insertAtRank(int r, E obj) {
        if (r < 0 || r > size) {
            throw new ArrayIndexOutOfBoundsException("越界了");
        }
        if (size >= capacity) {
            //扩容
            capacity = 2 * capacity;
            E[] newArr = (E[]) new Object[capacity];
            for (int i = 0; i < size; i++) {
                newArr[i] = elements[i];
            }
            elements = newArr;
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
