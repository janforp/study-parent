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

    protected BTNode<T> hot;//hot

    protected int size;//存放关键码总数

    protected int order;//B-树癿阶次，至少为3——创建时指定，一般丌能修改

    public BTree(int order) {
        if (order < 3) {
            throw new RuntimeException("至少为3");
        }
        this.order = order;
        this.size = 0;
        this.root = new BTNode<>();
    }

    /**
     * 与二叉搜索树类似，B-树的每一次查找过程中，在每一高度上至多访问 一个节点。
     * 这就意味着，对于高度为h的B-树，外存访问不超过O(h - 1)次。
     *
     * @param e 被查询的数据
     * @return 查询到返回，否则返回null
     */
    private T search(T e) {
        if (e == null) {
            throw new NullPointerException();
        }
        if (isEmpty()) {
            return null;
        }
        BTNode<T> v = root;
        //用于保存查询失败前的节点引用
        hot = null;
        while (v != null) {
            Vector<T> keys = v.keys;
            //找到，或者返回不大于e的最大下标
            int r = searchInKeys(keys, e);
            if (r >= 0 && e.equals(keys.get(r))) {
                //成功:在当前节点命中目标关键码
                return e;
            }
            hot = v;
            //否则，转入对应子树(hot指向其父)——需做I/O，最费时间
            v = v.children.get(r + 1);
        }
        return null;
    }

    /**
     * 与二叉搜索树类似，B-树的每一次查找过程中，在每一高度上至多访问 一个节点。
     * 这就意味着，对于高度为h的B-树，外存访问不超过O(h - 1)次。
     *
     * @param e 被查询的数据
     * @return 查询到返回，否则返回null
     */
    private BTNode<T> findNode(T e) {
        if (e == null) {
            throw new NullPointerException();
        }
        if (isEmpty()) {
            return null;
        }
        BTNode<T> v = root;
        //用于保存查询失败前的节点引用
        hot = null;
        while (v != null) {
            Vector<T> keys = v.keys;
            //找到，或者返回不大于e的最大下标
            int r = searchInKeys(keys, e);
            if (r >= 0 && e.equals(keys.get(r))) {
                //成功:在当前节点命中目标关键码
                return v;
            }
            hot = v;
            //否则，转入对应子树(hot指向其父)——需做I/O，最费时间
            v = v.children.get(r + 1);
        }
        return null;
    }

    /**
     * 若e在keys中存在，则返回e所在的下标，否则返回不大于e的最大下标
     */
    private static <T extends Comparable<? super T>> int searchInKeys(Vector<T> keys, T e) {
        if (e == null) {
            throw new NullPointerException();
        }
        for (int i = 0; i < keys.size(); i++) {
            T t = keys.get(i);
            int compareTo = e.compareTo(t);
            if (compareTo == 0) {
                return i;
            }
            if (compareTo < 0) {
                return i - 1;
            }
            if (i + 1 == keys.size()) {
                return i;
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
        if (e == null) {
            throw new NullPointerException();
        }
        //找到，返回具体的节点，否则元素插入到hot节点中
        T v = search(e);
        if (v != null) {
            //不能插入重复元素
            return false;
        }
        Vector<T> hotKeys = hot.keys;//search 方法必然终止与一个叶子节点
        //接下来，在该节 点中再次查找目标关键码e。尽管这次查找注定失败，
        //却可以确定e在其中的 正确插入位置 r。最后，只需将e插至这一位置。
        int r = searchInKeys(hotKeys, e);//在节点_hot的有序关键码向量中查找合适的插入位置
        hotKeys.insertElementAt(e, r + 1);//将新关键码插至对应的位置
        //新关键码右边添加一个新分支，因为查找失败于叶子节点，所以该节点的孩子引用肯定都是null
        //插入关键码的同时也插入该关键码右侧的child
        hot.children.insertElementAt(null, r + 2);
        size++;//更新规模
        //至此，hot所指的节点中增加了一个关键码。若该节点内关键码的总数依然合法(即不超 过m - 1个)
        //则插入操作随即完成
        //否则，称该节点发生了一次上溢，此时需要 通过适当的处理，使该节点以及整树重新满足B-树的条件。
        //这项任务将借助调 整算法solveOverflow(hot)来完成。
        solveOverflow(hot);//如有必要，需要分裂
        return true;//插入成功
    }

    public boolean remove(T e) {
        BTNode<T> v = findNode(e);
        if (v == null) {//确认目标关键码存在
            return false;
        }
        int r = searchInKeys(v.keys, e);//确定目标关键码在节点v中的下标（由shang，肯定合法）
        if (v.children.get(0) != null) {//若v非叶子，则e的后继必属于某叶节点
            BTNode<T> u = v.children.get(r + 1);//在e的右子树中一直向左，即可
            while (u.children.get(0) != null) {//在右子树中一直向左，即可
                u = u.children.get(0);//找出e的后继
            }
            v.keys.set(r, u.keys.get(0));//并与之交换位置
            v = u;//并与之交换位置
            r = 0;//并与之交换位置
        }//至此，v必然位于最底层，且其中第r个关键码就是待删除者
        v.keys.remove(r);//删除e，以及其下两个外部节点之一
        v.children.remove(r + 1);//删除e，以及其下两个外部节点之一
        size--;
        solveUnderflow(v);
        return true;
    }

    /**
     * 对节点v的上溢进行处理
     *
     * @param toSplitNode 待处理节点
     */
    private void solveOverflow(BTNode<T> toSplitNode) {//因插入而上溢之后的分裂处理
        //若插入节点的孩子数量并没有超过该B-树的阶次，则无需处理
        if (order >= toSplitNode.children.size()) {
            return;//递归基，当前节点并未上溢
        }
        //否则，该节点发生了上溢，下面的逻辑进行处理。

        //取中位数关键码作为分割点
        //待处理节点v的关键码： 0,1,2...s-1; s; s+1,s+2,....order-1
        // 因为刚刚发生上溢，所以该节点有 order-1 - 0 + 1 = order 个关键码，
        // 超出了关键码上限 order - 1
        //如order=6,则,s = 6 / 2 = 3,则分为：0,1,2; 3; 4,5
        int s = order / 2;//轴点(此时应有_order = key.size() = child.size() - 1)
        BTNode<T> splitNode = new BTNode<>();//注意:新节点已有一个空孩子

        //v右侧的 order-s-1 个孩子及关键码分裂为右侧节点u (order - 1 - s - 1 + 1 = order - s - 1)
        for (int j = 0; j < order - s - 1; j++) {
            //之前上溢节点v已经分裂为2个节点，v以及u，现在要把v左侧的孩子移到u节点中
            BTNode<T> removeNode = toSplitNode.children.remove(s + 1);
            splitNode.children.insertElementAt(removeNode, j);//逐个移动效率低
            //之前上溢节点v已经分裂为2个节点，v以及u，现在要把v左侧的关键码移到u节点中
            T removeKey = toSplitNode.keys.remove(s + 1);
            splitNode.keys.insertElementAt(removeKey, j);//此策略可以改进
        }
        //移动v最靠右的孩子，因为孩子比节点多一个
        BTNode<T> lastRemoveNode = toSplitNode.children.remove(s + 1);
        splitNode.children.setElementAt(lastRemoveNode, order - s - 1);
        if (splitNode.children.get(0) != null) {//若u的孩子们非空，则
            for (int j = 0; j < order - s; j++) {//将他们的父节点统一
                splitNode.children.get(j).parent = splitNode;//指向u
            }
        }

        //v当前的父节点p,其实也是分裂出来的节点u的父亲
        BTNode<T> p = toSplitNode.parent;
        if (p == null) {//若p为空则创建之
            root = p = new BTNode<>();
            p.children.setElementAt(toSplitNode, 0);
            toSplitNode.parent = p;
        }
        int r = 1 + searchInKeys(p.keys, toSplitNode.keys.get(0));//p中指向u的下标
        p.keys.insertElementAt(toSplitNode.keys.remove(s), r);//轴点兲键码上升
        p.children.insertElementAt(splitNode, r + 1);//新节点u与父节点p互联联
        splitNode.parent = p;//新节点u与父节点p互联
        solveOverflow(p);//上升一局，如有必要则继续分裂——至多递归O(log n)层
    }

    /**
     * //兲键码初除后若节点下溢，则做节点旋转戒合幵处理
     */
    private void solveUnderflow(BTNode<T> v) {//因删除而下溢之后合并处理
        if (v.children.size() >= (order + 1) / 2) {
            return;
        }
        BTNode<T> p = v.parent;
        if (p == null) {
            if (v.keys.isEmpty() && v.children.get(0) != null) {
                root = v.children.get(0);
                root.parent = null;
                v.children.set(0, null);
            }
            return;
        }
        int r = 0;
        while (p.children.get(r) != v) {
            r++;
        }
        if (r > 0) {
            BTNode<T> ls = p.children.get(r - 1);
            if ((order + 1) / 2 < ls.children.size()) {
                v.keys.insertElementAt(p.keys.remove(r - 1), 0);
                p.keys.set(r - 1, ls.keys.remove(ls.keys.size() - 1));
                v.children.insertElementAt(ls.children.remove(ls.children.size() - 1), 0);
                if (v.children.get(0) != null) {
                    v.children.get(0).parent = v;
                }
                return;
            }
        }

        if (p.children.size() - 1 > r) {
            BTNode<T> rs = p.children.get(r + 1);
            if ((order + 1) / 2 < rs.children.size()) {
                v.keys.insertElementAt(p.keys.get(r), v.keys.size());
                p.keys.set(r, rs.keys.remove(0));
                v.children.insertElementAt(rs.children.remove(0), v.children.size());
                if (v.children.get(v.children.size() - 1) != null) {
                    v.children.get(v.children.size() - 1).parent = v;
                }
                return;
            }
        }

        if (0 < r) {
            BTNode<T> ls = p.children.get(r - 1);
            ls.keys.insertElementAt(p.keys.remove(r - 1), ls.keys.size());
            p.children.remove(r);
            ls.children.insertElementAt(v.children.remove(0), ls.children.size());
            if (ls.children.get(ls.children.size() - 1) != null) {
                ls.children.get(ls.children.size() - 1).parent = ls;
            }
            while (!v.keys.isEmpty()) {
                ls.keys.insertElementAt(v.keys.remove(0), ls.keys.size());
                ls.children.insertElementAt(v.children.remove(0), ls.children.size());
                if (ls.children.get(ls.children.size() - 1) != null) {
                    ls.children.get(ls.children.size() - 1).parent = ls;
                }
            }
        } else {
            BTNode<T> rs = p.children.get(r + 1);
            rs.keys.insertElementAt(p.keys.remove(r), 0);
            p.children.remove(r);
            rs.children.insertElementAt(v.children.remove(v.children.size() - 1), 0);
            if (rs.children.get(0) != null) {
                rs.children.get(0).parent = rs;
            }
            while (!v.keys.isEmpty()) {
                rs.keys.insertElementAt(v.keys.remove(v.keys.size() - 1), 0);
                rs.children.insertElementAt(v.children.remove(v.children.size() - 1), 0);
                if (rs.children.get(0) != null) {
                    rs.children.get(0).parent = rs;
                }
            }
        }
        solveUnderflow(p);
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
    public static class BTNode<T extends Comparable<? super T>> {

        public BTNode<T> parent;//父节点

        public Vector<T> keys;//兲键码向量

        /**
         * 孩子向量的实际长度总是比关键码向量多一。
         */
        public Vector<BTNode<T>> children;//孩子向量(其长度总比key多一)

        /**
         * 注意:BTNode叧能作为根节点创建，而且刜始时有0个关键码和1个空孩子
         */
        BTNode() {
            parent = null;
            keys = new Vector<>();
            children = new Vector<>();
            //最左侧的孩子先放进去，一个空孩子
            children.insertElementAt(null, 0);
        }

        BTNode(T e, BTNode<T> lc, BTNode<T> rc) {
            parent = null;//作为根节点，而且刜始时
            keys = new Vector<>();
            keys.insertElementAt(e, 0);//叧有一个兲键码，以及
            children = new Vector<>();
            children.insertElementAt(lc, 0);//两个孩子
            children.insertElementAt(rc, 1);
            if (lc != null) {
                lc.parent = this;
            }
            if (rc != null) {
                rc.parent = this;
            }
        }

        @Override
        public String toString() {
            return "BTNode{" +
                    "keys=" + keys +
                    ", children=" + children +
                    '}';
        }
    }
}
