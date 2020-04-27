package com.janita.java.base.concurrent;

import lombok.AllArgsConstructor;
import lombok.Data;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.Random;
import java.util.concurrent.Callable;
import java.util.concurrent.CompletionService;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.Executor;
import java.util.concurrent.ExecutorCompletionService;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;

/**
 * 类说明：CompletionServiceTest
 *
 * @author zhucj
 * @since 20200423
 */
public class CompletionServiceTest {

    public static void main(String[] args) throws ExecutionException, InterruptedException {

        Collection<Callable<Result>> solvers = new ArrayList<>();

        for (int i = 0; i < 10; i++) {
            Callable<Result> callable = () -> {
                int nextInt = new Random().nextInt(20);
                System.out.println("随机数 = " + nextInt);
                Thread.sleep(nextInt * 1000);
                return new Result(10 + nextInt);
            };
            solvers.add(callable);
        }

        ExecutorService fixedThreadPool = Executors.newFixedThreadPool(10);
        boolean solveAll = CompletionServiceUse.solveAll(fixedThreadPool, solvers);

        boolean solveAnyOne = CompletionServiceUse.solveAnyOne(fixedThreadPool, solvers);
        if (solveAll && solveAnyOne) {
            fixedThreadPool.shutdown();
        }
    }
}

class CompletionServiceUse {

    static boolean solveAll(Executor executor, Collection<Callable<Result>> solvers)
            throws InterruptedException, ExecutionException {

        CompletionService<Result> ecs = new ExecutorCompletionService<>(executor);
        for (Callable<Result> callable : solvers) {
            ecs.submit(callable);
        }

        int n = solvers.size();
        for (int i = 0; i < n; ++i) {
            Future<Result> future = ecs.take();
            Result r = future.get();
            if (r != null) {
                use(r);
            }
        }
        return true;
    }

    private static void use(Result result) {
        System.out.println(result.getRes());
    }

    static boolean solveAnyOne(Executor executor, Collection<Callable<Result>> solvers) throws InterruptedException {
        CompletionService<Result> ecs = new ExecutorCompletionService<>(executor);
        int n = solvers.size();
        List<Future<Result>> futureList = new ArrayList<>(n);
        Result result = null;
        try {
            for (Callable<Result> callable : solvers) {
                Future<Result> future = ecs.submit(callable);
                futureList.add(future);
            }
            for (int i = 0; i < n; ++i) {
                try {
                    Future<Result> future = ecs.take();
                    Result r = future.get();
                    if (r != null) {
                        result = r;
                        break;
                    }
                } catch (ExecutionException ignore) {
                }
            }
        } finally {
            for (Future<Result> f : futureList) {
                f.cancel(true);
            }
        }
        if (result != null) {
            System.out.println("solveAnyOne");
            use(result);
        }
        return true;
    }
}

@Data
@AllArgsConstructor
class Result {

    private int res;
}
