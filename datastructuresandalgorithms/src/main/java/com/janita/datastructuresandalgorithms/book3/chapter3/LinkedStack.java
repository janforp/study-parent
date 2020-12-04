package com.janita.datastructuresandalgorithms.book3.chapter3;

import org.junit.Assert;
import org.junit.Test;

import java.util.NoSuchElementException;

/**
 * LinkedStack
 *
 * @author zhucj
 * @since 20201224
 */
public class LinkedStack<T> {

    private Node<T> header;

    private int size;

    public LinkedStack() {
        header = new Node<>(null, null);
        size = 0;
    }

    public boolean push(T x) {
        size++;
        if (header == null) {
            header = new Node<>(x, null);
            return true;
        }
        Node<T> p = new Node<>(x, header);
        header = p;
        return true;
    }

    public T pop() {
        if (size == 0) {
            throw new NoSuchElementException();
        }
        T data = header.data;
        header = header.next;
        size--;
        return data;
    }

    private static class Node<T> {

        private T data;

        private Node<T> next;

        public Node(T data, Node<T> next) {
            this.data = data;
            this.next = next;
        }
    }

    @Test
    public void test() {
        LinkedStack<Integer> stack = new LinkedStack<>();
        NoSuchElementException e = null;
        try {
            Integer pop = stack.pop();
        } catch (NoSuchElementException exception) {
            e = exception;
        }
        Assert.assertNotNull(e);
        stack.push(1);
        Integer pop = stack.pop();
        Assert.assertEquals(1, (int) pop);
        stack.push(2);
        stack.push(3);
        stack.push(4);
        stack.push(5);
        pop = stack.pop();
        Assert.assertEquals(5, (int) pop);
        stack.pop();
        stack.pop();
        pop = stack.pop();
        Assert.assertEquals(2, (int) pop);
        //抛出异常
        stack.pop();
    }
}
