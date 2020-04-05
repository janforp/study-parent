package com.geekbang.mq.cachestrategy;

/**
 * 类说明：LRU缓存
 *
 * @author zhucj
 * @since 20200423
 */
public abstract class BaseLruCache<K, V> implements Storage<K, V> {

    /**
     * 缓存容量
     */
    protected final int capacity;

    /**
     * 低速存储，所有的数据都可以从这里读到
     */
    protected final Storage<K, V> lowSpeedStorage;

    public BaseLruCache(int capacity, Storage<K, V> lowSpeedStorage) {
        this.capacity = capacity;
        this.lowSpeedStorage = lowSpeedStorage;
    }
}
