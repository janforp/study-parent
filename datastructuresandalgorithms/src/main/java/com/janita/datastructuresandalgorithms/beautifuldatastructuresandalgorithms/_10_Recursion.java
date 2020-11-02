package com.janita.datastructuresandalgorithms.beautifuldatastructuresandalgorithms;

/**
 * _10_Recursion
 *
 * @author zhucj
 * @since 20201126
 */
public class _10_Recursion {

    /**
     * f(n) = f(n-1) +1;f(1) = 1;
     *
     * @param n
     * @return
     */
    private static int f(int n) {
        if (n == 1) {
            return 1;
        }
        return f(n - 1) + 1;
    }
}
