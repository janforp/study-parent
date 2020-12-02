package com.janita.datastructuresandalgorithms.book3.chapter2;

import org.junit.Test;

/**
 * PartialSum
 * factorial
 *
 * @author zhucj
 * @since 20201126
 */
public class PartialSum {

    public static int sum(int n) {
        int partialSum = 0;
        for (int i = 1; i <= n; i++) {
            partialSum = partialSum + i * i * i;
        }
        return partialSum;
    }

    /**
     * T(N) = 2 + T(n-1) + T(n-2)
     *
     * @param n
     * @return
     */
    public static long fib(int n) {
        if (n <= 1) {
            return 1;
        }
        return fib(n - 1) + fib(n - 2);
    }

    @Test
    public void testSum() {
        System.out.println(fib(400));
    }
}
