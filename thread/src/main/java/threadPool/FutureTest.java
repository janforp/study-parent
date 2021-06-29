package threadPool;

import java.util.concurrent.ExecutionException;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;

/**
 * FutureTest
 *
 * @author zhucj
 * @since 20210325
 */
public class FutureTest {

    public static void main(String[] args) throws ExecutionException, InterruptedException {
        ExecutorService executor = Executors.newFixedThreadPool(5);
        Future<String> future = executor.submit(() -> {
            Thread.sleep(40000);
            return "完成了";
        });
        String result = future.get();
        System.out.println(result);
    }
}
