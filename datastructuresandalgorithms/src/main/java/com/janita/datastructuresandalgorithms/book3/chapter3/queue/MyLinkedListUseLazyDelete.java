package com.janita.datastructuresandalgorithms.book3.chapter3.queue;

import java.util.Iterator;
import java.util.ListIterator;

/**
 * MyLinkedList
 *
 * @author zhucj
 * @since 20201224
 */
@SuppressWarnings("all")
public class MyLinkedListUseLazyDelete<T> implements MyList<T> {

    /**
     * 标记节点，哨兵节点，头节点
     */
    private Node<T> beginMarker;

    /**
     * 标记节点，哨兵节点，尾节点
     */
    private Node<T> endMarker;

    /**
     * 规模
     */
    private int theSize;

    public MyLinkedListUseLazyDelete() {
        beginMarker = new Node<>(null, null, null);
        endMarker = new Node<>(null, beginMarker, null);
        beginMarker.next = endMarker;
        theSize = 0;
    }

    @Override
    public boolean add(T x) {
        Node<T> newNode = new Node<>(x, null, null);
        Node<T> lastPre = endMarker.prev;
        lastPre.next = newNode;
        newNode.next = endMarker;
        endMarker.prev = newNode;
        newNode.prev = lastPre;
        theSize++;
        return true;
    }

    @Override
    public void addFirst(T x) {
        Node<T> newNode = new Node<>(x, beginMarker, null);
        Node<T> lastNex = beginMarker.next;
        beginMarker.next = newNode;
        newNode.next = lastNex;
        lastNex.prev = newNode;
        newNode.prev = beginMarker;
        theSize++;
    }

    @Override
    public void addLast(T x) {
        add(x);
    }

    @Override
    public T removeFirst() {
        Node<T> resultNode = beginMarker.next;
        resultNode.delete = true;
        return resultNode.data;
    }

    @Override
    public T removeLast() {
        return null;
    }

    @Override
    public T getFirst() {
        return null;
    }

    @Override
    public T getLast() {
        return null;
    }

    @Override
    public void add(int idx, T x) {

    }

    @Override
    public T get(int idx) {
        return null;
    }

    @Override
    public T set(int idx, T x) {
        return null;
    }

    @Override
    public T remove(int idx) {
        return null;
    }

    @Override
    public boolean contains(T x) {
        return false;
    }

    @Override
    public void removeAll(Iterable<? extends T> items) {

    }

    @Override
    public void splice(MyList<T> lst) {

    }

    @Override
    public Iterator<T> iterator() {
        return null;
    }

    @Override
    public ListIterator<T> listIterator() {
        return null;
    }

    private static class Node<T> {

        public Node(T d, Node<T> p, Node<T> n) {
            data = d;
            prev = p;
            next = n;
            delete = false;
        }

        private T data;

        private Node<T> prev;

        private Node<T> next;

        private boolean delete;
    }

}