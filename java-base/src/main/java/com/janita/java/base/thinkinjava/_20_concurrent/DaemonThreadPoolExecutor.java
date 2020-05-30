package com.janita.java.base.thinkinjava._20_concurrent;

import java.util.concurrent.SynchronousQueue;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.TimeUnit;

/**
 * DaemonThreadPoolExecutor
 *
 * @author zhucj
 * @since 20200528
 */
public class DaemonThreadPoolExecutor extends ThreadPoolExecutor {

    public DaemonThreadPoolExecutor() {
        super(0, Integer.MAX_VALUE, 60L, TimeUnit.SECONDS, new SynchronousQueue<Runnable>(), new DaemonThreadFactory());
    }
}