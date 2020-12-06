package com.janita.datastructuresandalgorithms.book3.chapter3;

import com.janita.datastructuresandalgorithms.book3.chapter3.queue.MyQueue;
import lombok.AllArgsConstructor;

import java.util.NoSuchElementException;

/**
 * MySingleNodeQueue
 *
 * @author zhucj
 * @since 20201224
 */
public class MySingleNodeQueue<T> implements MyQueue<T> {

    private int size;

    private SingleNode<T> lastPushNode;

    @Override
    public boolean enqueue(T x) {
        size++;
        if (isEmpty()) {
            lastPushNode = new SingleNode<>(x, null);
            return true;
        }
        SingleNode<T> newNode = new SingleNode<>(x, lastPushNode);
        lastPushNode = newNode;
        return true;
    }

    @Override
    public T dequeue() {
        if (isEmpty()) {
            throw new NoSuchElementException();
        }
        if (size == 1) {
            T re = lastPushNode.data;
            lastPushNode = null;
            size--;
            return re;
        }
        SingleNode<T> node = lastPushNode;
        for (int i = 0; i < size - 2; i++) {
            node = node.next;
        }
        T re = node.next.data;
        node.next = null;
        size--;
        return re;
    }

    @Override
    public boolean isEmpty() {
        return size == 0;
    }

    @Override
    public int size() {
        return size;
    }

    @AllArgsConstructor
    private static class SingleNode<T> {

        private T data;

        private SingleNode<T> next;
    }
}
