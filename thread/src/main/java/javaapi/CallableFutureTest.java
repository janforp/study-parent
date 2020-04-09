package javaapi;

import java.util.concurrent.Callable;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;

/**
 * 类说明：
 *
 * @author zhucj
 * @since 20200423
 */
@SuppressWarnings("all")
public class CallableFutureTest {

    public static void main(String[] args) throws InterruptedException, ExecutionException {

        System.out.println("主线程启动");

        final ExecutorService executor = Executors.newFixedThreadPool(5);

        Callable<String> callable = new Callable<String>() {

            @Override
            public String call() throws Exception {
                System.out.println("进入子线程");
                Thread.sleep(1000 * 10);
                System.out.println("子线程执行结束");
                return "子线程返回值";
            }
        };
        Future<String> future = executor.submit(callable);
        Thread.sleep(1000 * 2);
        String result = future.get();
        System.out.println("子线程返回值 = " + result);
        executor.shutdown();
        System.out.println("主线程结束");
    }
}
