package com.janita.datastructuresandalgorithms.book3.chapter4_tree;

import lombok.AllArgsConstructor;
import lombok.Getter;

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

    @AllArgsConstructor
    @Getter
    class BinaryNode<T> {

        protected T element;

        protected BinaryNode<T> left;

        protected BinaryNode<T> right;

        public BinaryNode(T element) {
            this(element, null, null);
        }

        @Override
        public String toString() {
            return "{" +
                    "element=" + element +
                    '}';
        }
    }

}
