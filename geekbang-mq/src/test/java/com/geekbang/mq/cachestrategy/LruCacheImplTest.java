package com.geekbang.mq.cachestrategy;

import org.junit.Assert;
import org.junit.Test;

public class LruCacheImplTest {

    @Test
    public void get() {
        Storage<String,String> storage = new LruCacheImpl<>(2, null);
        storage.put("1", "1");
        storage.put("2", "2");
        storage.put("3", "3");
        Assert.assertNull(storage.get("1"));
        Assert.assertEquals("2", storage.get("2"));
        Assert.assertEquals("3", storage.get("3"));
    }

    @Test
    public void put() {
        Storage<String,String> storage = new LruCacheImpl<>(2, null);
        storage.put("1", "1");
        storage.put("2", "2");
        storage.put("3", "3");
        Assert.assertNull(storage.get("1"));
        Assert.assertEquals("2", storage.get("2"));
        Assert.assertEquals("3", storage.get("3"));
    }
}
