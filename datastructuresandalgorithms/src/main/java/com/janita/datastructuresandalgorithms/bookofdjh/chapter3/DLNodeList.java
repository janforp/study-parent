package com.janita.datastructuresandalgorithms.bookofdjh.chapter3;

import com.janita.datastructuresandalgorithms.bookofdjh.chapter2.Position;

import java.util.Iterator;

/**
 * 基于双向链表{@link com.janita.datastructuresandalgorithms.bookofdjh.chapter2.deque.DLNode}实现的列表{@link List}
 *
 * @author zhucj
 * @since 20201126
 */
public class DLNodeList<E> implements List<E> {

    @Override
    public int getSize() {
        return 0;
    }

    @Override
    public boolean isEmpty() {
        return false;
    }

    @Override
    public Position<E> first() {
        return null;
    }

    @Override
    public Position<E> last() {
        return null;
    }

    @Override
    public Position<E> getNext(Position<E> p) {
        return null;
    }

    @Override
    public Position<E> getPrev(Position<E> p) {
        return null;
    }

    @Override
    public Position<E> insertFirst(E e) {
        return null;
    }

    @Override
    public Position<E> insertLast(E e) {
        return null;
    }

    @Override
    public Position<E> insertAfter(Position<E> p, E e) {
        return null;
    }

    @Override
    public Position<E> insertBefore(Position<E> p, E e) {
        return null;
    }

    @Override
    public E remove(Position<E> p) {
        return null;
    }

    @Override
    public E removeFirst() {
        return null;
    }

    @Override
    public E removeLast() {
        return null;
    }

    @Override
    public E replace(Position<E> p, E e) {
        return null;
    }

    @Override
    public Iterator<E> positions() {
        return null;
    }

    @Override
    public Iterator<E> elements() {
        return null;
    }
}
