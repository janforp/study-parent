package com.janita.datastructuresandalgorithms.bookofdjh._chapter2.deque;

/**
 * DequeDLNode
 *
 * @author zhucj
 * @since 20201126
 */
public class DequeDLNode<E> implements Deque<E> {

    /**
     * 指向头节点(哨兵)
     */
    private DLNode<E> header;

    /**
     * 指向尾节点(哨兵)
     */
    private DLNode<E> trailer;

    /**
     * 队列中元素的数目
     */
    private int size;

    public DequeDLNode() {
        header = new DLNode<>();
        trailer = new DLNode<>();
        header.setNext(trailer);
        trailer.setPrev(header);
        size = 0;
    }

    @Override
    public void insertFirst(E ele) {
        DLNode<E> second = header.getNext();
        DLNode<E> first = new DLNode<>(ele, header, second);
        second.setPrev(first);
        header.setNext(first);
        size++;
    }

    @Override
    public void insertLast(E ele) {
        DLNode<E> second = trailer.getPrev();
        DLNode<E> first = new DLNode<>(ele, second, trailer);
        second.setNext(first);
        trailer.setPrev(first);
        size++;
    }

    @Override
    public E removeFirst() {
        if (isEmpty()) {
            throw new RuntimeException("空了");
        }
        DLNode<E> first = header.getNext();
        DLNode<E> second = first.getNext();
        header.setNext(second);
        second.setPrev(header);
        size--;
        return first.getElem();
    }

    @Override
    public E removeLast() {
        if (isEmpty()) {
            throw new RuntimeException("空了");
        }
        DLNode<E> first = trailer.getPrev();
        DLNode<E> second = first.getPrev();
        trailer.setPrev(second);
        second.setNext(trailer);
        size--;
        return first.getElem();
    }

    @Override
    public E first() {
        if (isEmpty()) {
            throw new RuntimeException("空了");
        }
        return header.getNext().getElem();
    }

    @Override
    public E last() {
        if (isEmpty()) {
            throw new RuntimeException("空了");
        }
        return trailer.getPrev().getElem();
    }

    @Override
    public int getSize() {
        return size;
    }

    @Override
    public boolean isEmpty() {
        return size == 0;
    }

    @Override
    public void traversal() {
        DLNode<E> first = header.getNext();
        while (first != trailer) {
            System.out.println(first.getElem() + " ");
            first = first.getNext();
        }
        System.out.println();
    }
}
