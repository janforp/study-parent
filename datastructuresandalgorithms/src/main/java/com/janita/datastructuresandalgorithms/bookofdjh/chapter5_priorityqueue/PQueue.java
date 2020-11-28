package com.janita.datastructuresandalgorithms.bookofdjh.chapter5_priorityqueue;

/**
 * PQueue
 *
 * @author zhucj
 * @since 20201126
 */
public interface PQueue<K, V> {

    int getSize();

    boolean isEmpty();

    /**
     * 返回最小条目
     *
     * @return
     */
    Entry<K, V> getMin();

    /**
     * 将value与key合并为一个条目，并返回该条目
     *
     * @param key
     * @param value
     * @return
     */
    Entry<K, V> insert(K key, V value);

    /**
     * 从中摘除最小条目并返回
     *
     * @return
     */
    Entry<K, V> delMin();
}
