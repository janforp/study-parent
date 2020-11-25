package com.janita.datastructuresandalgorithms.bookofdjh.chapter3;

/**
 * Vector
 *
 * @author zhucj
 * @since 20201126
 */
public interface Vector<E> {

    /**
     * 报告向量中的元素数目
     * 输入:无
     * 输出:非负整数
     *
     * @return
     */
    int getSize();

    /**
     * 判断向量是否为空
     * 输入:无
     * 输出:布尔量
     *
     * @return
     */
    boolean isEmpty();

    /**
     * 若 0 ≤ r < getSize()，则返回秩为 r 的那个元素 否则，报错
     * 输入:一个整数
     * 输出:对象
     *
     * @param r
     * @return
     */
    E getAtRank(int r);

    /**
     * 若 0 ≤ r < getSize()，则将秩为 r 的元素替换为 e，并返回原来的元素 否则，报错
     * 输入:一个整数和一个对象
     * 输出:对象
     *
     * @param r
     * @param obj
     * @return
     */
    E replaceAtRank(int r, E obj);

    /**
     * 若 0 ≤ r ≤ getSize()，则将 e 插入向量中，作为秩为 r 的元素(原秩不小于 r 的元素顺次后移); 并返回该元素
     * 否则，报错
     * 输入:一个整数和一个对象
     * 输出:对象
     *
     * @param r
     * @param obj
     * @return
     */
    E insertAtRank(int r, E obj);

    /**
     * 若 0 ≤ r < getSize()，则删除秩为 r 的那个元素并返回之(原秩大于 r 的元素顺次前移) 否则，报错
     * 输入:一个整数
     * 输出:对象
     *
     * @param r
     * @return
     */
    E removeAtRank(int r);
}