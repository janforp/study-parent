package com.janita.datastructuresandalgorithms.bookofdjh.chapter5_priorityqueue;

/**
 * Comparator
 *
 * @author zhucj
 * @since 20201126
 */
public interface Comparator<T> {

    /**
     * 如果
     * a > b,返回 正数如1
     * a = b,返回 0
     * a < b,返回 负数如-1
     *
     * @param a
     * @param b
     * @return
     */
    int compare(T a, T b);
}
