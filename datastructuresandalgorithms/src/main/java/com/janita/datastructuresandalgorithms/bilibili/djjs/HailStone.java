package com.janita.datastructuresandalgorithms.bilibili.djjs;

/**
 * HailStone
 *
 * @author zhucj
 * @since 20201126
 */
public class HailStone {

    public static int hailstone(int n) {
        int le = 1;
        while (n > 1) {
            boolean even = n % 2 == 0;
            if (!even) {
                n = 3 * n + 1;
            } else {
                n = n / 2;
            }
            le++;
        }
        return le;
    }

    public static void main(String[] args) {
        System.out.println(hailstone(7));
    }
}
