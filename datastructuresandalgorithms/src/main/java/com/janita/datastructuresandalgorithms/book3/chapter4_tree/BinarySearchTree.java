package com.janita.datastructuresandalgorithms.book3.chapter4_tree;

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
        return findMin(root).getElement();
    }

    @Override
    public T findMax() {
        if (isEmpty()) {
            throw new UnderFlowException();
        }
        return findMax(root).getElement();
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
        int compareResult = x.compareTo(t.getElement());
        if (compareResult < 0) {
            //如果当前查询的元素小于当前的根，则从根的左子树中继续查询
            return contains(x, t.getLeft());
        } else if (compareResult > 0) {
            //如果当前查询的元素小大于当前的根，则从根的右子树中继续查询
            return contains(x, t.getRight());
        } else {
            return true;
        }
    }

    private BinaryNode<T> findMin(BinaryNode<T> t) {
        if (t == null) {
            return null;
        } else if (t.getLeft() == null) {
            return t;
        }
        return findMin(t.getLeft());
    }

    private BinaryNode<T> findMax(BinaryNode<T> t) {
        if (t != null) {
            while (t.getRight() != null) {
                t = t.getRight();
            }
        }
        return t;
    }

    private BinaryNode<T> insert(T x, BinaryNode<T> t) {

    }

    private BinaryNode<T> remove(T x, BinaryNode<T> t) {

    }

    private void printTree(BinaryNode<T> root) {

    }
}
