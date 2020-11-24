package com.janita.datastructuresandalgorithms.bookofdjh._chapter2;

/**
 * Node
 *
 * @author zhucj
 * @since 20201126
 */
public class Node<E> implements Position<E> {

    /**
     * 数据对象
     */
    private E element;

    /**
     * 指向后继节点
     */
    private Node<E> next;

    public Node(E e, Node<E> next) {
        this.element = e;
        this.next = next;
    }

    public Node() {
        this(null, null);
    }

    @Override
    public E getElem() {
        return element;
    }

    @Override
    public E setElem(E e) {
        E old = this.element;
        this.element = e;
        return old;
    }

    public Node<E> getNext() {
        return this.next;
    }

    public void setNext(Node<E> next) {
        this.next = next;
    }
}
