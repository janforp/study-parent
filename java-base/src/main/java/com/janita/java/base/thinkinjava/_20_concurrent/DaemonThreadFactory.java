package com.janita.java.base.thinkinjava._20_concurrent;

import java.util.concurrent.ThreadFactory;

/**
 * DaemonThreadFactory
 *
 * @author zhucj
 * @since 20200528
 */
public class DaemonThreadFactory implements ThreadFactory {

    @Override
    public Thread newThread(Runnable r) {
        Thread thread = new Thread();
        thread.setDaemon(true);
        return thread;
    }
}
