package com.janita.java.base.concurrent.locks;

import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.locks.ReadWriteLock;
import java.util.concurrent.locks.ReentrantReadWriteLock;

/**
 * 类说明：CacheDemo
 *
 * @author zhucj
 * @since 20200423
 */
public class CacheDemo {

    private final Map<String, Object> cache = new HashMap<>();

    private ReadWriteLock rwl = new ReentrantReadWriteLock();

    public static void main(String[] args) {

        CacheDemo demo = new CacheDemo();
        Object data = demo.getData("1");
        demo.getData("1");
    }

    public Object getData(String key) {
        Object data;
        rwl.readLock().lock();
        try {
            data = cache.get(key);
            if (data != null) {
                return data;
            }
            rwl.readLock().unlock();
            //TODO 写数据之前上写锁，此处有可能有多个线程同时获取到写锁？
            rwl.writeLock().lock();
            try {
                //再次检查
                if (data == null) {
                    System.out.println("去数据库查询");
                    data = "去数据库查询的数据";
                    cache.put(key, data);
                }
            } finally {
                rwl.writeLock().unlock();
            }
            //写完之后上读锁，避免后面进来的其他线程又开始进行写
            rwl.readLock().lock();
        } finally {
            rwl.readLock().unlock();
        }
        return data;
    }
}
