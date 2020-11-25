package com.janita.datastructuresandalgorithms.bookofdjh.chapter2.deque;

import com.janita.datastructuresandalgorithms.bookofdjh.chapter2.Position;

/**
 * 在基于NLNode类实现双向链表的时候，为了使编程更加简洁，通常我们都要在最前端和最后 端各设置一个哑元节点(Dummy node )。
 * 这两个节点分别称作头节点(Header node )和尾节点 (Trailer node)(一)，起哨兵(Sentinel)的作用。也就是说，它们并不存储任何实质的数据对象，头
 * (尾)节点的next(prev)引用指向首(末)节点，而prev(next)引用为空。如此构成的双向链表
 * 结构，如 图二.10 所示。
 *
 * @author zhucj
 * @since 20201126
 */
public class DLNode<E> implements Position<E> {

    /**
     * 数据
     */
    private E element;

    /**
     * 前驱
     */
    private DLNode<E> prev;

    /**
     * 后继
     */
    private DLNode<E> next;

    public DLNode() {
        this(null, null, null);
    }

    public DLNode(E e, DLNode<E> p, DLNode<E> n) {
        element = e;
        prev = p;
        next = n;
    }

    @Override
    public E getElem() {
        return element;
    }

    @Override
    public E setElem(E e) {
        E old = element;
        this.element = e;
        return old;
    }

    public DLNode<E> getNext() {
        return next;
    }

    public DLNode<E> getPrev() {
        return prev;
    }

    public void setNext(DLNode<E> newNext) {
        next = newNext;
    }

    public void setPrev(DLNode<E> newPrev) {
        prev = newPrev;
    }
}