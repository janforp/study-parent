package com.janita.datastructuresandalgorithms.book3.chapter3;

/**
 * MyStack
 *
 * @author zhucj
 * @since 20201224
 */
public interface MyStack<T> {

    int size();

    boolean isEmpty();

    void push(T x);

    T pop();

    T peek();
}
