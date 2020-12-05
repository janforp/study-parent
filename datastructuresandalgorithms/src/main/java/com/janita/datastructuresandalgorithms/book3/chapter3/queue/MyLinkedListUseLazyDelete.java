package com.janita.datastructuresandalgorithms.book3.chapter3.queue;

import com.google.common.collect.Lists;
import org.junit.Assert;
import org.junit.Test;

import java.util.Iterator;
import java.util.List;
import java.util.ListIterator;
import java.util.NoSuchElementException;

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

    private int deleteSize;

    public MyLinkedListUseLazyDelete() {
        beginMarker = new Node<>(null, null, null);
        endMarker = new Node<>(null, beginMarker, null);
        beginMarker.next = endMarker;
        theSize = 0;
        deleteSize = 0;
    }

    @Override
    public int size() {
        return theSize;
    }

    @Override
    public boolean isEmpty() {
        return theSize == 0;
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
        if (isEmpty()) {
            throw new NoSuchElementException();
        }
        Node<T> delete = beginMarker.next;
        while (delete.delete) {
            delete = delete.next;
        }
        return remove(delete);
    }

    @Override
    public T removeLast() {
        if (isEmpty()) {
            throw new NoSuchElementException();
        }
        Node<T> delete = endMarker.prev;
        while (delete.delete) {
            delete = delete.prev;
        }
        return remove(delete);
    }

    @Override
    public T getFirst() {
        if (isEmpty()) {
            throw new NoSuchElementException();
        }
        Node<T> first = beginMarker.next;
        while (first.delete) {
            first = first.next;
        }
        return first.data;
    }

    @Override
    public T getLast() {
        if (isEmpty()) {
            throw new NoSuchElementException();
        }
        Node<T> last = endMarker.prev;
        while (last.delete) {
            last = last.prev;
        }
        return last.data;
    }

    @Override
    public void add(int idx, T x) {
        Node<T> node = getNode(idx);
        addBefore(node, x);
    }

    private void addBefore(Node<T> node, T x) {
        theSize++;
        Node<T> newNode = new Node<>(x, null, null);
        node.prev.next = newNode;
        newNode.prev = node.prev;
        newNode.next = node;
        node.prev = newNode;
    }

    private Node<T> getNode(int idx) {
        if (idx < 0 || idx >= theSize) {
            throw new IndexOutOfBoundsException();
        }
        if (isEmpty()) {
            throw new NoSuchElementException();
        }
        Node<T> node = beginMarker.next;
        int i = 0;
        while (i < idx) {
            node = node.next;
            if (!node.delete) {
                i++;
            }
        }
        return node;
    }

    @Override
    public T get(int idx) {
        return getNode(idx).data;
    }

    @Override
    public T set(int idx, T x) {
        if (idx < 0 || idx >= theSize) {
            throw new IndexOutOfBoundsException();
        }
        Node<T> next = getNode(idx);
        T old = next.data;
        next.data = x;
        return old;
    }

    @Override
    public T remove(int idx) {
        Node<T> node = getNode(idx);
        return remove(node);
    }

    private T remove(Node<T> node) {
        if (isEmpty()) {
            throw new NoSuchElementException();
        }
        T data = node.data;
        node.delete = true;
        theSize--;
        deleteSize++;
        if (deleteSize == theSize) {
            doRemove();
        }
        return data;
    }

    private void doRemove() {
        Node<T> current = beginMarker.next;
        while (current != endMarker) {
            if (current.delete) {
                current.prev.next = current.next;
                current.next.prev = current.prev;
            }
            current = current.next;
        }
        deleteSize = 0;
    }

    @Override
    public boolean contains(T x) {
        if (x == null) {
            Node<T> current = beginMarker.next;
            while (current != endMarker) {
                if (current.data == null) {
                    return true;
                }
                current = current.next;
            }
        } else {
            Node<T> current = beginMarker.next;
            while (current != endMarker) {
                if (x.equals(current.data)) {
                    return true;
                }
                current = current.next;
            }
        }
        return false;
    }

    @Override
    public void removeAll(Iterable<? extends T> items) {
        for (T item : items) {
            remove(item);
        }
    }

    private void remove(T x) {
        if (x == null) {
            Node<T> current = beginMarker.next;
            while (current != endMarker) {
                if (current.data == null) {
                    remove(current);
                }
                current = current.next;
            }
        } else {
            Node<T> current = beginMarker.next;
            while (current != endMarker) {
                if (x.equals(current.data)) {
                    remove(current);
                }
                current = current.next;
            }
        }
    }

    @Override
    public void splice(MyList<T> lst) {
        for (T x : lst) {
            addFirst(x);
        }
    }

    @Override
    public Iterator<T> iterator() {
        return null;
    }

    private class IteratorImpl implements Iterator<T> {

        Node<T> current = beginMarker.next;

        @Override
        public boolean hasNext() {
            return current != endMarker;
        }

        @Override
        public T next() {
            if (!hasNext()) {
                throw new NoSuchElementException();
            }
            Node<T> result = current;
            current = current.next;
            return result.data;
        }
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

    @Test
    public void test() {
        MyLinkedListUseLazyDelete<Integer> linkedList = new MyLinkedListUseLazyDelete<>();
        linkedList.add(1);
        linkedList.add(2);
        Assert.assertFalse(linkedList.contains(3));
        Assert.assertTrue(linkedList.contains(1));
        Assert.assertFalse(linkedList.contains(null));
        linkedList.add(null);
        Assert.assertTrue(linkedList.contains(null));

        linkedList.add(4);
        linkedList.add(5);
        linkedList.add(6);

        List<Integer> addAllList = Lists.newArrayList(4, 5, 6);
        Iterable<Integer> integers = new Iterable<Integer>() {
            @Override
            public Iterator<Integer> iterator() {
                return addAllList.iterator();
            }
        };

        linkedList.removeAll(addAllList);
    }

    @Test
    public void test2() {
        MyLinkedListUseLazyDelete<Integer> linkedList = new MyLinkedListUseLazyDelete<>();
        linkedList.add(1);
        linkedList.add(2);
        linkedList.add(3);
        linkedList.add(4);
        linkedList.add(5);
        linkedList.add(6);
        Assert.assertEquals(6, linkedList.size());
        List<Integer> addAllList = Lists.newArrayList(4, 5, 6);
        Iterable<Integer> integers = new Iterable<Integer>() {
            @Override
            public Iterator<Integer> iterator() {
                return addAllList.iterator();
            }
        };
        linkedList.removeAll(addAllList);
        Assert.assertEquals(3, linkedList.size());
    }
}