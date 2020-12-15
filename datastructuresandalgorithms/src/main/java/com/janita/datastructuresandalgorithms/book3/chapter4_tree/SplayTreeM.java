package com.janita.datastructuresandalgorithms.book3.chapter4_tree;

import lombok.AllArgsConstructor;
import org.junit.Assert;
import org.junit.Test;

/**
 * SplayTreeM
 *
 * @author zhucj
 * @since 20201224
 */
public class SplayTreeM<T extends Comparable<? super T>> {

    private SplayTreeNode<T> root;

    private int size;

    public SplayTreeM() {
        root = null;
        size = 0;
    }

    private SplayTreeNode<T> search(T e) {
        SplayTreeNode<T> p = searchIn(root, e);
        root = splay(p);
        return root;
    }

    /**
     * 试图找到e，如果e不存在，则找到接近e的节点
     *
     * @param v
     * @param e
     * @return
     */
    private SplayTreeNode<T> searchIn(SplayTreeNode<T> v, T e) {
        if (v == null || e.equals(v.element)) {
            return v;
        }
        SplayTreeNode<T> result = v;
        while (e.compareTo(result.element) > 0) {
            if (result.right != null) {
                result = result.right;
            } else {
                break;
            }
        }
        while (e.compareTo(result.element) < 0) {
            if (result.left != null) {
                result = result.left;
            } else {
                break;
            }
        }
        return result;
    }

    private T insert(T e) {
        if (root == null) {
            size++;
            root = new SplayTreeNode<>(e);
            return e;
        }
        SplayTreeNode<T> search = search(e);
        if (search != null && search.element.equals(e)) {
            return e;
        }
        size++;
        @SuppressWarnings("unchecked")
        SplayTreeNode<Integer> t = (SplayTreeNode<Integer>) root;
        SplayTreeNode<T> newNode = new SplayTreeNode<>(e);
        SplayTreeNode<Integer> left = t.left;
        SplayTreeNode<Integer> right = t.right;

    }

    private SplayTreeNode<T> splay(SplayTreeNode<T> v) {
        if (v == null) {
            return null;
        }
        if (v == root) {
            return v;
        }
        SplayTreeNode<T> p;//父亲
        SplayTreeNode<T> g;//爷爷
        while ((p = v.parent) != null && (g = v.parent.parent) != null) {//只要父亲，祖父同时存在，我们就进行一次双层伸展
            SplayTreeNode<T> gg = g.parent;//每轮之后，v都将以原曾祖父为父
            if (isLeftChild(v)) {//zig类型
                if (isLeftChild(p)) {//zig-zig
                    attachAsLeftChild(g, p.right);
                    attachAsLeftChild(p, v.right);
                    attachAsRightChild(p, g);
                    attachAsRightChild(v, p);
                } else {//zig-zag
                    attachAsRightChild(g, v.left);
                    attachAsLeftChild(p, v.right);
                    attachAsLeftChild(v, g);
                    attachAsRightChild(v, p);
                }
            } else if (isRightChild(v)) {//zag类型
                if (isRightChild(p)) {//zag-zag
                    attachAsRightChild(g, p.left);
                    attachAsRightChild(p, v.left);
                    attachAsLeftChild(p, g);
                    attachAsLeftChild(v, p);
                } else {//zag-zig
                    attachAsRightChild(p, v.left);
                    attachAsLeftChild(g, v.right);
                    attachAsLeftChild(v, p);
                    attachAsRightChild(v, g);
                }
            }
            //没经过一次双重伸展，都需要把局部的新的子树，接入到原树中对应的位置
            if (gg == null) {//若无曾祖父gg，则v现为树根
                v.parent = null;
            } else {//否则,gg此后应以v为左或右孩子
                if (g == gg.left) {
                    attachAsLeftChild(gg, v);
                } else {
                    attachAsRightChild(gg, v);
                }
            }
        }
        //所有的双层伸展都完成之后，可能还需要进行一次单层调整，因为最后一次双层伸展之后，节点只有一个父亲而没有祖父的情况是存在的
        if (p != null) {
            if (isLeftChild(v)) {
                attachAsRightChild(p, v.right);
                attachAsRightChild(v, p);
            } else {
                attachAsRightChild(p, v.left);
                attachAsLeftChild(v, p);
            }
        }
        v.parent = null;
        return v;
    }

