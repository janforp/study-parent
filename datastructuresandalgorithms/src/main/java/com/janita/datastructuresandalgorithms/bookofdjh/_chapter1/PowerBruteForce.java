package com.janita.datastructuresandalgorithms.bookofdjh._chapter1;

import lombok.experimental.UtilityClass;

import java.math.BigInteger;

/**
 * 从常数、对数、线性到平方时间复杂度，算法的效率不断下降，但就实际应用而言，
 * 的效率还在允许的范围内。然而，在多项式时间复杂度与指数时间复杂度之间，却有着一道巨大的 鸿沟，
 * 通常我们都认为，指数复杂度的算法无法应用于实际问题之中，它们不是有效的算法，甚至 不能称作算法。
 *
 * @author zhucj
 * @since 20201126
 */
@UtilityClass
public class PowerBruteForce {

    /**
     * 给定非负整数 r，计算 2^r
     * y = x^2 ----> log2y=x
     *
     * @param r 对于这类数值计算问题，我们更倾向于用输入数值(r)的二进制位数(n = 1 + ⎣log2r⎦)做为输入规模。
     * @return
     */
    public BigInteger powerBruteForce(long r) {
        if (r < 0) {
            throw new RuntimeException();
        }
        BigInteger power = BigInteger.ONE;
        while (0 < r--) {
            power = power.multiply(BigInteger.valueOf(2L));
        }
        return power;
    }

    /**
     * 算法:NonextremeElement(S[], n) 输入:由n个整数构成的集合S
     * 输出:其中的任一非极端元素
     * {
     * 任取的三个元素x, y, z ∈ S; //既然S是集合，这三个元素必互异
     * 通过比较，找出其中的最小者min{x, y, z}和最大者max{x, y, z};
     * 输出最小、最大者之外的那个元素; }
     *
     * @param ints
     * @return
     */
    public int nonExtremeElement(int[] ints) {
        if (ints == null || ints.length < 3) {
            throw new RuntimeException();
        }
        int a = ints[0];
        int b = ints[1];
        int c = ints[2];
        //现比较a,b;再确定c对于(a,b)区间的关系
        if (a > b) {
            int temp = a;
            a = b;
            b = temp;
        }
        if (c < a) {
            return a;
        }
        if (c > b) {
            return b;
        }
        return c;
    }

    /**
     * 算法:BaseConversion(n) 输入:十进制整数n
     * 输出:n的三进制表示
     * {
     * 不断循环，直到n = 0 { 输出 n mod 3; //取模 令 n = n/3; //整除
     * }
     *
     * 时间复杂度：O(logn)
     *
     * @return
     */
    public String baseConversion(int n, int base) {
        StringBuilder three = new StringBuilder();
        while (0 < n) {
            int mode = n % base;
            n = n / base;
            three.append(mode);
        }
        return three.reverse().toString();
    }

    public void main(String[] args) {
        System.out.println(powerBruteForce(1L));
        System.out.println(powerBruteForce(2L));
        System.out.println(powerBruteForce(8L));
        System.out.println(powerBruteForce(16L));
        System.out.println(powerBruteForce(1024L));

        System.out.println(nonExtremeElement(new int[] { 10, 3, 4, 5, 6, 2, 3 }));

        System.out.println(baseConversion(101, 3));
        System.out.println(baseConversion(23, 3));
        System.out.println(baseConversion(101, 3));
    }
}
