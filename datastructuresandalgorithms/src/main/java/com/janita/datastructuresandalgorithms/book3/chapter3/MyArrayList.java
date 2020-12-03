package com.janita.datastructuresandalgorithms.book3.chapter3;

import org.junit.Test;

import java.util.Iterator;
import java.util.NoSuchElementException;

/**
 * MyArrayList
 *
 * @author zhucj
 * @since 20201224
 */
@SuppressWarnings("all")
public class MyArrayList<T> implements Iterable<T> {

    private static final int DEFAULT_CAPACITY = 2;

    private int theSize;

    private T[] theItems;

    public MyArrayList() {
        doClear();
    }

    public void clear() {
        doClear();
    }

    private void doClear() {
        theSize = 0;
        ensureCapacity(DEFAULT_CAPACITY);
    }

    public int size() {
        return theSize;
    }

    public boolean isEmpty() {
        return size() == 0;
    }

    public void trimToSize() {
        ensureCapacity(size());
    }

    public T get(int idx) {
        if (idx < 0 || idx >= size()) {
            throw new ArrayIndexOutOfBoundsException();
        }
        return theItems[idx];
    }

    public T set(int idx, T newVal) {
        if (idx < 0 || idx >= size()) {
            throw new ArrayIndexOutOfBoundsException();
        }
        T oldVal = theItems[idx];
        theItems[idx] = newVal;
        return oldVal;
    }

    private void ensureCapacity(int newCapacity) {
        if (newCapacity < theSize) {
            return;
        }
        T[] old = theItems;
        theItems = (T[]) new Object[newCapacity];
        for (int i = 0; i < size(); i++) {
            theItems[i] = old[i];
        }
    }

    /**
     * 添加到列表的末端
     *
     * @param x
     * @return
     */
    public boolean add(T x) {
        add(size(), x);
        return true;
    }

    /**
     * 添加到指定位置
     *
     * @param idx
     * @param x
     */
    public void add(int idx, T x) {
        if (theItems.length == size()) {
            ensureCapacity(size() * 2 + 1);
        }
        for (int i = theSize; i > idx; i--) {
            theItems[i] = theItems[i - 1];
        }
        theItems[idx] = x;
        theSize++;
    }

    public T remove(int idx) {
        T removed = theItems[idx];
        for (int i = idx; i < size(); i++) {
            theItems[i] = theItems[i + 1];
        }
        theSize--;
        return removed;
    }

    @Override
    public Iterator<T> iterator() {
        return new ArrayListIterator();
    }

    private class ArrayListIterator implements Iterator<T> {

        private int current = 0;

        @Override
        public boolean hasNext() {
            return current < size();
        }

        @Override
        public T next() {
            if (!hasNext()) {
                throw new NoSuchElementException();
            }
            return theItems[current++];
        }

        @Override
        public void remove() {
            MyArrayList.this.remove(--current);
        }
    }

    @Test
    public void test() {
        MyArrayList<Integer> list = new MyArrayList<>();
        list.add(1);
        list.add(2);
        list.add(3);
        System.out.println(list);
    }
}
