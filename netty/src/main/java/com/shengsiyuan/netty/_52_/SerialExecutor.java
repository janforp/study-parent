package com.shengsiyuan.netty._52_;

import java.util.ArrayDeque;
import java.util.Queue;
import java.util.concurrent.Executor;

/**
 * 类说明：SerialExecutor
 *
 * @author zhucj
 * @since 20200423
 */
public class SerialExecutor implements Executor {

    final Queue<Runnable> tasks = new ArrayDeque<>();

    final Executor executor;

    Runnable active;

    SerialExecutor(Executor executor) {
        this.executor = executor;
    }

    @Override
    public synchronized void execute(Runnable command) {
        if (command == null) {
            return;
        }
        tasks.offer(() -> {
            try {
                command.run();
            } finally {
                scheduleNext();
            }
        });
        if (active == null) {
            scheduleNext();
        }
    }

    protected synchronized void scheduleNext() {
        if ((active = tasks.poll()) != null) {
            executor.execute(active);
        }
    }

    public static void main(String[] args) {
        SerialExecutor executor = new SerialExecutor(Runnable::run);
        executor.execute(() -> System.out.println("1"));
        executor.execute(() -> System.out.println("2"));
        executor.execute(() -> System.out.println("3"));
        executor.execute(() -> System.out.println("4"));
        executor.execute(() -> System.out.println("5"));
    }
}

/**
 * 在调用线程直接执行
 */
class DirectExecutor implements Executor {

    @Override
    public void execute(Runnable r) {
        r.run();
    }
}
