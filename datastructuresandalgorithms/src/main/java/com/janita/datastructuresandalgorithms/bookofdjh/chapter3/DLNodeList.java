package com.janita.datastructuresandalgorithms.bookofdjh.chapter3;

import com.janita.datastructuresandalgorithms.bookofdjh.chapter2.Position;
import com.janita.datastructuresandalgorithms.bookofdjh.chapter2.deque.DLNode;

import java.util.Iterator;

/**
 * 基于双向链表{@link com.janita.datastructuresandalgorithms.bookofdjh.chapter2.deque.DLNode}实现的列表{@link List}
 *
 * @author zhucj
 * @since 20201126
 */
public class DLNodeList<E> implements List<E> {

    /**
     * 列表的实际规模
     */
    private int numElem;

    /**
     * 哨兵:首节点+末节点
     */
    private DLNode<E> header;

    /**
     * 哨兵:首节点+末节点
     */
    private DLNode<E> trailer;

    public DLNodeList() {
        this.numElem = 0;
        //首、末节点相互链接
        header = new DLNode<>(null, null, null);
        trailer = new DLNode<>(null, header, null);
        header.setNext(trailer);
    }

    protected DLNode<E> checkPosition(Position<E> p) {
        if (p == null) {
            throw new NullPointerException("p空的");
        }
        if (p == header) {
            throw new IllegalArgumentException("意外:头节点哨兵位置非法");
        }
        if (p == trailer) {
            throw new IllegalArgumentException("意外:尾节点哨兵位置非法");
        }
        return (DLNode<E>) p;
    }

    @Override
    public int getSize() {
        return numElem;
    }

    @Override
    public boolean isEmpty() {
        return numElem == 0;
    }

    @Override
    public Position<E> first() {
        if (isEmpty()) {
            throw new RuntimeException("空了");
        }
        return header.getNext();
    }

    @Override
    public Position<E> last() {
        if (isEmpty()) {
            throw new RuntimeException("空了");
        }
        return trailer.getPrev();
    }

    @Override
    public Position<E> getNext(Position<E> p) {
        DLNode<E> v = checkPosition(p);
        DLNode<E> next = v.getNext();
        if (next == trailer) {
            throw new IllegalArgumentException("意外:企图越过列表后端");
        }
        return next;
    }

    @Override
    public Position<E> getPrev(Position<E> p) {
        DLNode<E> v = checkPosition(p);
        DLNode<E> prev = v.getPrev();
        if (prev == header) {
            throw new IllegalArgumentException("意外:企图越过列表前端");
        }
        return prev;
    }

    @Override
    public Position<E> insertFirst(E e) {
        numElem++;
        DLNode<E> newNode = new DLNode<>(e, header, header.getNext());
        header.getNext().setPrev(newNode);
        header.setNext(newNode);
        return newNode;
    }

    @Override
    public Position<E> insertLast(E e) {
        numElem++;
        DLNode<E> newNode = new DLNode<>(e, trailer.getPrev(), trailer);
        if (null == trailer.getPrev()) {
            System.out.println("!!!Prev of trailer is NULL!!!");
        }
        trailer.getPrev().setNext(newNode);
        trailer.setPrev(newNode);
        return newNode;
    }

    @Override
    public Position<E> insertAfter(Position<E> p, E e) {
        DLNode<E> v = checkPosition(p);
        numElem++;
        DLNode<E> newNode = new DLNode<>(e, v, v.getNext());
        v.getNext().setPrev(newNode);
        v.setNext(newNode);
        return newNode;
    }

    /**
     * 1,2,3....before v
     * 1,2,3....before, newNode, v
     *
     * before <- newNode -> v
     *
     * @param p
     * @param e
     * @return
     */
    @Override
    public Position<E> insertBefore(Position<E> p, E e) {
        DLNode<E> v = checkPosition(p);
        numElem++;
        DLNode<E> newNode = new DLNode<>(e, v.getPrev(), v);
        v.getPrev().setNext(newNode);
        v.setPrev(newNode);
        return newNode;
    }

    @Override
    public E remove(Position<E> p) {
        DLNode<E> v = checkPosition(p);
        numElem--;
        v.getPrev().setNext(v.getNext());
        v.getNext().setPrev(v.getPrev());

        v.setNext(null);
        v.setPrev(null);
        return p.getElem();
    }

    @Override
    public E removeFirst() {
        return remove(header.getNext());
    }

    @Override
    public E removeLast() {
        return remove(trailer.getPrev());
    }

    @Override
    public E replace(Position<E> p, E e) {
        DLNode<E> v = checkPosition(p);
        E old = v.getElem();
        v.setElem(e);
        return old;
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
