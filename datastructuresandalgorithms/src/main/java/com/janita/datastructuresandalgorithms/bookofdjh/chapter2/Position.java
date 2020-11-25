package com.janita.datastructuresandalgorithms.bookofdjh.chapter2;

/**
 * 抽象出位置(Position)这一概念，使我们既能够保持链表结构高效性，而又不致违背面向对 象的设计原则
 * 1.按线性次序排列的一组位置，就构成了一个列表
 *
 * @author zhucj
 * @since 20201126
 */
public interface Position<E> {

    /**
     * 返回存放于该位置的元素
     *
     * @return
     */
    E getElem();

    /**
     * 将给定元素存放至该位置，返回此前存放的元素
     *
     * @param e
     */
    E setElem(E e);
}