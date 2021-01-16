package com.janita.datastructuresandalgorithms.lc;

import org.junit.Test;

import java.util.ArrayList;
import java.util.List;

/**
 * 如何螺旋遍历二维数组？
 * https://zhuanlan.zhihu.com/p/299194187
 *
 * @author zhucj
 * @since 20201224
 */
public class SpiralTraversalArr {

    public static void traversalArr(int[][] arr) {
        for (int[] ints : arr) {
            for (int anInt : ints) {
                System.out.print(anInt + ", ");
            }
            System.out.println();
        }
    }

    public static List<Integer> spiralTraversalArr(int[][] matrix) {
        List<Integer> list = new ArrayList<Integer>();
        //当二维数组是空或任何一个维度是0，直接返回
        if (matrix == null || matrix.length == 0 || matrix[0].length == 0) {
            return list;
        }
        //m是矩阵的行数
        int m = matrix.length;
        //n是矩阵的列数
        int n = matrix[0].length;
        //二维数组的层数，取决于行和列的较小值
        int size = (Math.min(m, n) + 1) / 2;
        //大循环，从外向内逐层遍历矩阵
        for (int i = 0; i < size; i++) {
            //从左到右遍历“上边”
            for (int j = i; j < n - i; j++) {
                list.add(matrix[i][j]);
            }
            //从上到下遍历“右边”
            for (int j = i + 1; j < m - i; j++) {
                list.add(matrix[j][(n - 1) - i]);
            }
            //从右到左遍历“下边”
            for (int j = i + 1; j < n - i && (m - 1) - i > i; j++) {
                list.add(matrix[(m - 1) - i][(n - 1) - j]);
            }
            //从下到上遍历“左边”
            for (int j = i + 1; j < m - 1 - i && i < (n - 1) - i; j++) {
                list.add(matrix[(m - 1) - j][i]);
            }
        }
        return list;
    }

    private static int[][] build() {
        return new int[][] { { 1, 2, 3, 4, 5 }, { 6, 7, 8, 9, 10 }, { 11, 12, 13, 14, 15 }, { 16, 17, 18, 19, 20 } };
    }

    @Test
    public void test() {
        spiralTraversalArr(build());
    }
}