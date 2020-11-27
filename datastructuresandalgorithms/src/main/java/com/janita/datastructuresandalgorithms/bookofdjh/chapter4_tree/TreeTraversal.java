package com.janita.datastructuresandalgorithms.bookofdjh.chapter4_tree;

import com.janita.datastructuresandalgorithms.bookofdjh.queue.LoopQueueArray;
import com.janita.datastructuresandalgorithms.bookofdjh.queue.Queue;

/**
 * 前序遍历
 *
 * 算法:PreorderTraversal(v)
 * 输入:树节点v
 * 输出:v所有后代的前序遍历序列
 * {
 * if (null != v) {
 * 首先访问并输出v;
 * for (u = v.getFirstChild(); null != u; u = u.getNextSibling()) //依次
 * PreorderTraversal(u);//前序遍历v的各棵子树
 * }
 * }
 *
 * @author zhucj
 * @since 20201126
 */
public class TreeTraversal {

    /**
     * 前序遍历
     *
     * 算法:PreorderTraversal(v)
     * 输入:树节点v
     * 输出:v所有后代的前序遍历序列
     * {
     * if (null != v) {
     * 首先访问并输出v;
     * for (u = v.getFirstChild(); null != u; u = u.getNextSibling()) //依次
     * PreorderTraversal(u);//前序遍历v的各棵子树
     * }
     * }
     */
    public static <E> void preorderTraversal(Tree<E> root) {
        if (root == null) {
            return;
        }
        System.out.println(root.getElem());
        for (Tree<E> son = root.getFirstChild(); son != null; son = son.getNextSibling()) {
            preorderTraversal(son);
        }
    }

    /**
     * 后序遍历
     *
     * 算法:PostorderTraversal(v)
     * 输入:树节点v
     * 输出:v所有后代的后序遍历序列
     * {
     * if (null != v) {
     * for (u = v.getFirstChild(); null != u; u = u.getNextSibling()) //依次
     * PostorderTraversal(u);//后序遍历v的各棵子树
     * 当所有后代都访问过后，最后才访问并输出节点v;
     * }
     * }
     */
    public static <E> void postorderTraversal(Tree<E> root) {
        if (root == null) {
            return;
        }
        for (Tree<E> son = root.getFirstChild(); son != null; son = son.getNextSibling()) {
            postorderTraversal(son);
        }
        //当所有后代都访问过后，最后才访问并输出节点v;
        System.out.println(root.getElem());
    }

    /**
     * 层次遍历
     *
     * 算法:levelOrderTraversal(v)
     * 输入:树节点v
     * 输出:v所有后代的层次遍历序列 {
     * if (null != v) {
     * 创建一个队列Q;
     * Q.enqueue(v);//根节点入队
     * while (!Q.isEmpty()) {//在队列重新变空之前
     * u = Q.dequeue();//取出队列的首节点u
     * 访问并输出u;
     * for (w = u.getFirstChild(); null != w; w = w.nextSibling())//依次将u的
     * Q.enqueue(w);//每个孩子w加至队列中
     * } //while
     * } //if
     * }
     */
    public static <E> void levelOrderTraversal(Tree<E> root) {
        if (root == null) {
            return;
        }
        Queue<Tree<E>> queue = new LoopQueueArray<>();
        queue.enqueue(root);
        while (!queue.isEmpty()) {
            Tree<E> tree = queue.dequeue();
            //访问
            System.out.println(tree.getElem());
            for (Tree<E> son = tree.getFirstChild(); son != null; son = son.getNextSibling()) {
                queue.enqueue(son);
            }
        }
    }
}