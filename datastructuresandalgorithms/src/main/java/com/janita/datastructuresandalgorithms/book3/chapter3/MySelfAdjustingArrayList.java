package com.janita.datastructuresandalgorithms.book3.chapter3;

import java.util.NoSuchElementException;

/**
 * 自调整表
 * 添加元素到列表前端
 * 添加find(T x)操作，当元素被find访问时，该元素被移到表的前端，并且不改变其他元素的相对顺序
 *
 * 前端                   后端
 * 0 -> 1 -> 2 -> 3 -> 4 -> 5
 *
 * @author zhucj
 * @since 20201224
 */
public class MySelfAdjustingArrayList<T> implements MySelfAdjustingList<T> {

    private T[] arr;

    private int size;

    private int capacity;

    public MySelfAdjustingArrayList(int capacity) {
        arr = (T[]) new Object[capacity];
        size = 0;
        this.capacity = capacity;
    }

    @Override
    public int size() {
        return size;
    }

    @Override
    public boolean isEmpty() {
        return size() == 0;
    }

    @Override
    public boolean add(T x) {
        if (size == capacity) {
            throw new IndexOutOfBoundsException();
        }
        for (int i = size; i > 0; i--) {
            arr[i] = arr[i - 1];
        }
        arr[0] = x;
        size++;
        return true;
    }

    @Override
    public T remove(int idx) {
        if (idx < 0 || idx >= size) {
            throw new IndexOutOfBoundsException();
        }
        if (isEmpty()) {
            throw new NoSuchElementException();
        }
        T result = arr[idx];
        for (int i = idx; i < size; i++) {
            arr[i] = arr[i + 1];
        }
        return result;
    }

    @Override
    public T find(T x) {
        if (isEmpty()) {
            throw new IndexOutOfBoundsException();
        }
        int index = -1;
        if (x == null) {
            for (int i = 0; i < size; i++) {
                if (arr[i] == null) {
                    index = i;
                }
            }
        } else {
            for (int i = 0; i < size; i++) {
                if (x.equals(arr[i])) {
                    index = i;
                }
            }
        }
        if (index == -1) {
            throw new NoSuchElementException();
        }
        for (int i = index; i > 0; i--) {
            arr[i] = arr[i - 1];
        }
        arr[0] = x;
        return x;
    }
}
