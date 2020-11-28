package com.janita.datastructuresandalgorithms.bookofdjh.chapter5_priorityqueue;

/**
 * 条目接口
 *
 * 该类型的元素一般用于优先队列的元素
 *
 * @author zhucj
 * @since 20201126
 */
public interface Entry<K, V> {

    K getKey();

    K setKey(K key);

    V getValue();

    V setValue(V value);
}
