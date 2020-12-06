package com.janita.datastructuresandalgorithms.book3.chapter3;

import lombok.AllArgsConstructor;

import java.util.NoSuchElementException;

/**
 * Exercise3_28_MyDeque
 *
 * @author zhucj
 * @since 20201224
 */
public class Exercise3_28_MyDeque<T> {

    private DoubleNode<T> beginMarker;

    private DoubleNode<T> endMarker;

    private int size;

    public Exercise3_28_MyDeque() {
        beginMarker = new DoubleNode<>(null, null, null);
        endMarker = new DoubleNode<>(null, beginMarker, null);
        beginMarker.next = endMarker;
        size = 0;
    }

    public void push(T x) {
        size++;
        DoubleNode<T> newNode = new DoubleNode<>(x, beginMarker, beginMarker.next);
        beginMarker.next.prev = newNode;
        beginMarker.next = newNode;
    }

    public T pop() {
        if (size == 0) {
            throw new NoSuchElementException();
        }
        size--;
        DoubleNode<T> result = beginMarker.next;
        beginMarker.next = result.next;
        result.next.prev = beginMarker;
        return result.data;
    }

    public void inject(T x) {
        size++;
        DoubleNode<T> newNode = new DoubleNode<>(x, endMarker.prev, endMarker);
        endMarker.prev.next = newNode;
        endMarker.prev = newNode;
    }

    public T eject() {
        if (size == 0) {
            throw new NoSuchElementException();
        }
        size--;
        DoubleNode<T> result = endMarker.prev;
        endMarker.prev = result.prev;
        result.prev.next = endMarker;
        return result.data;
    }

    public void printReverse() {
        if (size == 0) {
            System.out.println("空表");
        }
        DoubleNode<T> current = endMarker.prev;
        while (current != beginMarker) {
            System.out.println("当前元素为 " + current.data);
            current = current.prev;
        }
    }

    @AllArgsConstructor
    private static class DoubleNode<T> {

        private T data;

        private DoubleNode<T> prev;

        private DoubleNode<T> next;
    }
}
