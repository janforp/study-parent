package com.janita.design.mode.decorator;

/**
 * 类说明：
 *
 * @author zhucj
 * @since 20200423
 */
public class ScheduledCache implements Cache {

    private Cache delegate;

    private long expireMs;

    private Long lastClearTime;

    public ScheduledCache(Cache cache, long expireMs) {
        this.delegate = cache;
        this.expireMs = expireMs;
        this.lastClearTime = System.currentTimeMillis();
    }

    @Override
    public String getId() {
        return delegate.getId();
    }

    @Override
    public Object getObject(String key) {
        ensureScheduled();
        return delegate.getObject(key);
    }

    private void ensureScheduled() {
        long now = System.currentTimeMillis();
        long escape = now - lastClearTime;
        if (escape > expireMs) {
            delegate.clear();
        }
    }

    @Override
    public void putObject(String key, Object o) {
        ensureScheduled();
        delegate.putObject(key, o);
    }

    @Override
    public void removeObject(String key) {
        ensureScheduled();
        delegate.removeObject(key);
    }

    @Override
    public int getSize() {
        return delegate.getSize();
    }

    @Override
    public void clear() {
        delegate.clear();
    }
}
