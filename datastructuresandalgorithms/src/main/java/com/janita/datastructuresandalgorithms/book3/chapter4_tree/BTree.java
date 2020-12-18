package com.janita.datastructuresandalgorithms.book3.chapter4_tree;

import java.util.Vector;

/**
 * BTree
 *
 * @author zhucj
 * @since 20201224
 */
public class BTree<T extends Comparable<? super T>> {

    protected BTNode<T> root;//根节点

    //TODO ?
    protected BTNode<T> hot;//hot

    protected int size;//存放关键码总数

    protected int order;//B-树癿阶次，至少为3——创建时指定，一般丌能修改

    public BTree(int order) {
        if (order < 3) {
            throw new RuntimeException("至少为3");
        }
        this.order = order;
        this.size = 0;
        //TODO ? C++为何此处初始化了root？
        this.root = new BTNode<>();
    }

    /**
     * 与二叉搜索树类似，B-树的每一次查找过程中，在每一高度上至多访问 一个节点。这就意味着，对于高度为h的B-树，外存访问不超过O(h - 1)次。
     *
     * TODO 查找过程必然终止于某一外部节点v，且其父节点由变量 _hot指示
     *
     * @param e 被查询的数据
     * @return 查询到返回，否则返回null
     */
    public T search(T e) {
        BTNode<T> v = root;
        hot = null;
        while (v != null) {
            Vector<T> key = v.key;
            int r = search(key, e);
            if (r >= 0 && e.equals(key.get(r))) {
                //成功:在当前节点命中目标关键码
                return e;
            }
            hot = v;
            //否则，转入对应子树(hot指向其父)——需做I/O，最费时间
            v = v.child.get(r + 1);
        }
        return null;
    }

    /**
     * 在key中，找到不大于e的最大index
     */
    private static <T extends Comparable<? super T>> int search(Vector<T> key, T e) {
        for (int i = 0; i < key.size(); i++) {
            T t = key.get(i);
            int compareTo = e.compareTo(t);
            if (compareTo == 0) {
                return i;
            }
            if (compareTo < 0) {
                return i - 1;
            }
        }
        return -1;
    }

    public boolean insert(T e) {
        T v = search(e);
        if (v != null) {
            //不能插入重复元素
            return false;
        }
        Vector<T> key = hot.key;
        //接下来，在该节 点中再次查找目标关键码e。尽管这次查找注定失败，却可以确定e在其中的正确插入位置r。最 后，只需将e插至这一位置。
        int r = search(key, e);//在节点_hot的有序关键码向量中查找合适的插入位置
        key.insertElementAt(e, r + 1);//将新关键码插至对应的位置
        hot.child.insertElementAt(null, r + 2);//TODO 创建一个空子树指针
        size++;//更新规模
        //至此，_hot所指的节点中增加了一个关键码。若该节点内关键码的总数依然合法(即不超 过m - 1个)，则插入操作随即完成
        //否则，称该节点发生了一次上溢(overflow)，此时需要 通过适当的处理，使该节点以及整树重新满足B-树的条件。
        // 由代码8.9可见，这项任务将借助调 整算法solveOverflow(_hot)来完成。
        solveOverflow(hot);//如有必要，需要分裂
        return true;//插入成功
    }

    public boolean remove(T e) {
        return false;
    }

    private void solveOverflow(BTNode<T> node) {//因插入而上溢之后的分裂处理

    }

    private void solveUnderflow(BTNode<T> node) {//因删除而下溢之后合并处理

    }

    public boolean isEmpty() {
        return root == null;
    }

    public int size() {
        return size;
    }

    private int order() {
        return order;
    }

    /**
     * B-树节点模板类
     * I N I N I N I
     * |N| = 3
     * |I| = |N| + 1 = 4
     * 孩子向量的实际长度总是比关键码向量多一。
     */
    private static class BTNode<T extends Comparable<? super T>> {

        private BTNode<T> parent;//父节点

        private Vector<T> key;//兲键码向量

        /**
         * 孩子向量的实际长度总是比关键码向量多一。
         */
        private Vector<BTNode<T>> child;//孩子向量(其长度总比key多一)

        /**
         * 注意:BTNode叧能作为根节点创建，而且刜始时有0个关键码和1个空孩子
         */
        BTNode() {
            parent = null;
            key = new Vector<>();
            child = new Vector<>();
        }

        BTNode(T e, BTNode<T> lc, BTNode<T> rc) {
            parent = null;//作为根节点，而且刜始时
            key = new Vector<>();
            key.insertElementAt(e, 0);//叧有一个兲键码，以及
            child = new Vector<>();
            child.insertElementAt(lc, 0);//两个孩子
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
