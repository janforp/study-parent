package com.janita.datastructuresandalgorithms.bilibili.traversalbinnode;

import org.junit.Assert;
import org.junit.Test;

import java.util.ArrayList;
import java.util.List;
import java.util.Stack;

/**
 * 中遍历
 *
 * LVR
 *
 * @author zhucj
 * @since 20210128
 */
public class TraversalPost {

    /**
     * 迭代实现
     *
     * @param node 二叉树根节点
     * @param <T> 类型
     */
    public static <T> List<T> traversalPost(BinNode<T> node) {
        if (node == null) {
            return null;
        }
        Stack<BinNode<T>> stack = new Stack<>();
        List<T> list = new ArrayList<>();
        while (true) {
            visitAloneLeftBranch(node, list, stack);
            if (stack.isEmpty()) {
                break;
            }
            node = stack.pop();
        }
        return list;
    }

    /**
     * 沿着节点的左边一直往下，遇到的元素直接访问
     *
     * @param node 节点
     * @param list 遍历序列
     * @param stack 辅助栈
     * @param <T> 类型
     */
    private static <T> void visitAloneLeftBranch(BinNode<T> node, List<T> list, Stack<BinNode<T>> stack) {
        if (node == null) {
            return;
        }
        while (node != null) {
            list.add(node.data);
            stack.push(node.right);
            node = node.left;
        }
    }

    /**
     * 后序遍历递归实现
     *
     * @param node 二叉树根节点
     * @param list 遍历序列
     * @param <T> 类型
     */
    public static <T> void traversalPostRec(BinNode<T> node, List<T> list) {
        if (node == null) {
            return;
        }
        traversalPostRec(node.left, list);
        traversalPostRec(node.right, list);
        list.add(node.data);
    }

    @Test
    public void test() {
        BinNode<String> binNode = BinNode.build();
        List<String> listIt = traversalPost(binNode);
        List<String> listRe = new ArrayList<>();
        traversalPostRec(binNode, listRe);
        Assert.assertEquals(listIt, listRe);
    }
}
