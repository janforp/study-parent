package com.janita.datastructuresandalgorithms.bookofdjh.chapter3;

import com.janita.datastructuresandalgorithms.bookofdjh.chapter2.Position;

/**
 * Sequence
 *
 * @author zhucj
 * @since 20201126
 */
public interface Sequence<E> extends Vector<E>, List<E> {

    /**
     * 若0 <= r < getSize()，则返回秩为r的元素所在的位置;否则，报错
     *
     * @param r
     * @return
     */
    Position<E> rank2Pos(int r);

    /**
     * 若p是序列中的合法位置，则返回存放于p处的元素的秩;否则，报错
     *
     * @param p
     * @return
     */
    int pso2Rank(Position<E> p);
}