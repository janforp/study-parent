package com.janita.datastructuresandalgorithms.bookofdjh.stack.examples;

import org.junit.Assert;
import org.junit.Test;

/**
 * Factorial
 *
 * @author zhucj
 * @since 20201126
 */
public class Factorial {

    /**
     * 为了更好地说明Java运行栈支持递归方法的机制，我们来考察另一经典的递归函数:阶乘Fac(n) = n!
     *
     * @param n
     * @return
     */
    public static long factorial(long n) {
        if (n <= 1) {
            return 1;
        }
        return n * factorial(n - 1);
    }

    @Test
    public void testFactorial() {
        Assert.assertEquals(factorial(1), 1);
        Assert.assertEquals(factorial(3), 6);
    }
}
