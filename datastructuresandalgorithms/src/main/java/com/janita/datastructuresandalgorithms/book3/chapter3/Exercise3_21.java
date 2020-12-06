package com.janita.datastructuresandalgorithms.book3.chapter3;

import org.junit.Assert;
import org.junit.Test;

import java.util.Stack;

/**
 * Exercise3_21
 *
 * @author zhucj
 * @since 20201224
 */
public class Exercise3_21 {

    public static boolean valid(String express) {
        if (express == null || express.trim().length() == 0) {
            return true;
        }
        Stack<String> stack = new Stack<>();
        int index = 0;
        while (index < express.length()) {
            char c = express.charAt(index);
            if (c == '/' && express.charAt(index + 1) == '*') {
                stack.push("/*");
                index = index + 2;
                continue;
            }
            if (c == '*' && express.charAt(index + 1) == '/') {
                String pop = stack.pop();
                if (!"/*".equals(pop)) {
                    return false;
                }
                index = index + 2;
                continue;
            }
            if (c == '(') {
                stack.push(c + "");
                index++;
                continue;
            }
            if (c == ')') {
                String pop = stack.pop();
                if (!"(".equals(pop)) {
                    return false;
                }
                index++;
                continue;
            }
            if (c == '[') {
                stack.push(c + "");
                index++;
                continue;
            }
            if (c == ']') {
                String pop = stack.pop();
                if (!"[".equals(pop)) {
                    return false;
                }
                index++;
                continue;
            }
            if (c == '{') {
                stack.push(c + "");
                index++;
                continue;
            }
            if (c == '}') {
                String pop = stack.pop();
                if (!"{".equals(pop)) {
                    return false;
                }
                index++;
                continue;
            }
            index++;
        }
        return stack.isEmpty();
    }

    @Test
    public void test() {
        String express = "";
        Assert.assertTrue(valid(express));
        express = "a + b + (c + d) + {e+[f+11]+9}";
        Assert.assertTrue(valid(express));

        express = "a + b + (c + d) + {e+[f+11]+9]}";
        Assert.assertFalse(valid(express));
    }
}
