package com.janita.java.base.threadpool;

import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;

/**
 * NetworkService
 *
 * @author zhucj
 * @since 20210225
 */
public class NetworkService implements Runnable {

    private final ServerSocket serverSocket;

    private final ExecutorService pool;

    public NetworkService(int port, int poolSize) throws IOException {
        this.serverSocket = new ServerSocket(port);
        this.pool = Executors.newFixedThreadPool(poolSize);
    }

    @Override
    public void run() {
        try {
            for (; ; ) {
                pool.execute(new Handler(serverSocket.accept()));
            }
        } catch (IOException ex) {
            pool.shutdown();
        }
    }

    /**
     * 以下方法分两个阶段关闭{@code ExecutorService}，首先是通过调用{@code shutdown}拒绝传入的任务，然后在必要时调用{@code shutdownNow}来取消所有未完成的任务：
     *
     * @param pool
     */
    void shutdownAndAwaitTermination(ExecutorService pool) {
        pool.shutdown();// Disable new tasks from being submitted
        try {
            // Wait a while for existing tasks to terminate
            if (!pool.awaitTermination(60, TimeUnit.SECONDS)) {
                pool.shutdownNow();// Cancel currently executing tasks
                // Wait a while for tasks to respond to being cancelled
                if (!pool.awaitTermination(60, TimeUnit.SECONDS)) {
                    System.err.println("Pool did not terminate");
                }
            }
        } catch (InterruptedException e) {
            // (Re-)Cancel if current thread also interrupted
            pool.shutdownNow();
            // Preserve interrupt status
            Thread.currentThread().interrupt();
        }
    }
}

class Handler implements Runnable {

    private final Socket socket;

    Handler(Socket socket) {
        this.socket = socket;
    }

    @Override
    public void run() {
        //做一些事情
    }
}
