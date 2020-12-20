package com.janita.datastructuresandalgorithms.book3.chapter4_tree;

import java.util.Vector;

/**
 * BTree2
 *
 * https://blog.csdn.net/yjw123456/article/details/105599745?utm_source=app
 *
 * @author zhucj
 * @see https://blog.csdn.net/yjw123456/article/details/105599745?utm_source=app
 * @since 20201224
 */
public class BTree2<E extends Comparable<? super E>> {

    private static final int DEFAULT_M = 3;

    private int size;

    private Node root;

    private final int m;

    private Node lastReachedNode;

    /**
     * 节点的关键码低于该值就发生了下溢
     */
    private final int ceilHalfM;

    public BTree2(int m) {
        if (m <= 2) {
            throw new IllegalArgumentException("m 至少为 3");
        }
        this.m = m;
        size = 0;
        root = new Node();
        this.ceilHalfM = (m + 1) / 2;
    }

    public BTree2() {
        this(DEFAULT_M);
    }

    public int size() {
        return size;
    }

    private Node search(E e) {
        Node p = root;
        lastReachedNode = null;
        while (p != null) {
            int rank = searchInKeys(p.keys, e);
            if (rank >= 0 && e.compareTo(p.keys.get(rank)) == 0) {
                return p;
            }
            lastReachedNode = p;
            p = p.children.get(rank + 1);
        }
        return null;
    }

    public boolean insert(E e) {
        Node node = search(e);
        if (node != null) {
            return false;
        }
        int rank = searchInKeys(lastReachedNode.keys, e);
        lastReachedNode.insertNode(rank + 1, e, null);
        size++;
        split(lastReachedNode);
        return true;
    }

    private void split(Node node) {
        if (node.children.size() <= m) {
            return;
        }
        //取中位数s,以关键字k_s为界划分为： k_0,...,k_(s-1),  k_s  ,k_(s+1),...,k_(m_1)
        int s = m / 2;
        Node newNode = new Node();//新节点最左侧已有一个空分支
        for (int i = 0; i < m - s - 1; i++) {
            //将s+1到m-1的元素(包括关键码和分支)逐个移到newNode中 ，共有 m-1-(s+1)+1 = m-s-1个 即endIndex-startIndex+1
            newNode.children.insertElementAt(node.children.remove(s + 1), i);
            newNode.keys.insertElementAt(node.keys.remove(s + 1), i);
        }
        //移动node的最后一个分支到newNode中,此时node的关键码数和分支数相同，那是因为中位数关键码还未提升
        newNode.setChild(m - s - 1, node.children.remove(s + 1));

        if (!newNode.isLeaf()) {
            //如果新节点不是叶子节点，新节点有m-s个分支,设置其分支的父节点指向它
            for (int i = 0; i < m - s; i++) {
                newNode.getChild(i).parent = newNode;
            }
        }

        //获取node当前的父节点p,若p不为空，它的左孩子已经指向node了，不需要修改，只要让其右孩子指向newNode
        Node p = node.parent;
        if (p == null) {//如果分裂的是根节点
            p = new Node();//虽然此时最新分裂生成的新根节点还没有关键码，但是后面会把第 s 个关键码放进去的
            root = p;

            //node作为新父节点的左孩子,后面有newNode作为它的右孩子
            p.setChild(0, node);
            node.parent = p;
        }

        //得到中位数关键码在p节点中的合适位置
        //不大于中位数的最大元素的位置  + 1，注意这种处理手法，多次出现
        int rank = 1 + searchInKeys(p.keys, node.getKey(0));
        //中位数对应关键码上升,newNode作为它的右孩子
        p.insertNode(rank, node.keys.remove(s), newNode);
        //上升一层，可能需要继续分裂
        split(p);

    }

    public boolean remove(E e) {
        Node node = search(e);
        if (node == null) {
            return false;
        }
        //得到e在节点中的位置
        int rank = searchInKeys(node.keys, e);
        if (!node.isLeaf()) {//如果非叶子，则找到后继节点
            Node p = node.children.get(rank + 1);//右子树中一直向左
            while (!p.isLeaf()) {
                //找到node的后继节点
                p = p.children.get(0);
            }
            //将node与后继节点交换位置
            node.keys.set(rank, p.getKey(0));
            node = p;
            rank = 0;
        }

        //此时node位于最底层，其中第rank个关键码就是待删除者
        //除了要删除关键码，还要删除该关键码的右分支
        node.keys.remove(rank);
        node.keys.remove(rank + 1);
        size--;
        solveUnderflow(node);
        return true;
    }

