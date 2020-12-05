package com.janita.datastructuresandalgorithms.book3.chapter3;

import com.google.common.collect.Lists;
import org.junit.Test;

import java.util.Iterator;
import java.util.List;
import java.util.ListIterator;
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

    /**
     * 添加所有元素到末端
     *
     * @param items
     */
    public void addAll(Iterable<? extends T> items) {
        Iterator<? extends T> iterator = items.iterator();
        while (iterator.hasNext()) {
            add(iterator.next());
        }
    }

    public Iterator<T> reverseIterator() {
        return new ArrayListReverseIterator();
    }

    public class ArrayListReverseIterator implements Iterator<T> {

        private int current = theSize;

        @Override
        public boolean hasNext() {
            return current > 0;
        }

        @Override
        public T next() {
            return theItems[--current];
        }
    }

    public ListIterator<T> listIterator() {
        return new ArrayListIterator2();
    }

    @Override
    public Iterator<T> iterator() {
        return new ArrayListIterator();
    }

    private class ArrayListIterator2 implements ListIterator<T> {

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
        public boolean hasPrevious() {
            return current > 0;
        }

        @Override
        public T previous() {
            if (!hasPrevious()) {
                throw new NoSuchElementException();
            }
            return theItems[--current];
        }

        @Override
        public int nextIndex() {
            return current;
        }

        @Override
        public int previousIndex() {
            return current - 1;
        }

        @Override
        public void remove() {
            MyArrayList.this.remove(current);
        }

        @Override
        public void set(T t) {
            theItems[current] = t;
        }

        @Override
        public void add(T t) {
            MyArrayList.this.add(current, t);
        }
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

        List<Integer> addAllList = Lists.newArrayList(4, 5, 6);
        Iterable<Integer> integers = new Iterable<Integer>() {
            @Override
            public Iterator<Integer> iterator() {
                return addAllList.iterator();
            }
        };
        list.addAll(integers);
    }
}
