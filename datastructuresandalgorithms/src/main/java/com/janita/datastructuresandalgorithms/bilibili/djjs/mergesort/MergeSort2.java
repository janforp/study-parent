package com.janita.datastructuresandalgorithms.bilibili.djjs.mergesort;

/**
 * MergeSor
 * T(n) = 2T(n/2) + O(n) =======> nlogn
 *
 * @author zhucj
 * @since 20201126
 */
public class MergeSort2 {

    /**
     * S[lo,hi) ---> S[lo,mi) + S[mi,hi)
     *
     * @param arr
     * @param lo
     * @param hi
     */
    public static void mergeSort(int[] arr, int lo, int hi) {
        if (hi <= lo) {
            return;
        }
        int mi = (lo + hi) >> 1;
        mergeSort(arr, lo, mi);
        mergeSort(arr, mi, hi);
        merge(arr, lo, mi, hi);
    }

    /**
     * low =0
     * mi = 2
     * hi = 5
     * [2,3,7,5,9,10]
     *
     * @param arr
     * @param lo
     * @param mi
     * @param hi
     */
    private static void merge(int[] arr, int lo, int mi, int hi) {
        int[] leftArr = new int[mi - lo + 1];
        for (int i = 0; i <= mi; i++) {
            leftArr[i] = arr[i];
        }
        int[] rightArr = new int[hi - mi];
        for (int i = 0; i < rightArr.length; i++) {
            rightArr[i] = arr[++mi];
        }

    }

    public static void main(String[] args) {
        int[] arr = new int[] { 2, 3, 7, 5, 9, 10 };
        merge(arr, 0, 2, 5);
    }
}