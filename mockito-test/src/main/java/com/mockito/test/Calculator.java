package com.mockito.test;

/**
 * 类说明：
 *
 * @author zhucj
 * @since 20200423
 */
public class Calculator {

    private int sumXX(int a, int b) {
        System.out.println("sumXX");
        return a + b;
    }

    public int callSumXX(int a, int b) {
        System.out.println("callSumXX");
        return  sumXX(a, b);
    }
}
