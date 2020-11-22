package com.janita.datastructuresandalgorithms.bookofdjh;

import java.math.BigInteger;

/**
 * Recursive
 *
 * @author zhucj
 * @since 20201126
 */
public class Recursive {

    /**
     * 数组元素的递归求和
     * 这里，我们再次以第 1.3.3 节(12 页)中曾讨论过的数组求和问题为例，介绍如何采用线性递
     * 归的策略，对数组A[]中的n个整数进行求和。为此，我们可以注意到:若n=1，则数组的总和就是A[0];
     * 否则，数组的总和就是前n-1 个整数(A[0..n-2])的和，再加上A[]的最后一个元素(A[n-1])。于是，
     * 我们可以利用 算法一.8 来解决这一问题。
     * 算法:LinearSum(A, n) 输入:包含至少n个整数的数组A，n≥1
     * 输出:A中前n个元素之和 {
     * if (n=1) return A[0];
     * else return LinearSum(A, n-1)+A[n-1]; }
     *
     * O(n)
     *
     * T(n) = T(n-1) + O(1)  | (O(1) = c1);
     * T(1) = O(1) | (O(1) = c2);
     *
     * =====>
     *
     * T(n) = c1(n - 1) + c2 = c1*n + (c2 - c1) = O(n)
     *
     * @param ints
     * @param n
     * @return
     */
    public static int linearSum(int[] ints, int n) {
        if (ints == null || ints.length == 0) {
            throw new RuntimeException();
        }
        if (n == 1) {
            return ints[0];
        }
        return linearSum(ints, n - 1) + ints[n - 1];
    }

    /**
     * 算法:ReverseArray(A, lo, hi) 输入:数组A，非负整数lo和hi
     * 输出:A[lo..hi]的次序被倒置 {
     * if (lo<hi) {
     * Swap(A[lo], A[hi]); ReverseArray(A, lo+1; hi-1);
     *
     * @param ints
     * @param lo
     * @param hi
     */
    public static void reverseArray(int[] ints, int lo, int hi) {
        //这一算法的递归基有两种情况:lo = hi (数组长度为奇数时)或 lo > hi (数组长度为
        //偶数时)。长度为奇(偶)数的数组，必然终止于前(后)一种平凡情况，因此算法必会终止。
        if (lo == hi) {
            return;
        }
        if (lo < hi) {
            swap(ints, lo, hi);
        }
        reverseArray(ints, lo + 1, hi - 1);
    }

    public static void iterativeReverseArray(int[] ints, int lo, int hi) {
        while (lo < hi) {
            swap(ints, lo, hi);
            lo++;
            hi--;
        }
    }

    static void swap(int[] ints, int lo, int hi) {
        int low = ints[lo];
        int high = ints[hi];
        ints[lo] = high;
        ints[hi] = low;
    }

    /**
     * power(2, r) = 2^r
     *
     * O(n)
     *
     * @param base
     * @param power
     * @return
     */
    public static BigInteger power(int base, int power) {
        if (power == 0) {
            return BigInteger.ONE;
        }
        return BigInteger.valueOf(base).multiply(power(base, power - 1));
    }

    /**
     * 不难看出，每经过一次递归调用，指数 r 都会递减至少一半。因此，只需 O(logr)次递归调用， 即可得到计算结果。与前面提到的 O(r)复杂度相比，几乎降低了一个线性因子，前后两个算法的效 率有天壤之别
     * O(logn)
     *
     * @param r
     * @return
     */
    public static int power(int r) {
        if (r == 0) {
            return 1;
        }
        if (r % 2 == 1) {
            return 2 * (power((r - 1) / 2)) * (power((r - 1) / 2));
        }
        return power(r / 2) * power(r / 2);
    }

    public static void main(String[] args) {
        int[] ints = { 1, 2, 3 };
        System.out.println(linearSum(ints, 3));
        reverseArray(ints, 0, 2);
        ints = new int[] { 1, 2, 3 };
        iterativeReverseArray(ints, 0, 2);
        print(ints);
        System.out.println(power(2, 4));
        System.out.println(power(4));
    }

    private static void print(int[] ints) {
        for (int i : ints) {
            System.out.print(i);
        }
    }
}
