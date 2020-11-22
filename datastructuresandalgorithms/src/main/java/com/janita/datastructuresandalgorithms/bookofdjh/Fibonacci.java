package com.janita.datastructuresandalgorithms.bookofdjh;

import org.junit.Test;

import java.math.BigInteger;
import java.util.HashMap;
import java.util.Map;

/**
 * Fibonacci
 *
 * @author zhucj
 * @since 20201126
 */
@SuppressWarnings("all")
public class Fibonacci {

    /**
     * 算法:BinaryFib(k) 输入:非负整数k
     * 输出:Fib(k) {
     * if (k<=1) return k;
     * else return BinaryFib(k-1) + BinaryFib(k-2); }
     *
     * 不幸的是，尽管表面看来 Fibonacci 数的这一定义与二分递归十分相似，
     * 但在这里采用这一策 略的效率却极低。实际上，上述算法需要运行 O(2^k)的时间才能计算出第 k 个 Fibonacci 数，
     * 也就是 说，这是一个指数复杂度的算法，在实际环境中毫无用处。
     *
     * O(n^2)
     *
     * @param order 第order个数，从1开始
     * @return
     */
    private static BigInteger binaryFib(int order) {
        if (order <= 0) {
            throw new RuntimeException();
        }
        if (order == 1) {
            return BigInteger.valueOf(1L);
        }
        if (order == 2) {
            return BigInteger.valueOf(2L);
        }
        return binaryFib(order - 1).add(binaryFib(order - 2));
    }

    private static BigInteger binaryFibBetter(int order) {
        Map<Integer, BigInteger> resultMap = new HashMap<>();
        resultMap.put(1, BigInteger.valueOf(1L));
        resultMap.put(2, BigInteger.valueOf(2L));
        if (order <= 0) {
            throw new RuntimeException();
        }
        if (resultMap.containsKey(order)) {
            return resultMap.get(order);
        }
        if (order == 1) {
            return BigInteger.valueOf(1L);
        }
        if (order == 2) {
            return BigInteger.valueOf(2L);
        }
        BigInteger add = binaryFibBetter(order - 1).add(binaryFibBetter(order - 2));
        resultMap.put(order, add);
        return add;
    }

    /**
     * 0,1,1,2,3,5,8
     *
     * O(n)
     *
     * @param order 第order个数，从1开始
     * @return
     */
    private static BigInteger linearFibonacci(int order) {
        if (order <= 0) {
            throw new RuntimeException();
        }
        if (order == 1) {
            return BigInteger.ONE;
        }
        if (order == 2) {
            return BigInteger.valueOf(2L);
        }
        BigInteger[] a = new BigInteger[order];
        a[0] = BigInteger.valueOf(1L);
        a[1] = BigInteger.valueOf(2L);
        for (int i = 2; i < order; i++) {
            a[i] = a[i - 1].add(a[i - 2]);
        }
        return a[order - 1];
    }

    @Test
    public void binaryFibTest() {
        System.out.println(linearFibonacci(100));
        System.out.println(binaryFibBetter(100));
        System.out.println(binaryFib(100));
    }

    //a=4，b=9，g=8，i=5，l=0，o=3，r=6，y=2。

    //boy + girl = baby
    //9+3+2 + 8+5 + 6 + 0 = 9 + 4 + 9 + 2
}
