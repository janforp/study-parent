package com.janita.datastructuresandalgorithms.book3.chapter3;

import org.junit.Assert;
import org.junit.Test;

import java.util.ArrayList;
import java.util.List;
import java.util.Stack;

/**
 * Exercise3_21
 *
 * @author zhucj
 * @since 20201224
 */
public class Exercise3_22 {

    public static int calculate(List<String> list) {
        Stack<Integer> stack = new Stack<>();
        for (String item : list) {
            if ("+".equals(item)) {
                stack.push(stack.pop() + stack.pop());
                continue;
            }
            if ("-".equals(item)) {
                stack.push(stack.pop() - stack.pop());
                continue;
            }

            if ("*".equals(item)) {
                stack.push(stack.pop() * stack.pop());
                continue;
            }
            if ("/".equals(item)) {
                stack.push(stack.pop() / stack.pop());
                continue;
            }
            stack.push(Integer.valueOf(item));
        }
        return stack.pop();
    }

    @Test
    public void test() {
        List<String> list = new ArrayList<>();
        list.add("1");
        list.add("2");
        list.add("3");
        list.add("*");
        list.add("+");
        list.add("4");
        list.add("5");
        list.add("*");
        list.add("6");
        list.add("+");
        list.add("7");
        list.add("*");
        list.add("+");
        Assert.assertEquals(189, calculate(list));
    }
}
