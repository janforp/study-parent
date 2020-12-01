package com.janita.datastructuresandalgorithms.beautifuldatastructuresandalgorithms;

import org.junit.Test;

/**
 * Pivot
 *
 * @author zhucj
 * @since 20201126
 */
public class Pivot {

    private static <T extends Comparable<T>> int getPivot(T[] arr) {
        int lo = 0;
        int hi = arr.length - 1;
        while (lo < hi) {
            while (lo < hi && arr[lo].compareTo(arr[hi]) <= 0) {
                hi--;
            }
            swap(arr, lo, hi);
            while (lo < hi && arr[lo].compareTo(arr[hi]) <= 0) {
                lo++;
            }
            swap(arr, lo, hi);
        }
        return lo;
    }

    private static <T extends Comparable<T>> void swap(T[] arr, int lo, int hi) {
        T tem = arr[lo];
        arr[lo] = arr[hi];
        arr[hi] = tem;
    }

    @Test
    public void test() {

        System.out.println(Integer.valueOf(1).compareTo(2));
        Integer[] arr = new Integer[] { 90, 1, 2, 3, 4, 5, 10, 22 };
        System.out.println(getPivot(arr));

        String main = "Now is the time for all good people to come";
        String pattern = "people";
        System.out.println(main.indexOf(pattern));
    }

    public static void main(String[] args) {
        int[] arr = new int[] { 5, 4, 8, 6, 3, 9, 0, 1, 7, 2 };
        quickSort(arr, 0, arr.length - 1);
        printArray(arr);
    }

    public static void quickSort(int[] arr, int start, int end) {
        if (end <= start) {
            return;
        }
        int low = start;
        int high = end;
        int pivot = arr[low];
        while (low < high) {
            while (low < high && arr[high] >= pivot) {
                high--;
            }
            arr[low] = arr[high]; // 将小于 pivot 的数放在地位
            while (low < high && arr[low] <= pivot) {
                low++;
            }
            arr[high] = arr[low]; // 将大于 pivot 的数放在高位
        }
        arr[low] = pivot;
        quickSort(arr, start, low - 1); // 递归排序左半部分
        quickSort(arr, low + 1, end); // 递归排序右半部分
    }

    public static void printArray(int[] arr) {
        for (int j : arr) {
            System.out.print(j + " ");
        }
    }

}
