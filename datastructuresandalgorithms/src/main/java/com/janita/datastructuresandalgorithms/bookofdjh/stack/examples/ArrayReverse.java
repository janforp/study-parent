package com.janita.datastructuresandalgorithms.bookofdjh.stack.examples;

import com.janita.datastructuresandalgorithms.bookofdjh.stack.Stack;
import com.janita.datastructuresandalgorithms.bookofdjh.stack.StackArray;
import org.junit.Assert;
import org.junit.Test;

/**
 * 借助栈进行数组倒置
 *
 * @author zhucj
 * @since 20201126
 */
public class ArrayReverse {

    public static Integer[] reverseArrayByStack(Integer[] a) {
        if (a == null || a.length == 0) {
            return a;
        }
        Stack stack = new StackArray(a.length);
        for (Integer integer : a) {
            stack.push(integer);
        }
        for (int i = 0; i < a.length; i++) {
            a[i] = (Integer) stack.pop();
        }
        return a;
    }

    @Test
    public void testReverse() {
        Integer[] integers = { 1, 2, 3, 4, 5 };
        Integer[] reverse = reverseArrayByStack(integers);
        Assert.assertEquals(5, (int) reverse[0]);
        Assert.assertEquals(4, (int) reverse[1]);
        Assert.assertEquals(3, (int) reverse[2]);
        Assert.assertEquals(2, (int) reverse[3]);
        Assert.assertEquals(1, (int) reverse[4]);
    }
}
