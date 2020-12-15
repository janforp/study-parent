package com.janita.datastructuresandalgorithms.book3.chapter4_tree;

import org.junit.Assert;
import org.junit.Test;

import java.util.NoSuchElementException;

/**
 * SplayTreeM
 *
 * @author zhucj
 * @since 20201224
 */
public class SplayTreeM<T extends Comparable<? super T>> {

    private SplayTreeNode root;

    private int size;

    public SplayTreeM() {
        root = null;
        size = 0;
    }

    public boolean insert(T x) {
        if (x == null) {
            throw new NullPointerException("不接受空数据");
        }
        SplayTreeNode newNode = new SplayTreeNode(x);
        size++;
        if (root == null) {
            root = newNode;
            return true;
        }
        SplayTreeNode node = find(root, x);
        if (node == null) {
            root = newNode;
            return true;
        }
        if (node.element.equals(x)) {
            throw new RuntimeException("不能添加重复元素");
        }
        SplayTreeNode left = node.left;
        SplayTreeNode right = node.right;
        newNode.left = node;
        newNode.right = right;
        node.parent = newNode;
        if (right != null) {
            right.parent = newNode;
        }
        root = newNode;
        return true;
    }

    public T find(T x) {
        SplayTreeNode node = find(root, x);
        if (node == null) {
            throw new NoSuchElementException();
        }
        return node.element;
    }

    private SplayTreeNode find(SplayTreeNode node, T x) {
        if (x == null) {
            throw new NullPointerException("不接受空数据");
        }
        if (size == 0) {
            return null;
        }
        if (node == null) {
            return null;
        }
        SplayTreeNode result = node;
        while (x.compareTo(result.element) < 0) {
            if (result.left != null) {
                result = result.left;
            } else {
                break;
            }
        }
        while (x.compareTo(result.element) > 0) {
            if (result.right != null) {
                result = result.right;
            } else {
                break;
            }
        }
        splay(result);
        return result;
    }

    private SplayTreeNode splay(SplayTreeNode v) {
        if (v == null) {
            return null;
        }
        if (v == root) {
            return v;
        }
        SplayTreeNode p;//父亲
        SplayTreeNode g;//爷爷
        while ((p = v.parent) != null && (g = v.parent.parent) != null) {
            SplayTreeNode gg = g.parent;//每轮之后，v都将以原曾祖父为父
            if (isLeftChild(v)) {
                if (isLeftChild(p)) {
                    //zig-zig
                    attachAsLeftChild(g, p.right);
                    attachAsLeftChild(p, v.right);
                    attachAsRightChild(p, g);
                    attachAsRightChild(v, p);
                } else {
                    //zig-zag
                    attachAsRightChild(g, p.left);
                    attachAsRightChild(p, v.left);
                    attachAsLeftChild(v, p);
                    attachAsLeftChild(p, g);
                }
            } else if (isRightChild(v)) {
                if (isRightChild(p)) {
                    //zag-zag
                    attachAsRightChild(g, p.left);
                    attachAsRightChild(p, v.left);
                    attachAsLeftChild(p, g);
                    attachAsLeftChild(v, p);
                } else {
                    //zag-zig
                    attachAsRightChild(p, v.left);
                    attachAsLeftChild(g, v.right);
                    attachAsLeftChild(v, p);
                    attachAsRightChild(v, g);
                }
            }
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
        if (p != null) {
            if (isLeftChild(v)) {
                attachAsRightChild(p, v.right);
                attachAsRightChild(v, p);
            } else {
                attachAsRightChild(p, v.left);
                attachAsLeftChild(v, p);
            }
            v.parent = null;
        }
        return v;
    }

    private void attachAsRightChild(SplayTreeNode p, SplayTreeNode rc) {
        p.right = rc;
        if (rc != null) {
            rc.parent = p;
        }
    }

    private void attachAsLeftChild(SplayTreeNode p, SplayTreeNode lc) {
        p.left = lc;
        if (lc != null) {
            lc.parent = p;
        }
    }

    private boolean isRightChild(SplayTreeNode v) {
        if (v == null) {
            return false;
        }
        SplayTreeNode parent = v.parent;
        if (parent == null) {
            return false;
        }
        return parent.right == v;
    }

    private boolean isLeftChild(SplayTreeNode v) {
        if (v == null) {
            return false;
        }
        SplayTreeNode parent = v.parent;
        if (parent == null) {
            return false;
        }
        return parent.left == v;
    }

    private boolean contains(T x) {
        SplayTreeNode node = find(root, x);
        if (node == null) {
            return false;
        }
        return node.element.equals(x);
    }

    private class SplayTreeNode {

        private T element;

        private SplayTreeNode parent;

        private SplayTreeNode left;

        private SplayTreeNode right;

        SplayTreeNode(T element) {
            this.element = element;
        }

        @Override
        public String toString() {
            return "SplayTreeNode{" +
                    "element=" + element +
                    '}';
        }
    }

    @Test
    public void test() {
        SplayTreeM<Integer> treeM = new SplayTreeM<>();
        treeM.insert(1);
        treeM.insert(2);
        treeM.insert(3);
        treeM.insert(4);
        treeM.insert(14);
        treeM.insert(5);
        treeM.insert(6);
        treeM.insert(9);
        treeM.insert(7);
        treeM.insert(8);
        treeM.insert(11);
        treeM.insert(10);
        treeM.insert(12);
        treeM.insert(13);

        Integer integer = treeM.find(1);
        Assert.assertNotNull(integer);
    }
}