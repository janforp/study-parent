package com.janita.datastructuresandalgorithms.book3.chapter4_tree;

import org.junit.Test;

/**
 * 二叉搜索树/二叉查找树
 *
 * @author zhucj
 * @since 20201224
 */
public class BinarySearchTree<T extends Comparable<? super T>>
        implements IBinarySearchTree<T> {

    /**
     * 根节点
     */
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

    /**
     * 在树{@code t} t 中插入值为 {@code x} x的节点，并返回原节点{@code t} t
     *
     * @param x 要插入的值
     * @param t 输节点
     * @return
     */
    private BinaryNode<T> insert(T x, BinaryNode<T> t) {
        if (t == null) {
            //最后插入的时候肯定是这一行代码，因为BST插入只能插入到叶子节点，
            // 而他的上一栈为 t.left = 或者 t.right = 他们等号后面
            // 其实就是该 new BinaryNode<>(x, null, null)
            //因为插入的是叶子，所以 new BinaryNode<>(x, null, null) 左右孩子都是null
            return new BinaryNode<>(x, null, null);
        }
        int compareResult = x.compareTo(t.element);
        if (compareResult < 0) {//x小于当前根，则插入到根的左子树
            //递归的把x插入到适当的子树中，其中当t.left=null的时候就会真正插入
            t.left = insert(x, t.left);
        } else if (compareResult > 0) {//x大于当前根，则插入到根的右子树
            //递归的把x插入到适当的子树中，其中当t.right=null的时候就会真正插入
            t.right = insert(x, t.right);
        }
        return t;
    }

    /**
     * 在树{@code t} t 中删除值为 {@code x} x的节点，并返回原节点{@code t} t
     *
     * @param x 要删除的值
     * @param t 输节点
     * @return
     */
    private BinaryNode<T> remove(T x, BinaryNode<T> t) {
        if (t == null) {
            return null;
        }
        int compareResult = x.compareTo(t.element);
        if (compareResult < 0) {//往左查询
            t.left = remove(x, t.left);
        } else if (compareResult > 0) {//往右查询
            t.right = remove(x, t.right);
        } else if (t.left != null && t.right != null) {//要删除的节点有2个孩子
            //在要删除节点的右子树中找到最小元素，直接使用该最小元素代替该元素，
            // 因为右边的元素肯定都比该元素大，选一个最小的代替他也不违反二叉搜索树的定义
            //然后再把右子树中的最小值元素删除即可
            t.element = findMin(t.right).element;
            t.right = remove(t.element, t.right);
        } else {//要删除的节点有1个孩子，就使用他的孩子代替他即可
            t = (t.left != null) ? t.left : t.right;
        }
        return t;
    }

    private void printTree(BinaryNode<T> root) {
    }

    @Test
    public void test() {
        IBinarySearchTree<Integer> tree = build();
        tree.remove(2);
        System.out.println(tree);
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
