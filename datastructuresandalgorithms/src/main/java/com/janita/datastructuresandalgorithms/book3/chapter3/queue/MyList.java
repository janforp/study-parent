package com.janita.datastructuresandalgorithms.book3.chapter3.queue;

import java.util.Iterator;
import java.util.ListIterator;

/**
 * MyList
 *
 * @author zhucj
 * @since 20201224
 */
public interface MyList<T> extends Iterable<T> {

    int size();

    boolean isEmpty();

    boolean add(T x);

    void addFirst(T x);

    void addLast(T x);

    T removeFirst();

    T removeLast();

    T getFirst();

    T getLast();

    void add(int idx, T x);

    T get(int idx);

    T set(int idx, T x);

    T remove(int idx);

    boolean contains(T x);

    void removeAll(Iterable<? extends T> items);

    void splice(MyList<T> lst);

    Iterator<T> iterator();

    ListIterator<T> listIterator();
}
