package com.janita.datastructuresandalgorithms.book3.chapter3;

import com.google.common.collect.Lists;
import org.junit.Assert;
import org.junit.Test;

import java.util.ConcurrentModificationException;
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

    public void addFirst(T x) {
        add(0, x);
    }

    public void addLast(T x) {
        add(size(), x);
    }

    public void removeFirst() {
        remove(0);
    }

    public void removeLast() {
        remove(size() - 1);
    }

    public T getFirst() {
        return get(0);
    }

    public T getLast() {
        return get(size() - 1);
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

    public boolean contains(T x) {
        if (x == null) {
            Node<T> p = beginMarker.next;
            while (p != endMarker) {
                if (p.data == null) {
                    return true;
                }
                p = p.next;
            }
        } else {
            Node<T> p = beginMarker.next;
            while (p != endMarker) {
                if (x.equals(p.data)) {
                    return true;
                }
                p = p.next;
            }
        }
        return false;
    }

    private Node<T> get(T x) {
        if (x == null) {
            Node<T> p = beginMarker.next;
            while (p != endMarker) {
                if (p.data == null) {
                    return p;
                }
                p = p.next;
            }
        } else {
            Node<T> p = beginMarker.next;
            while (p != endMarker) {
                if (x.equals(p.data)) {
                    return p;
                }
                p = p.next;
            }
        }
        return null;
    }

    private T remove(Node<T> p) {
        p.next.prev = p.prev;
        p.prev.next = p.next;
        theSize--;
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

    public void removeAll(Iterable<? extends T> items) {
        Iterator<? extends T> iterator = items.iterator();
        while (iterator.hasNext()) {
            T next = iterator.next();
            Node<T> tNode = get(next);
            if (tNode != null) {
                remove(tNode);
            }
        }
    }

    /**
     * 将lst中的所有项都删除，把他们放到 MyLinkedList.this的itr之前，而lst和this必须是不同的表
     * 城西必须是常数时间
     *
     * @param lst
     */
    public void splice(MyLinkedList<? extends T> lst) {
        for (T t : lst) {
            add(0, t);
        }
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

    public ListIterator<T> listIterator() {
        return new MyListIterator();
    }

    private class MyListIterator implements ListIterator<T> {

        private Node<T> current = beginMarker.next;

        private int currentIndex = 0;

        @Override
        public boolean hasNext() {
            return current != endMarker;
        }

        @Override
        public T next() {
            if (!hasNext()) {
                throw new NoSuchElementException();
            }
            T value = current.data;
            current = current.next;
            currentIndex++;
            return value;
        }

        @Override
        public boolean hasPrevious() {
            return current != beginMarker.next;
        }

        @Override
        public T previous() {
            if (!hasPrevious()) {
                throw new NoSuchElementException();
            }
            current = current.prev;
            currentIndex--;
            return current.data;
        }

        @Override
        public int nextIndex() {
            return currentIndex;
        }

        @Override
        public int previousIndex() {
            return currentIndex - 1;
        }

        @Override
        public void remove() {
            MyLinkedList.this.remove(current);
        }

        @Override
        public void set(T t) {
            current.data = t;
        }

        @Override
        public void add(T t) {
            MyLinkedList.this.add(t);
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

    @Test
    public void test() {
        MyLinkedList<Integer> linkedList = new MyLinkedList<>();
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
        MyLinkedList<Integer> linkedList = new MyLinkedList<>();
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
