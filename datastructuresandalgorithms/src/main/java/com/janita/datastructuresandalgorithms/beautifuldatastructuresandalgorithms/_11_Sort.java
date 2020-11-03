package com.janita.datastructuresandalgorithms.beautifuldatastructuresandalgorithms;

/**
 * _11_Sort
 *
 * @author zhucj
 * @since 20201126
 */
public class _11_Sort {

    private static void print(int[] numbers) {
        StringBuilder str = new StringBuilder();
        for (int number : numbers) {
            str.append(number).append(",");
        }
        String toString = str.toString();
        toString = toString.substring(0, toString.length() - 1);
        System.out.println(toString);
    }

    public static class BubbleSort {

        /**
         * 冒泡排序
         * 原始数组：4,5,6,3,2,1
         * 需要交换位置的组合：(4,3),(4,2),(4,1),(5,3),(5,2),(5,1),(6,3),(6,2),(6,1),(3,2),(3,1),(2,1)
         *
         * @param numbers
         */
        public static void bubbleSort(int[] numbers) {
            if (numbers == null || numbers.length == 0) {
                return;
            }
            System.out.print("原始数组 = ");
            print(numbers);
            int exchangeTimes = 0;
            for (int i = 0; i < numbers.length; i++) {
                System.out.println("第 " + (i + 1) + " 次冒泡开始");
                for (int j = 0; j < numbers.length - i - 1; j++) {
                    if (numbers[j] > numbers[j + 1]) {
                        System.out.println("*** 数据 (" + numbers[j] + "," + numbers[j + 1] + ") 进行交换");
                        int temp = numbers[j];
                        numbers[j] = numbers[j + 1];
                        numbers[j + 1] = temp;
                        exchangeTimes++;
                    }
                }
                print(numbers);
                System.out.println("第 " + (i + 1) + " 次冒泡结束");
                System.out.println();
            }
            System.out.println("共进行了 " + (exchangeTimes) + " 次数据交换");
        }

        /**
         * 冒泡排序
         * 原始数组：4,5,6,3,2,1
         * 需要交换位置的组合：(4,3),(4,2),(4,1),(5,3),(5,2),(5,1),(6,3),(6,2),(6,1),(3,2),(3,1),(2,1)
         *
         * @param numbers
         */
        public static void betterBubbleSort(int[] numbers) {
            if (numbers == null || numbers.length == 0) {
                return;
            }
            boolean flag = false;
            System.out.print("原始数组 = ");
            print(numbers);
            int exchangeTimes = 0;
            for (int i = 0; i < numbers.length; i++) {
                System.out.println("第 " + (i + 1) + " 次冒泡开始");
                for (int j = 0; j < numbers.length - i - 1; j++) {
                    if (numbers[j] > numbers[j + 1]) {
                        System.out.println("*** 数据 (" + numbers[j] + "," + numbers[j + 1] + ") 进行交换");
                        int temp = numbers[j];
                        numbers[j] = numbers[j + 1];
                        numbers[j + 1] = temp;
                        exchangeTimes++;
                        flag = true;
                    }
                }
                print(numbers);
                System.out.println("第 " + (i + 1) + " 次冒泡结束");
                System.out.println();
                if (!flag) {
                    break;
                }
            }
            System.out.println("共进行了 " + (exchangeTimes) + " 次数据交换");
        }
    }
}