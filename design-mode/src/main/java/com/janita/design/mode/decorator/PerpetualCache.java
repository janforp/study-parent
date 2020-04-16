package com.janita.design.mode.decorator;

import java.util.HashMap;
import java.util.Map;

/**
 * 类说明：一个最普通的缓存，永久缓存
 *
 * @author zhucj
 * @since 20200423
 */
public class PerpetualCache implements Cache {

    private String id;

    private Map<String, Object> cacheMap = new HashMap<>();

    public PerpetualCache(String id) {
        this.id = id;
    }

    @Override
    public String getId() {
        return id;
    }

    @Override
    public Object getObject(String key) {
        return cacheMap.get(key);
    }

    @Override
    public void putObject(String key, Object o) {
        cacheMap.put(key, o);
    }

    @Override
    public void removeObject(String key) {
        cacheMap.remove(key);
    }

    @Override
    public int getSize() {
        return cacheMap.size();
    }

    @Override
    public void clear() {
        cacheMap.clear();
    }
}
