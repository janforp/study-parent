package com.janita.datastructuresandalgorithms.bookofdjh.chapter5_priorityqueue;

import lombok.AllArgsConstructor;
import lombok.Getter;

/**
 * EntryDefault
 *
 * @author zhucj
 * @since 20201126
 */
@AllArgsConstructor
public class EntryDefault<K, V> implements Entry<K, V> {

    @Getter
    protected K key;

    @Getter
    protected V value;

    @Override
    public K setKey(K key) {
        K old = this.key;
        this.key = key;
        return old;
    }

    @Override
    public V setValue(V value) {
        V old = this.value;
        this.value = value;
        return old;
    }
}
