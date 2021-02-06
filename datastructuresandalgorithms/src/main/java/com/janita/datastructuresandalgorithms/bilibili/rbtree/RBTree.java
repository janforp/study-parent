package com.janita.datastructuresandalgorithms.bilibili.rbtree;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * RBTree
 *
 * @author zhucj
 * @since 20210128
 */

public class RBTree<K extends Comparable<K>, V> {

    private static final boolean RED = true;

    private static final boolean BLACK = false;

    private RBNode<K, V> root;

    @Data
    @NoArgsConstructor
    @AllArgsConstructor
    static class RBNode<K extends Comparable<K>, V> {

        private RBNode<K, V> parent;

        private RBNode<K, V> left;

        private RBNode<K, V> right;

        private boolean color;

        private K key;

        private V value;
    }

    private RBNode<K, V> parentOf(RBNode<K, V> node) {
        if (node != null) {
            return node.parent;
        }
        return null;
    }

    private boolean isRed(RBNode<K, V> node) {
        if (node != null) {
            return node.color == RED;
        }
        return false;
    }

    private boolean isBlack(RBNode<K, V> node) {
        if (node != null) {
            return node.color == BLACK;
        }
        return false;
    }

    private void setRed(RBNode<K, V> node) {
        if (node != null) {
            node.color = RED;
        }
    }

    private void setBlack(RBNode<K, V> node) {
        if (node != null) {
            node.color = BLACK;
        }
    }

    public void inOrderPrint() {
        inOrderPrint(root);
    }

    private void inOrderPrint(RBNode<K, V> node) {
        if (node != null) {
            inOrderPrint(node.left);
            System.out.println("key: " + node.key + ", value: " + node.value);
            inOrderPrint(node.right);
        }
    }

    /**
     * 左旋
     * ----p                     p
     * ----|                     |
     * ----x          ----->     y
     * --/  \                   /  \
     * -lx   y                 x    ry
     * -----/  \             /  \
     * ----ly  ry          lx   ly
     *
     * 1.将x的右节点指向y的左子节点ly,将y的左子节点的父节点更新为x
     * 2.将x的父节点(不为空时)，更新为y的父节点为x的父节点，并将x的父节点指定子树（当前x的子树位置）指定为y
     * 3.将x的父节点更新为y，将y的左子节点更新为x
     */
    private void leftRotate(RBNode<K, V> x) {
        if (x == null) {
            return;
        }
        RBNode<K, V> y = x.right;
        x.right = y.left;
        if (y.left != null) {
            root.left.parent = x;
        }

        if (x.parent != null) {
            y.parent = x.parent;
            if (x == x.parent.left) {
                x.parent.left = y;
            } else {
                x.parent.right = y;
            }
        } else {
            this.root = y;
        }
        x.parent = y;
        y.left = x;
    }

    /**
     * 右旋
     */
    private void rightRotate(RBNode<K, V> x) {
        if (x == null) {
            return;
        }
        RBNode<K, V> y = x.right;
        x.right = y.left;
        if (y.left != null) {
            root.left.parent = x;
        }

        if (x.parent != null) {
            y.parent = x.parent;
            if (x == x.parent.left) {
                x.parent.left = y;
            } else {
                x.parent.right = y;
            }
        } else {
            this.root = y;
        }
        x.parent = y;
        y.left = x;
    }

}
