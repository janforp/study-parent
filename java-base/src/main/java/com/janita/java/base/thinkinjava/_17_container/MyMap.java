package com.janita.java.base.thinkinjava._17_container;

import java.util.AbstractMap;
import java.util.Set;

/**
 * 类说明：MyMap
 *
 * @author zhucj
 * @since 20200528
 */
public class MyMap<K, V> extends AbstractMap<K, V> {

    @Override
    public Set<Entry<K, V>> entrySet() {
        return null;
    }
}

class MyMapTest {

    public static void main(String[] args) {

        MyMap<Object, Object> myMap = new MyMap<>();
        Set<Object> objects = myMap.keySet();
        System.out.println(objects);
    }
}