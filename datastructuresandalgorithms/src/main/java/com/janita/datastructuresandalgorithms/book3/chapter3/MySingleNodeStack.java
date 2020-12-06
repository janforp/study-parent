package com.janita.datastructuresandalgorithms.book3.chapter3;

import lombok.AllArgsConstructor;

import java.util.NoSuchElementException;

/**
 * 不使用头节点跟为节点高效实现stack
 *
 * @author zhucj
 * @since 20201224
 */
public class MySingleNodeStack<T> implements MyStack<T> {

    private int size;

    private SingleNode<T> lastPushNode;

    @Override
    public int size() {
        return size;
    }

    @Override
    public boolean isEmpty() {
        return size == 0;
    }

    @Override
    public void push(T x) {
        size++;
        SingleNode<T> newNode = new SingleNode<>(x, null);
        if (isEmpty()) {
            lastPushNode = newNode;
            return;
        }
        newNode.next = lastPushNode;
        lastPushNode = newNode;
    }

    @Override
    public T pop() {
        if (isEmpty()) {
            throw new NoSuchElementException();
        }
        size--;
        SingleNode<T> next = lastPushNode.next;
        T res = lastPushNode.data;
        lastPushNode = next;
        return res;
    }

    @Override
    public T peek() {
        if (isEmpty()) {
            throw new NoSuchElementException();
        }
        return lastPushNode.data;
    }

    @AllArgsConstructor
    private static class SingleNode<T> {

        private T data;

        private SingleNode<T> next;
    }
}
