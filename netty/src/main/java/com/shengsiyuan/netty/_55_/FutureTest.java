package com.shengsiyuan.netty._55_;

import java.util.concurrent.ExecutionException;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;

/**
 * 类说明：FutureTest
 *
 * @author zhucj
 * @since 20200423
 */
public class FutureTest {

    public static void main(String[] args) throws ExecutionException, InterruptedException {
        ExecutorService executor = Executors.newSingleThreadExecutor();
        ArchiveSearcher searcher = target -> {
            Thread.sleep(3000);
            return "终于搜索到了：" + target;
        };

        Future<String> future = executor.submit(() -> searcher.search("java"));
        goOnDoOtherThings();
        //这一步是（可能）阻塞的呢
        displaySearchResult(future.get());
        //如果不关闭，则程序不会直接退出哦
        executor.shutdown();
    }

    private static void displaySearchResult(String searchResult) {
        System.out.println("查询结果：" + searchResult);
    }

    private static void goOnDoOtherThings() throws InterruptedException {
        Thread.sleep(4000);
        System.out.println("主线程继续做其他事情，不要等待查询返回");
    }
}

interface ArchiveSearcher {

    String search(String target) throws InterruptedException;
}