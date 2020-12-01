package com.janita.datastructuresandalgorithms.book3;

import org.junit.Test;

/**
 * 给定一个整数组，求他的最大子序列
 *
 * @author zhucj
 * @since 20201126
 */
public class MaxSubSum {

    @Test
    public void test() {
        int[] a = new int[] { 4, -3, 5, -2, -1, 2, 6, -2 };
        System.out.println(maxSubSum1(a));
        System.out.println(maxSubSum2(a));
        System.out.println(maxSubSum3(a));
        System.out.println(maxSubSum4(a));
    }

    public static int maxSubSum1(int[] a) {
        int maxSum = 0;
        for (int i = 0; i < a.length; i++) {//O(n)
            for (int j = i; j < a.length; j++) {//O(n^2)
                int thisSum = 0;
                for (int k = i; k <= j; k++) {//O(n^3)
                    thisSum += a[k];
                }
                if (thisSum > maxSum) {
                    maxSum = thisSum;
                }
            }
        }
        return maxSum;
    }

    public static int maxSubSum2(int[] a) {
        int maxSum = 0;
        for (int i = 0; i < a.length; i++) {//O(n)
            int thisSum = 0;
            //分别求0-len-1,1,len-1,......,len-2到len-1以及len-1的最大值，在他们中取最大
            for (int j = i; j < a.length; j++) {
                thisSum += a[j];
                if (thisSum > maxSum) {
                    maxSum = thisSum;
                }
            }
        }
        return maxSum;
    }

    public static int maxSubSum3(int[] a) {
        return maxSumRec(a, 0, a.length - 1);
    }

    private static int maxSumRec(int[] a, int left, int right) {//O(NlogN)
        if (left == right) {
            return Math.max(a[left], 0);
        }
        int center = (left + right) / 2;
        int maxLeftSum = maxSumRec(a, left, center);
        int maxRightSum = maxSumRec(a, center + 1, right);

        int maxLeftBorderSum = 0;
        int leftBorderSum = 0;
        for (int i = center; i >= left; i--) {
            leftBorderSum += a[i];
            if (leftBorderSum > maxLeftBorderSum) {
                maxLeftBorderSum = leftBorderSum;
            }
        }

        int maxRightBorderSum = 0;
        int rightBorderSum = 0;
        for (int i = center + 1; i <= right; i++) {
            rightBorderSum += a[i];
            if (rightBorderSum > maxRightBorderSum) {
                maxRightBorderSum = rightBorderSum;
            }
        }
        return max3(maxLeftSum, maxRightSum, maxLeftBorderSum + maxRightBorderSum);
    }

    private static int max3(int maxLeftSum, int maxRightSum, int maxMiddleSum) {
        int max = maxLeftSum;
        if (maxRightSum > max) {
            max = maxRightSum;
        }
        if (maxMiddleSum > max) {
            max = maxMiddleSum;
        }
        return max;
    }

    @SuppressWarnings("all")
    public static int maxSubSum4(int[] arr) {
        //4, -3, 5, -2, -1, 2, 6, -2
        int maxSum = 0;
        int thisSum = 0;
        for (int j = 0; j < arr.length; j++) {
            thisSum = thisSum + arr[j];
            if (thisSum > maxSum) {
                maxSum = thisSum;
            } else if (thisSum < 0) {
                thisSum = 0;
            }
        }
        return maxSum;
    }
}
