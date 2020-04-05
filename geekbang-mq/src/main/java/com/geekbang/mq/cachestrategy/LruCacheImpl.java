package com.geekbang.mq.cachestrategy;

import java.util.LinkedHashMap;
import java.util.Map;

/**
 * 类说明：
 *
 * @author zhucj
 * @since 20200423
 */
public class LruCacheImpl<K, V> extends BaseLruCache<K, V> {

    private LinkedHashMap<K, V> cacheContainer = new LinkedHashMap<K, V>() {
        @Override
        protected boolean removeEldestEntry(Map.Entry<K, V> eldest) {
            return cacheContainer.size() > 2;
        }
    };

    public LruCacheImpl(int capacity, Storage<K, V> lowSpeedStorage) {
        super(capacity, lowSpeedStorage);
    }

    @Override
    public V get(K key) {
        return cacheContainer.get(key);
    }

    @Override
    public void put(K key, V value) {
        cacheContainer.put(key, value);
    }
}
