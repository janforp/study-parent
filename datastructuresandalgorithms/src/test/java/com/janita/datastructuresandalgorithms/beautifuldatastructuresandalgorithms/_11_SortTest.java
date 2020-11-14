package com.janita.datastructuresandalgorithms.beautifuldatastructuresandalgorithms;

import org.junit.Test;

/**
 * _11_SortTest
 *
 * @author zhucj
 * @since 20201126
 */
public class _11_SortTest {

    @Test
    public void bubbleSortTest() {
        _11_Sort.BubbleSort.bubbleSort(new int[] { 3, 5, 4, 1, 2, 3 });
        _11_Sort.BubbleSort.betterBubbleSort(new int[] { 3, 5, 4, 1, 2, 3 });
    }
}
