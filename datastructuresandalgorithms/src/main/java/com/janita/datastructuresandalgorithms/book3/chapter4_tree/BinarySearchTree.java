package com.janita.datastructuresandalgorithms.book3.chapter4_tree;

import org.junit.Test;

/**
 * 二叉搜索树/二叉查找树
 *
 * @author zhucj
 * @since 20201224
 */
public class BinarySearchTree<T extends Comparable<? super T>> implements IBinarySearchTree<T> {

    private BinaryNode<T> root;

    public BinarySearchTree() {
        root = null;
    }

    @Override
    public boolean isEmpty() {
        return root == null;
    }

    @Override
    public void makeEmpty() {
        root = null;
    }

    @Override
    public boolean contains(T x) {
        return contains(x, root);
    }

    @Override
    public T findMin() {
        if (isEmpty()) {
            throw new UnderFlowException();
        }
        return findMin(root).element;
    }

    @Override
    public T findMax() {
        if (isEmpty()) {
            throw new UnderFlowException();
        }
        return findMax(root).element;
    }

    @Override
    public void insert(T x) {
        root = insert(x, root);
    }

    @Override
    public void remove(T x) {
        root = remove(x, root);
    }

    @Override
    public void printTree() {
        printTree(root);
    }

    private boolean contains(T x, BinaryNode<T> t) {
        if (t == null) {
            return false;
        }
        int compareResult = x.compareTo(t.element);
        if (compareResult < 0) {
            //如果当前查询的元素小于当前的根，则从根的左子树中继续查询
            return contains(x, t.left);
        } else if (compareResult > 0) {
            //如果当前查询的元素小大于当前的根，则从根的右子树中继续查询
            return contains(x, t.right);
        } else {
            return true;
        }
    }

    private BinaryNode<T> findMin(BinaryNode<T> t) {
        if (t == null) {
            return null;
        } else if (t.left == null) {
            return t;
        }
        return findMin(t.left);
    }

    private BinaryNode<T> findMax(BinaryNode<T> t) {
        if (t != null) {
            while (t.right != null) {
                t = t.right;
            }
        }
        return t;
    }

    private BinaryNode<T> insert(T x, BinaryNode<T> t) {
        if (t == null) {
            return new BinaryNode<>(x, null, null);
        }
        int compareResult = x.compareTo(t.getElement());
        if (compareResult < 0) {//x小于当前根，则插入到根的左子树
            t.left = insert(x, t.left);//递归的把x插入到适当的子树中
        } else if (compareResult > 0) {//x大于当前根，则插入到根的右子树
            t.right = insert(x, t.right);//递归的把x插入到适当的子树中
        }
        return t;
    }

    private BinaryNode<T> remove(T x, BinaryNode<T> t) {
        if (t == null) {
            return null;
        }
        int compareResult = x.compareTo(t.element);
        if (compareResult < 0) {
            t.left = remove(x, t.left);
        } else if (compareResult > 0) {
            t.right = remove(x, t.right);
        } else if (t.left != null && t.right != null) {//2个孩子
            t.element = findMin(t.right).element;
            t.right = remove(t.element, t.right);
        } else {//1个孩子
            t = (t.left != null) ? t.left : t.right;
        }
        return t;
    }

    private void printTree(BinaryNode<T> root) {
    }

    @Test
    public void test() {
        IBinarySearchTree<Integer> tree = build();
        tree.insert(5);
        tree.insert(7);
    }

    private IBinarySearchTree<Integer> build() {
        IBinarySearchTree<Integer> tree = new BinarySearchTree<>();
        tree.insert(6);
        tree.insert(2);
        tree.insert(8);
        tree.insert(1);
        tree.insert(4);
        tree.insert(3);
        return tree;
    }
}
