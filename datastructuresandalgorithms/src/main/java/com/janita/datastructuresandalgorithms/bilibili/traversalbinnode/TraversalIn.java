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

    /**
     * 从当前节点出发，沿左侧分支不断深入，直至没有左分支的节点
     *
     * @param node 节点
     * @param stack 辅助栈
     * @param <T> 类型
     */
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
            //从当前节点出发，逐批入栈
            goAlongLeftBranch(node, stack);
            //直至所有节点都出去完毕
            if (stack.isEmpty()) {
                break;
            }
            //弹出栈顶节点并访问
            node = stack.pop();
            if (node != null) {
                list.add(node.data);
            }
            if (node != null) {
                //转向右子树
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