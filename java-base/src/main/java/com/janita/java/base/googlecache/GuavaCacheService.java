package com.janita.java.base.googlecache;

import com.google.common.cache.Cache;
import com.google.common.cache.CacheBuilder;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.concurrent.TimeUnit;

/**
 * GuavaCacheService
 *
 * @author zhucj
 * @since 20210128
 */
public class GuavaCacheService {

    static Cache<Integer, String> cache = CacheBuilder.newBuilder()
            .expireAfterWrite(5, TimeUnit.SECONDS)
            .build();

    public static void main(String[] args) throws Exception {
        new Thread(() -> {
            while (true) {
                SimpleDateFormat sdf = new SimpleDateFormat("HH:mm:ss");
                System.out.println(sdf.format(new Date()) + " size: " + cache.size());
                try {
                    Thread.sleep(2000);
                } catch (InterruptedException e) {

                }
            }
        }).start();
        SimpleDateFormat sdf = new SimpleDateFormat("HH:mm:ss");
        cache.put(1, "a");
        System.out.println("写入 key:1 ,value:" + cache.getIfPresent(1));
        Thread.sleep(10000);
        cache.put(2, "b");
        System.out.println("写入 key:2 ,value:" + cache.getIfPresent(2));
        Thread.sleep(10000);
        System.out.println(sdf.format(new Date())
                + " sleep 10s , key:1 ,value:" + cache.getIfPresent(1));
        System.out.println(sdf.format(new Date())
                + " sleep 10s, key:2 ,value:" + cache.getIfPresent(2));
    }
}