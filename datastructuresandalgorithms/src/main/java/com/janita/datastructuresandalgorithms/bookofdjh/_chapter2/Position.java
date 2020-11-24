package com.janita.datastructuresandalgorithms.bookofdjh._chapter2;

/**
 * Position
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
