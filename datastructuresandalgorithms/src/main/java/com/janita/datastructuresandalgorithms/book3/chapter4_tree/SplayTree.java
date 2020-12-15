package com.janita.datastructuresandalgorithms.book3.chapter4_tree;

import lombok.AllArgsConstructor;

/**
 * SplayTreeM
 *
 * @author zhucj
 * @since 20201224
 */
public class SplayTree<T extends Comparable<? super T>> {

    private SplayTreeNode root;

    private int size;

    public SplayTree() {
        root = null;
        size = 0;
    }

    private SplayTreeNode search(T e) {
        SplayTreeNode p = searchIn(root, e);
        root = splay(p);
        return root;
    }

    public boolean contains(T e) {
        SplayTreeNode node = search(e);
        if (node == null) {
            return false;
        }
        return node.element.equals(e);
    }

    public boolean remove(T e) {
        if (root == null) {
            return false;
        }
        if (!e.equals(search(e).element)) {
            return false;
        }
        SplayTreeNode w = root;//search之后待删除节点已经提升至树根
        if (!hasLChild(root)) {//无左子树
            root = root.right;
            if (root != null) {
                root.parent = null;
            }
        } else if (!hasRChild(root)) {//无右子树
            root = root.left;
            if (root != null) {
                root.parent = null;
            }
        } else {//左右子树同时存在
            SplayTreeNode lTree = root.left;
            lTree.parent = null;
            root.left = null;//暂时切下左子树
            root = root.right;
            root.parent = null;//保留右子树
            //TODO 此处需要验证下
            search(w.element);//必然失败的查找，导致右子树中的最小元素被提升到root位置，左子树为空
            root.left = lTree;
            lTree.parent = root;
        }
        //release(w.element);
        size--;
        //release(w);
        return true;
    }

    private boolean hasRChild(SplayTreeNode node) {
        if (node == null) {
            return false;
        }
        return node.right != null;
    }

    private boolean hasLChild(SplayTreeNode node) {
        if (node == null) {
            return false;
        }
        return node.left != null;
    }

    /**
     * 试图找到e，如果e不存在，则找到接近e的节点
     *
     * @param v
     * @param e
     * @return
     */
    private SplayTreeNode searchIn(SplayTreeNode v, T e) {
        if (v == null || e.equals(v.element)) {
            return v;
        }
        SplayTreeNode result = v;
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

    public T insert(T e) {
        if (root == null) {
            size++;
            root = new SplayTreeNode(e);
            return e;
        }
        SplayTreeNode search = search(e);
        if (search != null && search.element.equals(e)) {
            return e;
        }
        size++;
        SplayTreeNode t = root;
        SplayTreeNode newNode = new SplayTreeNode(e);
        SplayTreeNode left = t.left;
        SplayTreeNode right = t.right;
        if (e.compareTo(t.element) > 0) {
            newNode.right = right;
            if (right != null) {
                right.parent = newNode;
            }
            newNode.left = t;
            t.parent = newNode;
            root = newNode;
        } else {
            newNode.left = left;
            if (left != null) {
                left.parent = newNode;
            }
            t.left = newNode;
            newNode.parent = t;
        }
        return e;
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
        while ((p = v.parent) != null && (g = v.parent.parent) != null) {//只要父亲，祖父同时存在，我们就进行一次双层伸展
            SplayTreeNode gg = g.parent;//每轮之后，v都将以原曾祖父为父
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
                attachAsLeftChild(p, v.right);
                attachAsRightChild(v, p);
            } else {
                attachAsRightChild(p, v.left);
                attachAsLeftChild(v, p);
            }
        }
        v.parent = null;
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

    @AllArgsConstructor
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
}