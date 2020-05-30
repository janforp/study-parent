package com.janita.java.base.thinkinjava._20_concurrent;

/**
 * IntGenerator
 *
 * @author zhucj
 * @since 20200528
 */
public abstract class IntGenerator {

    private volatile boolean canceled = false;

    public abstract int next();

    public void cancel() {
        canceled = true;
    }

    public boolean isCanceled() {
        return canceled;
    }
}
