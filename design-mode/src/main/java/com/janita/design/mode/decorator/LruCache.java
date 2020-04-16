package com.janita.design.mode.decorator;

import java.util.LinkedHashMap;
import java.util.Map;

/**
 * 类说明：
 *
 * @author zhucj
 * @since 20200423
 */
public class LruCache implements Cache {

    private Cache delegate;

    private Map<String, String> lruMap;

    private String eldestKey;

    public LruCache(Cache delegate) {
        this.delegate = delegate;
        lruMap = new LinkedHashMap<String, String>(5, .75F, true) {
            @Override
            protected boolean removeEldestEntry(Map.Entry eldest) {
                boolean bigger = size() > 5;
                if (bigger) {
                    Object key = eldest.getKey();
                    eldestKey = (String) key;
                }
                return bigger;
            }
        };
    }

    @Override
    public String getId() {
        return delegate.getId();
    }

    @Override
    public Object getObject(String key) {
        lruMap.get(key);
        return delegate.getObject(key);
    }

    @Override
    public void putObject(String key, Object o) {
        delegate.putObject(key, o);
        cycleKeyList(key);
    }

    @Override
    public void removeObject(String key) {
        lruMap.remove(key);
        delegate.removeObject(key);
    }

    @Override
    public int getSize() {
        return delegate.getSize();
    }

    @Override
    public void clear() {
        delegate.clear();
        lruMap.clear();
    }

    private void cycleKeyList(String key) {
        lruMap.put(key, key);
        if (eldestKey != null) {
            delegate.removeObject(eldestKey);
            eldestKey = null;
        }
    }
}
