package com.janita.datastructuresandalgorithms.book3.chapter4_tree;

import com.google.common.collect.Lists;
import com.google.common.collect.Sets;
import org.junit.Assert;
import org.junit.Test;

import java.util.ArrayList;
import java.util.List;
import java.util.Stack;

/**
 * 后序表达式 转 表达式树
 *
 * @author zhucj
 * @since 20201224
 */
public class PostOrderToExpressionTree {

    private static BinaryNode<String> transform(List<String> postOrderExpression) {
        Stack<BinaryNode<String>> stack = new Stack<>();
        for (String item : postOrderExpression) {
            if (isOperator(item)) {
                BinaryNode<String> right = stack.pop();
                BinaryNode<String> left = stack.pop();
                BinaryNode<String> root = new BinaryNode<>(item, left, right);
                stack.push(root);
            } else {
                stack.push(new BinaryNode<>(item, null, null));
            }
        }
        return stack.pop();
    }

    private static boolean isOperator(String item) {
        return Sets.newHashSet("+", "-", "*", "/").contains(item);
    }

    @Test
    public void test() {
        ArrayList<String> list = Lists.newArrayList("a", "b", "+", "c", "d", "e", "+", "*", "*");
        BinaryNode<String> binaryNode = transform(list);
        Assert.assertNotNull(binaryNode);
        //        inorderTraver(binaryNode);
        inorderTraver2(binaryNode);
    }

    private static void inorderTraver(BinaryNode<String> root) {
        if (root != null) {
            inorderTraver(root.getLeft());
            System.out.println(root.getElement());
            inorderTraver(root.getRight());
        }
    }

    private static void inorderTraver2(BinaryNode<String> root) {
        Stack<BinaryNode<String>> stack = new Stack<>();
        BinaryNode<String> node = root;
        while (node != null || !stack.isEmpty()) {
            if (node != null) {
                stack.push(node);
                node = node.getLeft();
            } else {
                BinaryNode<String> binaryNode = stack.pop();
                System.out.println(binaryNode.getElement());
                node = binaryNode.getRight();
            }
        }
    }
}