    /**
     * 解决下溢问题：删除后关键码数量不满足B树定义:不小于ceil(m/2)
     *
     * @param node
     */
    private void solveUnderflow(Node node) {
        if (node.children.size() >= ceilHalfM) {
            return;
        }
        Node p = node.parent;
        if (p == null) {//已经到了根节点，没有分支的下限
            //树根已不含有关键码，却有唯一的非空孩子,将它作为新根，这是整颗树高度下降的唯一场景
            if (node.keys.size() == 0 && node.getChild(0) != null) {
                root = node.getChild(0);
                root.parent = null;
                node.setChild(0, null);//node对应的引用可被回收了
            }
            return;
        }

        //还未达到根节点
        //确定node是p的第rank个孩子,此时p可能不含关键码，不能通过关键码查找
        int rank = 0;
        while (node.compareTo(p.getChild(rank)) != 0) {
            rank++;
        }

        //向左兄弟借关键码
        if (rank > 0) {
            //若node不是p的第一个孩子，必存在左兄弟
            Node ls = p.getChild(rank - 1);//left sibling : ls
            if (ls.children.size() > ceilHalfM) {//若左兄弟的关键码足够
                //rank - 1关键码处的右孩子是rank

                //这里借的是对应的父节点的关键码
                node.keys.insertElementAt(p.getKey(rank - 1), 0);
                //将左兄弟最大的关键码提升到父节点位置
                p.setKey(rank - 1, ls.keys.remove(rank - 1));

                //将左兄弟最右侧的孩子给node
                node.children.insertElementAt(ls.children.remove(ls.children.size() - 1), 0);
                if (!node.isLeaf()) {
                    //如果该孩子不是空还需要修改parent引用
                    node.children.get(0).parent = node;
                }
                return;
            }
            //若左兄弟关键码不够
        }

        //代码到了这里说明 左兄弟为空或 左兄弟关键码数不够

        //若有右兄弟，向右兄弟借关键码，和向左兄弟借关键码是一个镜像操作
        if (p.children.size() - 1 > rank) {
            Node rs = p.getChild(rank + 1);
            //若右兄弟关键码数足够
            if (rs.children.size() > ceilHalfM) {
                //p借出一个关键码给node，作为最大者
                node.keys.insertElementAt(p.getKey(rank), node.keys.size());
                p.setKey(rank, rs.keys.remove(0));//右兄弟最小关键码提升

                node.children.insertElementAt(rs.children.remove(0), node.children.size());
                if (node.getChild(node.children.size() - 1) != null) {
                    node.getChild(node.children.size() - 1).parent = node;
                }
                return;
            }
        }

        //代码执行到此，说明左，右兄弟要么为空(左右兄弟至少有一个不为空)，要么关键码数不够
        if (rank > 0) { //说明左兄弟不为空,合并到左兄弟
            Node ls = p.getChild(rank - 1);
            //首先将父节点rank-1处关键码下移到左兄弟
            ls.keys.insertElementAt(p.keys.remove(rank - 1), ls.keys.size());
            p.children.remove(rank);//下移后指向node的分支删除掉
            //node的最左侧孩子过继给ls做最右侧孩子
            ls.children.insertElementAt(node.children.remove(0), ls.children.size());
            if (ls.getChild(ls.children.size() - 1) != null) {
                ls.getChild(ls.children.size() - 1).parent = ls;
            }
            //将node剩余的关键码和孩子移动到ls
            while (!node.keys.isEmpty()) {
                ls.keys.insertElementAt(node.keys.remove(0), ls.keys.size());
                ls.children.insertElementAt(node.children.remove(0), ls.children.size());
                if (ls.getChild(ls.children.size() - 1) != null) {
                    ls.getChild(ls.children.size() - 1).parent = ls;
                }
            }
        } else {
            //与右兄弟合并
            Node rs = p.getChild(rank + 1);
            rs.keys.insertElementAt(p.keys.remove(rank), 0);
            p.children.remove(rank);

            rs.children.insertElementAt(node.children.remove(node.children.size() - 1), 0);
            if (rs.getChild(0) != null) {
                rs.getChild(0).parent = rs;
            }
            while (!node.keys.isEmpty()) {
                rs.keys.insertElementAt(node.keys.remove(node.keys.size() - 1), 0);
                if (rs.getChild(0) != null) {
                    rs.getChild(0).parent = rs;
                }
            }
        }
        //可能父节点p也下溢了
        solveUnderflow(p);
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

    private class Node implements Comparable<Node> {

        Node parent;

        Vector<E> keys = new Vector<>();

        Vector<Node> children = new Vector<>();

        Node() {
            children.insertElementAt(null, 0);
        }

        E getKey(int index) {
            return keys.get(index);
        }

        Node getChild(int index) {
            return children.get(index);
        }

        void setChild(int index, Node node) {
            children.set(index, node);
        }

        void setKey(int index, E e) {
            keys.set(index, e);
        }

        boolean isLeaf() {
            return this.children.get(0) == null;
        }

        void insertNode(int rightRank, E e, Node rightChild) {
            this.keys.insertElementAt(e, rightRank);
            this.children.insertElementAt(rightChild, rightRank + 1);
            if (rightChild != null) {
                rightChild.parent = this;
            }
        }

        Node(E e, Node lc, Node rc) {
            keys.insertElementAt(e, 0);
            children.insertElementAt(lc, 0);
            children.insertElementAt(rc, 1);
            if (lc != null) {
                lc.parent = this;
            }
            if (rc != null) {
                rc.parent = this;
            }
        }

        @Override
        public int compareTo(Node other) {
            return this.keys.get(keys.size() - 1).compareTo(other.keys.get(other.keys.size() - 1));
        }
    }
}
