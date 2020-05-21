package com.janita.java.base.thinkinjava._15_genericity;

import java.util.Iterator;
import java.util.LinkedList;

/**
 * 类说明：SimpleQueue
 *
 * @author zhucj
 * @since 20200528
 */
public class SimpleQueue<T> implements Iterable<T> {

    private LinkedList<T> storage = new LinkedList<T>();

    public void add(T t) {
        storage.offer(t);
    }

    public T get() {
        return storage.poll();
    }

    public Iterator<T> iterator() {
        return storage.iterator();
    }
}
