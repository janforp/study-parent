package com.janita.datastructuresandalgorithms.book3.chapter4_tree;

import java.util.Vector;

/**
 * BTree
 *
 * @author zhucj
 * @since 20201224
 */
public class BTree<T extends Comparable<? super T>> {

    private BTNode root;//根节点

    private int size;//存放癿兲键码总数

    private int order;//B-树癿阶次，至少为3——创建时指定，一般丌能修改

    public BTree(int order) {
        if (order < 3) {
            throw new RuntimeException("至少为3");
        }
        this.order = order;
        this.size = 0;
        this.root = null;
    }

    public boolean isEmpty() {
        return root == null;
    }

    public int size() {
        return size;
    }

    public boolean contains(T e) {
        return false;
    }

    public boolean insert(T e) {
        return false;
    }

    public boolean remove(T e) {
        return false;
    }

    private void solveOverflow(BTNode node) {//因揑入而上溢乀后癿分裂处理

    }

    void solveUnderflow(BTNode node) {//因删除而下溢之后合并处理

    }

    private class BTNode {

        private BTNode parent;//父节点

        private Vector<T> key;//兲键码向量

        /**
         * 孩子向量的实际长度总是比关键码向量多一。
         */
        private Vector<BTNode> child;//孩子向量(其长度总比key多一)

        BTNode() {
            parent = null;
            key = new Vector<>();
            child = new Vector<>();
        }

        BTNode(T e, BTNode lc, BTNode rc) {
            parent = null;
            key = new Vector<>();
            key.insertElementAt(e, 0);
            child = new Vector<>();
            child.insertElementAt(lc, 0);
            child.insertElementAt(rc, 1);
            if (lc != null) {
                lc.parent = this;
            }
            if (rc != null) {
                rc.parent = this;
            }
        }
    }
}
