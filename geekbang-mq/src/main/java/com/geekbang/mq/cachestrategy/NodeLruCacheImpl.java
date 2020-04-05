package com.geekbang.mq.cachestrategy;

import java.util.HashMap;

/**
 * 类说明：
 *
 * @author zhucj
 * @since 20200423
 */
public class NodeLruCacheImpl<K, V> extends BaseLruCache<K, V> {

    HashMap<K, Node> map = new HashMap<K, Node>();

    Node head = null;

    Node end = null;

    public NodeLruCacheImpl(int capacity, Storage<K, V> lowSpeedStorage) {
        super(capacity, lowSpeedStorage);
    }

    @Override
    public V get(K key) {
        // 查询缓存有没有
        V v1 = getValue(key);
        // 缓存内不存在
        if (v1 == null) {
            // 查询lowSpeedStorage(类似于磁盘)
            V v2 = lowSpeedStorage.get(key);
            if (v2 != null) {
                // lowSpeedStorage存在, 将它放入缓存
                set(key, v2);
            }
            return v2;
        }
        return v1;
    }

    @Override
    public void put(K key, V value) {

    }

    /**
     * 获取一个缓存数据之后，应该把这个数据在当前位置中移除，并重新添加到头的位置，这些都是在返回数据之前完成的
     */
    public V getValue(K key) {
        if (map.containsKey(key)) {
            Node n = map.get(key);
            remove(n);
            setHead(n);
            return n.value;
        }
        return null;
    }

    /**
     * 移除元素分为，N的前边和N的后边都要看是怎么样的情况
     */
    public void remove(Node n) {
        if (n.pre != null) {
            n.pre.next = n.next;
        } else {
            head = n.next;
        }

        if (n.next != null) {
            n.next.pre = n.pre;
        } else {
            end = n.pre;
        }

    }

    public void setHead(Node n) {
        //head原位置应该是指向第一个元素，现在把这个位置给n.next
        n.next = head;
        n.pre = null;

        if (head != null) {
            head.pre = n;
        }

        head = n;
        //判断头尾是够为空
        if (end == null) {
            end = head;
        }
    }

    /**
     * 设置看原位置是否有元素，如果有的话就替换，这证明使用过了，然后将其替换为头结点的元素，如果是一个新的节点就要判断它的大小是否符合规范
     */
    public void set(K key, V value) {
        if (map.containsKey(key)) {
            Node old = map.get(key);
            old.value = value;
            remove(old);
            setHead(old);
        } else {
            Node created = new Node(key, value);
            if (map.size() >= capacity) {
                map.remove(end.key);
                remove(end);
                setHead(created);
            } else {
                setHead(created);
            }
            map.put(key, created);
        }
    }

    class Node {

        K key;

        V value;

        Node pre;

        Node next;

        public Node(K key, V value) {
            this.key = key;
            this.value = value;
        }
    }
}
