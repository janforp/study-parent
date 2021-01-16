package com.janita.datastructuresandalgorithms.bilibili.traversalbinnode;

import org.junit.Assert;
import org.junit.Test;

import java.util.ArrayList;
import java.util.List;
import java.util.Stack;

/**
 * 先序遍历
 *
 * VLR
 *
 * @author zhucj
 * @since 20210128
 */
public class TraversalPre {

    /**
     * 迭代实现
     *
     * @param node 二叉树根节点
     * @param <T> 类型
     */
    public static <T> List<T> traversalPre(BinNode<T> node) {
        if (node == null) {
            return null;
        }
        //辅助栈
        Stack<BinNode<T>> stack = new Stack<>();
        //返回的遍历序列
        List<T> list = new ArrayList<>();
        while (true) {
            //从当前节点出发，逐批访问
            visitAloneLeftBranch(node, list, stack);
            //直到栈空
            if (stack.isEmpty()) {
                break;
            }
            //弹出下一批的起点
            node = stack.pop();
        }
        return list;
    }

    /**
     * 从当前节点出发，沿左分支不断深入，直至没有左分支的节点，沿途节点遇到后立即访问（也就是放入list）
     *
     * @param node 节点
     * @param list 遍历序列
     * @param stack 辅助栈
     * @param <T> 类型
     */
    private static <T> void visitAloneLeftBranch(BinNode<T> node, List<T> list,
            Stack<BinNode<T>> stack) {
        if (node == null) {
            return;
        }
        while (node != null) {
            //访问当前节点
            list.add(node.data);
            //右孩子入栈
            stack.push(node.right);
            //沿左分支深入一层
            node = node.left;
        }
    }

    /**
     * 先序遍历递归实现
     *
     * @param node 二叉树根节点
     * @param list 遍历序列
     * @param <T> 类型
     */
    public static <T> void traversalPreRec(BinNode<T> node, List<T> list) {
        if (node == null) {
            return;
        }
        list.add(node.data);
        //尾递归
        traversalPreRec(node.left, list);
        traversalPreRec(node.right, list);
    }

    @Test
    public void test() {
        BinNode<String> binNode = BinNode.build();
        List<String> listIt = traversalPre(binNode);
        List<String> listRe = new ArrayList<>();
        traversalPreRec(binNode, listRe);
        Assert.assertEquals(listIt, listRe);
    }
}