    private void attachAsRightChild(SplayTreeNode<T> p, SplayTreeNode<T> rc) {
        p.right = rc;
        if (rc != null) {
            rc.parent = p;
        }
    }

    private void attachAsLeftChild(SplayTreeNode<T> p, SplayTreeNode<T> lc) {
        p.left = lc;
        if (lc != null) {
            lc.parent = p;
        }
    }

    private boolean isRightChild(SplayTreeNode<T> v) {
        if (v == null) {
            return false;
        }
        SplayTreeNode<T> parent = v.parent;
        if (parent == null) {
            return false;
        }
        return parent.right == v;
    }

    private boolean isLeftChild(SplayTreeNode<T> v) {
        if (v == null) {
            return false;
        }
        SplayTreeNode<T> parent = v.parent;
        if (parent == null) {
            return false;
        }
        return parent.left == v;
    }

    @AllArgsConstructor
    private static class SplayTreeNode<T> {

        private T element;

        private SplayTreeNode<T> parent;

        private SplayTreeNode<T> left;

        private SplayTreeNode<T> right;

        SplayTreeNode(T element) {
            this.element = element;
        }

        @Override
        public String toString() {
            return "SplayTreeNode<T>{" +
                    "element=" + element +
                    '}';
        }
    }

    @Test
    public void test1() {

        SplayTreeNode<Integer> node7 = new SplayTreeNode<>(7);
        SplayTreeNode<Integer> node6 = new SplayTreeNode<>(6);
        SplayTreeNode<Integer> node5 = new SplayTreeNode<>(5);
        SplayTreeNode<Integer> node4 = new SplayTreeNode<>(4);
        SplayTreeNode<Integer> node3 = new SplayTreeNode<>(3);
        SplayTreeNode<Integer> node2 = new SplayTreeNode<>(2);
        SplayTreeNode<Integer> node1 = new SplayTreeNode<>(1);

        node7.left = node6;
        node6.parent = node7;

        node6.left = node5;
        node5.parent = node6;

        node5.left = node4;
        node4.parent = node5;

        node4.left = node3;
        node3.parent = node4;

        node3.left = node2;
        node2.parent = node3;

        node2.left = node1;
        node1.parent = node2;

        Assert.assertNotNull(node7);
    }

