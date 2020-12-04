package com.janita.datastructuresandalgorithms.book3.chapter3;

import java.util.ConcurrentModificationException;
import java.util.Iterator;
import java.util.NoSuchElementException;

/**
 * MyLinkedList
 *
 * @author zhucj
 * @since 20201224
 */
@SuppressWarnings("all")
public class MyLinkedList<T> implements Iterable<T> {

    /**
     * 标记节点，哨兵节点，头节点
     */
    private Node<T> beginMarker;

    /**
     * 标记节点，哨兵节点，尾节点
     */
    private Node<T> endMarker;

    private int theSize;

    private int modCount;

    public MyLinkedList() {
        doClear();
    }

    private void doClear() {
        beginMarker = new Node<>(null, null, null);
        endMarker = new Node<>(null, beginMarker, null);
        beginMarker.next = endMarker;

        theSize = 0;
        modCount++;
    }

    public void clear() {
        doClear();
    }

    public int size() {
        return theSize;
    }

    public boolean isEmpty() {
        return size() == 0;
    }

    public boolean add(T x) {
        add(size(), x);
        return true;
    }

    private void add(int idx, T x) {
        addBefore(getNode(idx, 0, size()), x);
    }

    public T get(int idx) {
        return getNode(idx).data;
    }

    public T set(int idx, T newVal) {
        Node<T> p = getNode(idx);
        T oldVal = p.data;
        p.data = newVal;
        return oldVal;
    }

    public T remove(int idx) {
        return remove(getNode(idx));
    }

    private T remove(Node<T> p) {
        p.next.prev = p.prev;
        p.prev.next = p.next;
        theSize++;
        modCount++;
        return p.data;
    }

    private void addBefore(Node<T> p, T x) {
        Node<T> newNode = new Node<>(x, p.prev, p);
        newNode.prev.next = newNode;
        p.prev = newNode;

        //以上三句可以使用下面这一句代替
        //p.prev = p.prev.next = new Node<>(x,p.prev, p);

        theSize++;
        modCount++;
    }

    private Node<T> getNode(int idx, int lower, int upper) {
        if (idx < lower || idx > upper) {
            throw new IndexOutOfBoundsException();
        }
        Node<T> p;
        if (idx < size() / 2) {
            p = beginMarker.next;
            for (int i = 0; i < idx; i++) {
                p = p.next;
            }
        } else {
            p = endMarker;
            for (int i = size(); i > idx; i--) {
                p = p.prev;
            }
        }
        return p;
    }

    private Node<T> getNode(int idx) {
        return getNode(idx, 0, size() - 1);
    }

    @Override
    public Iterator<T> iterator() {
        return new LinkedListIterator();
    }

    private class LinkedListIterator implements Iterator<T> {

        /**
         * 存储当前节点的引用
         */
        private Node<T> current = beginMarker.next;

        private int expectedModeCount = modCount;

        private boolean okToRemove = false;

        @Override
        public boolean hasNext() {
            return current != endMarker;
        }

        @Override
        public T next() {
            if (modCount != expectedModeCount) {
                throw new ConcurrentModificationException();
            }
            if (!hasNext()) {
                throw new NoSuchElementException();
            }
            T nextItem = current.data;
            current = current.next;
            okToRemove = true;
            return nextItem;
        }

        @Override
        public void remove() {
            if (modCount != expectedModeCount) {
                throw new ConcurrentModificationException();
            }
            if (!okToRemove) {
                throw new IllegalStateException();
            }
            MyLinkedList.this.remove(current.prev);
            expectedModeCount++;
            okToRemove = false;
        }
    }

    private static class Node<T> {

        public Node(T d, Node<T> p, Node<T> n) {
            data = d;
            prev = p;
            next = n;
        }

        private T data;

        private Node<T> prev;

        private Node<T> next;
    }
}
