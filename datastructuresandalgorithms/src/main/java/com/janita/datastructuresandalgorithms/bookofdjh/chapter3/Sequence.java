package com.janita.datastructuresandalgorithms.bookofdjh.chapter3;

import com.janita.datastructuresandalgorithms.bookofdjh.chapter2.Position;

/**
 * 与链表相对称地，我们也可以利用数组来实现序列
 * ⎯⎯将序列 S 中的各个元素分别存放到数组 A[]对应的单元中。
 * 具体地，每一位置对象除了设有一个引用指向数组 A[]，
 * 同时还保存一个下标 i。
 * 这就使得 getElem(p)方法可以直接返回 A[i]。
 * 这种实现也有不足:A[]中的单元无法直接得到各自对 应的位置。
 * 比如，在执行 insertFirst()操作时，我们将无法通知 S 中相关的那些位置，以便它们将各 自的秩加一
 * (你应该还记得，序列中的位置总是相对于其前后邻居而言的，而不是它们的秩)。因此， 为了利用数组来实现一个通用的序列结构，我们只能另想办法。
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