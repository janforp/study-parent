package com.janita.java.base.thinkinjava._20_concurrent.atomic;

import java.util.Random;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.atomic.AtomicInteger;

import static com.janita.java.base.thinkinjava.util.Print.print;

/**
 * FastSimulation
 *
 * @author zhucj
 * @since 20200623
 */
public class FastSimulation {

    static final int N_ELEMENTS = 100000;

    static final int N_GENES = 30;

    static final int N_EVOLVERS = 50;

    /**
     * 数组size=N_ELEMENTS
     * 每个元素是一个N_GENES的数组
     */
    static final AtomicInteger[][] GRID = new AtomicInteger[N_ELEMENTS][N_GENES];

    static Random rand = new Random(47);

    static class Evolver implements Runnable {

        @Override
        public void run() {

            while (!Thread.interrupted()) {
                int element = rand.nextInt(N_ELEMENTS);
                for (int i = 0; i < N_GENES; i++) {
                    int previous = element - 1;
                    if (previous < 0) {
                        //如果小于0，则使用最后一个元素
                        previous = N_ELEMENTS - 1;
                    }
                    int next = element + 1;
                    if (next >= N_ELEMENTS) {
                        next = 0;
                    }
                    int oldValue = GRID[element][i].get();
                    // Perform some kind of modeling calculation:
                    int newValue = oldValue + GRID[previous][i].get() + GRID[next][i].get();
                    newValue /= 3; // Average the three values
                    if (!GRID[element][i].compareAndSet(oldValue, newValue)) {
                        // Policy here to deal with failure. Here, we
                        // just report it and ignore it; our model
                        // will eventually deal with it.
                        print("Old value changed from " + oldValue);
                    }
                }
            }
        }
    }

    public static void main(String[] args) throws Exception {
        ExecutorService exec = Executors.newCachedThreadPool();
        for (int i = 0; i < N_ELEMENTS; i++)
            for (int j = 0; j < N_GENES; j++) {
                GRID[i][j] = new AtomicInteger(rand.nextInt(1000));
            }
        for (int i = 0; i < N_EVOLVERS; i++) {
            exec.execute(new Evolver());
        }
        TimeUnit.SECONDS.sleep(5);
        exec.shutdownNow();
    }
}
