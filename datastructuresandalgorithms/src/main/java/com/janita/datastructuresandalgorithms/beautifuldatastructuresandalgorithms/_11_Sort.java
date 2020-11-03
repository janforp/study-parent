package com.janita.datastructuresandalgorithms.beautifuldatastructuresandalgorithms;

/**
 * _11_Sort
 *
 * @author zhucj
 * @since 20201126
 */
public class _11_Sort {

    /**
     * 冒泡排序
     *
     * @param numbers
     */
    public static void bubbleSort(int[] numbers) {
        if (numbers == null || numbers.length == 0) {
            return;
        }
        for (int i = 0; i < numbers.length; i++) {
            int temI = numbers[i];
            for (int j = i + 1; j < numbers.length; j++) {
                int tempJ = numbers[j];
                if (temI == tempJ) {
                    continue;
                }
                if (temI > tempJ) {
                    numbers[i] = tempJ;
                    numbers[j] = temI;
                }
                print(numbers);
            }
        }
    }

    private static void print(int[] numbers) {
        for (int number : numbers) {
            System.out.print(number + ",");
        }
        System.out.println();
    }
}
