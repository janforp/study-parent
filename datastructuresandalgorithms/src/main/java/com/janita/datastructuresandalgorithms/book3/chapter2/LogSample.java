package com.janita.datastructuresandalgorithms.book3.chapter2;

import org.junit.Test;

/**
 * LogSample
 *
 * @author zhucj
 * @since 20201126
 */
public class LogSample {

    @Test
    public void test() {
        Integer[] orderArr = new Integer[] { 1, 2, 3, 4, 5, 5, 6, 90 };
        System.out.println(binSearch2(6, orderArr));
        System.out.println(binarySearch(orderArr, 6));
        System.out.println(gcd(50, 15));
    }

    public static int binSearch2(int x, Integer[] orderArr) {
        return binSearchRec(x, orderArr, 0, orderArr.length - 1);
    }

    private static int binSearchRec(int x, Integer[] orderArr, int left, int right) {
        if (left == right) {
            if (orderArr[left] == x) {
                return left;
            }
            return -1;
        }
        int center = (left + right) / 2;
        int middle = orderArr[center];
        if (middle == x) {
            return center;
        }
        if (middle > x) {
            return binSearchRec(x, orderArr, left, center);
        }
        return binSearchRec(x, orderArr, center + 1, right);
    }

    public static <T extends Comparable<? super T>> int binarySearch(T[] orderArr, T x) {
        int low = 0;
        int high = orderArr.length - 1;
        while (low <= high) {
            int mid = (low + high) / 2;
            if (orderArr[mid].compareTo(x) < 0) {
                //如果中间的数字小于x，则说明x在右侧
                low = mid + 1;
            } else if (orderArr[mid].compareTo(x) > 0) {
                //如果中间的数字大于x，则说明x在左侧
                high = mid - 1;
            } else {
                //中间的数字就是x
                return mid;
            }
        }
        return -1;
    }

    /**
     * greatest common divisor 最大公约数
     *
     * gcd(50,15) = 5;
     *
     * @param m
     * @param n
     * @return
     */
    public static long gcd(long m, long n) {
        while (n != 0) {
            long rem = m % n;
            m = n;
            n = rem;
        }
        return m;
    }
}
