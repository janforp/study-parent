package com.janita.datastructuresandalgorithms.book3.chapter3.queue;

import org.junit.Assert;
import org.junit.Test;

import java.util.NoSuchElementException;

/**
 * MyLinkedQueue
 *
 * @author zhucj
 * @since 20201224
 */
public class MyLinkedQueue<T> implements MyQueue<T> {

    private Node<T> head;

    private Node<T> tail;

    private int theSize;

    public MyLinkedQueue() {
        head = new Node<>(null, null, null);
        tail = new Node<>(null, head, null);
        head.next = tail;
        theSize = 0;
    }

    @Override
    public boolean enqueue(T x) {
        if (theSize == 0) {
            Node<T> newNode = new Node<>(x, head, tail);
            head.next = newNode;
            tail.prev = newNode;
            theSize++;
            return true;
        }
        //插入到队尾
        theSize++;
        Node<T> newNode = new Node<>(x, null, null);
        tail.prev.next = newNode;
        newNode.next = tail;
        newNode.prev = tail.prev;
        tail.prev = newNode;
        return true;
    }

    @Override
    public T dequeue() {
        if (isEmpty()) {
            throw new NoSuchElementException();
        }
        theSize--;
        Node<T> next = head.next;
        head.next = next.next;
        next.next.prev = head;
        return next.data;
    }

    @Override
    public boolean isEmpty() {
        return theSize == 0;
    }

    @Override
    public int size() {
        return theSize;
    }

    private static class Node<T> {

        private T data;

        private Node<T> prev;

        private Node<T> next;

        public Node(T data, Node<T> prev, Node<T> next) {
            this.data = data;
            this.prev = prev;
            this.next = next;
        }
    }

    @Test
    public void test1() {
        MyQueue<Integer> queue = new MyLinkedQueue<>();
        queue.enqueue(1);
        Integer dequeue = queue.dequeue();
        Assert.assertEquals(1, (int) dequeue);
        Assert.assertTrue(queue.isEmpty());
        queue = new MyLinkedQueue<>();
        queue.enqueue(2);
        queue.enqueue(3);
        queue.enqueue(4);
        Assert.assertEquals(3, queue.size());
        Assert.assertEquals(2, (int) queue.dequeue());
        Assert.assertEquals(3, (int) queue.dequeue());
        Assert.assertEquals(4, (int) queue.dequeue());
        NoSuchElementException exception = null;
        try {
            queue.dequeue();
        } catch (NoSuchElementException e) {
            exception = e;
        }
        Assert.assertNotNull(exception);
    }
}