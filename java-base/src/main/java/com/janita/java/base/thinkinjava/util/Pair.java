package com.janita.java.base.thinkinjava.util;

/**
 * 类说明：Pair
 *
 * @author zhucj
 * @since 20200528
 */
public class Pair<K, V> {

    public final K key;

    public final V value;

    public Pair(K k, V v) {
        key = k;
        value = v;
    }
}
