package design.design014.future;

import java.util.Random;
import java.util.concurrent.*;

/**
 * Created by Janita on 2017/9/25 0025-上午 10:49
 * 该类是：
 */
public class CallableAndFuture {
    public static void main(String[] args) {
        m1();
        m2();
    }


    private static void m1() {
        Callable<Integer> callable = () -> new Random().nextInt(100);
        FutureTask<Integer> future = new FutureTask<>(callable);
        new Thread(future).start();
        try {
            Thread.sleep(5000);// 可能做一些事情
            System.out.println(future.get());
        } catch (InterruptedException e) {
            e.printStackTrace();
        } catch (ExecutionException e) {
            e.printStackTrace();
        }
    }

    private static void m2() {
        ExecutorService threadPool = Executors.newCachedThreadPool();
        CompletionService<Integer> cs = new ExecutorCompletionService<>(threadPool);
        for(int i = 1; i < 5; i++) {
            final int taskID = i;
            cs.submit(() -> taskID);
        }
        // 可能做一些事情
        for(int i = 1; i < 5; i++) {
            try {
                System.out.println(cs.take().get());
            } catch (InterruptedException e) {
                e.printStackTrace();
            } catch (ExecutionException e) {
                e.printStackTrace();
            }
        }
    }
}