package com.janita.datastructuresandalgorithms.bilibili.traversalbinnode;

import org.junit.Assert;
import org.junit.Test;

import java.util.ArrayList;
import java.util.List;
import java.util.Stack;

/**
 * 中序遍历
 *
 * @author zhucj
 * @since 20210128
 */
public class TraversalIn {

    /**
     * 中序遍历递归实现
     *
     * @param node 二叉树根节点
     * @param list 遍历序列
     * @param <T> 类型
     */
    public static <T> void traversalInRec(BinNode<T> node, List<T> list) {
        if (node == null) {
            return;
        }
        traversalInRec(node.left, list);
        list.add(node.data);
        traversalInRec(node.right, list);
    }

    private static <T> void goAlongLeftBranch(BinNode<T> node, Stack<BinNode<T>> stack) {
        while (node != null) {
            stack.push(node);
            node = node.left;
        }
    }

    public static <T> List<T> traversalIn(BinNode<T> node) {
        if (node == null) {
            return null;
        }
        //辅助栈
        Stack<BinNode<T>> stack = new Stack<>();
        //返回的遍历序列
        List<T> list = new ArrayList<>();
        while (true) {
            goAlongLeftBranch(node, stack);
            if (stack.isEmpty()) {
                break;
            }
            BinNode<T> pop = stack.pop();
            if (pop != null) {
                list.add(pop.data);
            }
            if (node != null) {
                node = node.right;
            }
        }
        return list;
    }

    @Test
    public void test() {
        BinNode<String> binNode = BinNode.build();
        List<String> listIt = traversalIn(binNode);
        List<String> listRe = new ArrayList<>();
        traversalInRec(binNode, listRe);
        Assert.assertEquals(listIt, listRe);
    }
}