    @Test
    public void test2() {
        SplayTreeNode<Integer> node15 = new SplayTreeNode<>(15);
        SplayTreeNode<Integer> node14 = new SplayTreeNode<>(14);
        SplayTreeNode<Integer> node13 = new SplayTreeNode<>(13);
        SplayTreeNode<Integer> node12 = new SplayTreeNode<>(12);
        SplayTreeNode<Integer> node11 = new SplayTreeNode<>(11);
        SplayTreeNode<Integer> node10 = new SplayTreeNode<>(10);
        SplayTreeNode<Integer> node9 = new SplayTreeNode<>(9);
        SplayTreeNode<Integer> node8 = new SplayTreeNode<>(8);

        SplayTreeNode<Integer> node7 = new SplayTreeNode<>(7);
        SplayTreeNode<Integer> node6 = new SplayTreeNode<>(6);
        SplayTreeNode<Integer> node5 = new SplayTreeNode<>(5);
        SplayTreeNode<Integer> node4 = new SplayTreeNode<>(4);
        SplayTreeNode<Integer> node3 = new SplayTreeNode<>(3);
        SplayTreeNode<Integer> node2 = new SplayTreeNode<>(2);
        SplayTreeNode<Integer> node1 = new SplayTreeNode<>(1);

        node15.left = node14;
        node14.parent = node15;

        node14.left = node13;
        node13.parent = node14;

        node13.left = node12;
        node12.parent = node13;

        node12.left = node11;
        node11.parent = node12;

        node11.left = node10;
        node10.parent = node11;

        node10.left = node9;
        node9.parent = node10;

        node9.left = node8;
        node8.parent = node9;

        node8.left = node7;
        node7.parent = node8;

        node7.left = node6;
        node6.parent = node7;

        node6.left = node5;
        node5.parent = node6;

        node5.left = node4;
        node4.parent = node5;

        node4.left = node3;
        node3.parent = node4;

        node3.left = node2;
        node2.parent = node3;

        node2.left = node1;
        node1.parent = node2;

        Assert.assertNotNull(node15);

        SplayTreeM<Integer> splay = new SplayTreeM<>();
        splay.splay(node1);
        Assert.assertEquals(7, (int) node6.right.element);

        splay.splay(node3);

        Assert.assertEquals(1, (int) node3.left.element);
        Assert.assertEquals(14, (int) node3.right.element);

        Assert.assertEquals(6, (int) node10.left.element);
        Assert.assertEquals(13, (int) node12.right.element);

    }

    @Test
    public void test3() {
        SplayTreeNode<Integer> root = new SplayTreeNode<>(15);
        SplayTreeNode<Integer> node14 = new SplayTreeNode<>(14);
        SplayTreeNode<Integer> node13 = new SplayTreeNode<>(13);
        SplayTreeNode<Integer> node12 = new SplayTreeNode<>(12);
        SplayTreeNode<Integer> node11 = new SplayTreeNode<>(11);
        SplayTreeNode<Integer> node10 = new SplayTreeNode<>(10);
        SplayTreeNode<Integer> node9 = new SplayTreeNode<>(9);
        SplayTreeNode<Integer> node8 = new SplayTreeNode<>(8);

        SplayTreeNode<Integer> node7 = new SplayTreeNode<>(7);
        SplayTreeNode<Integer> node6 = new SplayTreeNode<>(6);
        SplayTreeNode<Integer> node5 = new SplayTreeNode<>(5);
        SplayTreeNode<Integer> node4 = new SplayTreeNode<>(4);
        SplayTreeNode<Integer> node3 = new SplayTreeNode<>(3);
        SplayTreeNode<Integer> node2 = new SplayTreeNode<>(2);
        SplayTreeNode<Integer> node1 = new SplayTreeNode<>(1);

        root.left = node14;
        node14.parent = root;

        node14.left = node13;
        node13.parent = node14;

        node13.left = node12;
        node12.parent = node13;

        node12.left = node11;
        node11.parent = node12;

        node11.left = node10;
        node10.parent = node11;

        node10.left = node9;
        node9.parent = node10;

        node9.left = node8;
        node8.parent = node9;

        node8.left = node7;
        node7.parent = node8;

        node7.left = node6;
        node6.parent = node7;

        node6.left = node5;
        node5.parent = node6;

        node5.left = node4;
        node4.parent = node5;

        node4.left = node3;
        node3.parent = node4;

        node3.left = node2;
        node2.parent = node3;

        node2.left = node1;
        node1.parent = node2;

        Assert.assertNotNull(root);

        SplayTreeM<Integer> treeM = new SplayTreeM<>();
        treeM.root = root;
        SplayTreeNode<Integer> search = treeM.search(0);
        Assert.assertNotNull(search);

        SplayTreeM<Integer> splay = new SplayTreeM<>();
        Assert.assertEquals(7, (int) node6.right.element);

        splay.splay(node3);

        Assert.assertEquals(1, (int) node3.left.element);
        Assert.assertEquals(14, (int) node3.right.element);

        Assert.assertEquals(6, (int) node10.left.element);
        Assert.assertEquals(13, (int) node12.right.element);

    }
}