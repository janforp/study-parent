package com.janita.datastructuresandalgorithms.book3.chapter4_tree;

/**
 * IBinarySearchTree
 *
 * @author zhucj
 * @since 20201224
 */
public interface IBinarySearchTree<T> {

    boolean isEmpty();

    void makeEmpty();

    boolean contains(T x);

    T findMin();

    T findMax();

    void insert(T x);

    void remove(T x);

    void printTree();
}
