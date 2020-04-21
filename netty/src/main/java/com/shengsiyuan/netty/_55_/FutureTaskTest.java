package com.shengsiyuan.netty._55_;

import java.util.concurrent.ExecutionException;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.FutureTask;

/**
 * 类说明：FutureTaskTest
 *
 * @author zhucj
 * @since 20200423
 */
public class FutureTaskTest {

    public static void main(String[] args) throws ExecutionException, InterruptedException {
        ExecutorService executor = Executors.newSingleThreadExecutor();
        ArchiveSearcher searcher = target -> {
            Thread.sleep(3000);
            return "终于搜索到了：" + target;
        };
        FutureTask<String> futureTask = new FutureTask<>(() -> searcher.search("C++"));
        executor.submit(futureTask);
        goOnDoOtherThings();
        //这一步是（可能）阻塞的呢
        displaySearchResult(futureTask.get());
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