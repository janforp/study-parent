package com.janita.datastructuresandalgorithms.book3.chapter3;

/**
 * MyList
 *
 * @author zhucj
 * @since 20201224
 */
public interface MySelfAdjustingList<T> {

    int size();

    boolean isEmpty();

    boolean add(T x);

    T remove(int idx);

    T find(T x);
}
