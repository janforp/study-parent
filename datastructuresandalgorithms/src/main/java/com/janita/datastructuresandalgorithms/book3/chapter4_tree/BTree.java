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
            throw new RuntimeException("至少为4");
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
            int r = searchInKey(key, e);
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
    private static <T extends Comparable<? super T>> int searchInKey(Vector<T> key, T e) {
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

    /**
     * O(h)
     *
     * @param e
     * @return
     */
    public boolean insert(T e) {
        T v = search(e);
        if (v != null) {
            //不能插入重复元素
            return false;
        }
        Vector<T> key = hot.key;//search 方法必然终止与一个叶子节点
        //接下来，在该节 点中再次查找目标关键码e。尽管这次查找注定失败，却可以确定e在其中的正确插入位置r。最 后，只需将e插至这一位置。
        int r = searchInKey(key, e);//在节点_hot的有序关键码向量中查找合适的插入位置
        key.insertElementAt(e, r + 1);//将新关键码插至对应的位置
        hot.child.insertElementAt(null, r + 2);//新关键码右边添加一个新分支，因为查找失败于叶子节点，所以该节点的孩子引用肯定都是null
        size++;//更新规模
        //至此，_hot所指的节点中增加了一个关键码。若该节点内关键码的总数依然合法(即不超 过m - 1个)，则插入操作随即完成
        //否则，称该节点发生了一次上溢(overflow)，此时需要 通过适当的处理，使该节点以及整树重新满足B-树的条件。
        // 由代码8.9可见，这项任务将借助调 整算法solveOverflow(_hot)来完成。
        solveOverflow(hot);//如有必要，需要分裂
        return true;//插入成功
    }

    private boolean insert2(T e) {
        T v = search(e);
        if (v != null) {
            //不能插入重复元素
            return false;
        }
        Vector<T> key = hot.key;//search 方法必然终止与一个叶子节点
        //接下来，在该节 点中再次查找目标关键码e。尽管这次查找注定失败，却可以确定e在其中的正确插入位置r。最 后，只需将e插至这一位置。
        int r = searchInKey(key, e);//在节点_hot的有序关键码向量中查找合适的插入位置
        key.insertElementAt(e, r + 1);//将新关键码插至对应的位置
        hot.child.add(null);//新关键码右边添加一个新分支，因为查找失败于叶子节点，所以该节点的孩子引用肯定都是null,该写法与 insert 写法效果一样，但是比较费解，所以编码还是要有一定的语意，不能往前想几步！！！！！
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

    /**
     * 对节点v的上溢进行处理
     *
     * @param v 待处理节点
     */
    private void solveOverflow(BTNode<T> v) {//因插入而上溢之后的分裂处理
        //若插入节点的孩子数量并没有超过该B-树的阶次，则无需处理
        if (order >= v.child.size()) {
            return;//递归基，当前节点并未上溢
        }
        //否则，该节点发生了上溢，下面的逻辑进行处理。

        //取中位数关键码作为分割点
        //待处理节点v的关键码： 0,1,2...s-1; s; s+1,s+2,....order-1
        // 因为刚刚发生上溢，所以该节点有 order-1 - 0 + 1 = order 个关键码，
        // 超出了关键码上限 order - 1
        //如order=6,则,s = 6 / 2 = 3,则分为：0,1,2; 3; 4,5
        int s = order / 2;//轴点(此时应有_order = key.size() = child.size() - 1)
        BTNode<T> u = new BTNode<>();//注意:新节点已有一个空孩子
        for (int j = 0; j < order - s - 1; j++) {//v右侧的 order-s-1 个孩子及关键码分裂为右侧节点u
            //之前上溢节点v已经分裂为2个节点，v以及u，现在要把v左侧的孩子移到u节点中
            u.child.insertElementAt(v.child.remove(s + 1), j);//逐个移动效率低
            //之前上溢节点v已经分裂为2个节点，v以及u，现在要把v左侧的关键码移到u节点中
            u.key.insertElementAt(v.key.remove(s + 1), j);//此策略可以改进
        }
        //移动v最靠右的孩子
        //TODO ？
        u.child.setElementAt(v.child.remove(s + 1), order - s - 1);
        if (u.child.get(0) != null) {//若u的孩子们非空，则
            for (int j = 0; j < order - s; j++) {//将他们的父节点统一
                u.child.get(j).parent = u;//指向u
            }
        }
        BTNode<T> p = v.parent;//v当前的父节点p
        if (p == null) {//若p为空则创建之
            root = p = new BTNode<>();
            p.child.setElementAt(v, 0);
            v.parent = p;
        }
        int r = 1 + searchInKey(p.key, v.key.get(0));//p中指向u的下标
        p.key.insertElementAt(v.key.remove(s), r);//轴点兲键码上升
        p.child.insertElementAt(u, r + 1);//新节点u与父节点p互//新节点u与父节点p互联联
        u.parent = p;//新节点u与父节点p互联
        solveOverflow(p);//上升一局，如有必要则继续分裂——至多递归O(log n)层
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
            //最左侧的孩子先放进去，一个空孩子
            child.insertElementAt(null, 0);
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
