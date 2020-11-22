package com.janita.datastructuresandalgorithms.bookofdjh.stack;

import org.junit.Assert;
import org.junit.Test;

/**
 * ArrayReverse
 *
 * @author zhucj
 * @since 20201126
 */
public class ArrayReverse {

    public static Integer[] reverse(Integer[] a) {
        if (a == null || a.length == 0) {
            return a;
        }
        Stack stack = new StackArray(a.length);
        Integer[] b = new Integer[a.length];
        for (int i = 0; i < a.length; i++) {
            stack.push(a[i]);
        }
        for (int i = 0; i < a.length; i++) {
            b[i] = (Integer) stack.pop();
        }
        return b;
    }

    @Test
    public void testReverse() {
        Integer[] integers = { 1, 2, 3, 4, 5 };
        Integer[] reverse = reverse(integers);
        Assert.assertEquals(5, (int) reverse[0]);
        Assert.assertEquals(4, (int) reverse[1]);
        Assert.assertEquals(3, (int) reverse[2]);
        Assert.assertEquals(2, (int) reverse[3]);
        Assert.assertEquals(1, (int) reverse[4]);
    }
}
