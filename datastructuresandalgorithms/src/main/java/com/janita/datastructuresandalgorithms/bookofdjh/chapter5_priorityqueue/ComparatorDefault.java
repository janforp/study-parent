package com.janita.datastructuresandalgorithms.bookofdjh.chapter5_priorityqueue;

/**
 * ComparatorDefault
 *
 * @author zhucj
 * @since 20201126
 */
public class ComparatorDefault<T extends Comparable<T>> implements Comparator<T> {

    @Override
    public int compare(T a, T b) {
        return a.compareTo(b);
    }
}
