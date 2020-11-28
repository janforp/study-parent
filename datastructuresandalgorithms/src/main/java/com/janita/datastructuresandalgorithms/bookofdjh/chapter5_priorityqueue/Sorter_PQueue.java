package com.janita.datastructuresandalgorithms.bookofdjh.chapter5_priorityqueue;

import com.janita.datastructuresandalgorithms.bookofdjh.chapter1.Sorter;
import com.janita.datastructuresandalgorithms.bookofdjh.chapter3.Sequence;

/**
 * Sorter_PQueue
 *
 * @author zhucj
 * @since 20201126
 */
public class Sorter_PQueue<K extends Comparable<K>, V> implements Sorter<K> {

    private Comparator<K> C;

    public Sorter_PQueue() {
        this(new ComparatorDefault<>());
    }

    public Sorter_PQueue(Comparator<K> comp) {
        this.C = comp;
    }

    @Override
    public void sort(Sequence<K> s) {
    }
}
