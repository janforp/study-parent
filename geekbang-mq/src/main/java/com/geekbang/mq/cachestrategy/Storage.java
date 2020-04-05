package com.geekbang.mq.cachestrategy;

/**
 * 类说明：
 *
 * @author zhucj
 * @since 20200423
 */
public interface Storage<K, V> {

    /**
     * 根据提供的key来访问数据 * @param key 数据Key * @return 数据值
     */
    V get(K key);

    void put(K key, V value);
}
