//package com.janita.datastructuresandalgorithms.book3.chapter4_tree.redblack;
//
//import java.util.NoSuchElementException;
//
///**
// * RedBlack
// *
// * @author zhucj
// * @since 20201224
// */
//public class RedBlack<T extends Comparable<? super T>> {
//
//    private RedBlackNode root;
//
//    private RedBlackNode search(RedBlackNode node, T e) {
//        if (root == null) {
//            throw new NoSuchElementException();
//        }
//        if (e.compareTo(node.element) == 0) {
//            return node;
//        } else if (e.compareTo(node.element) < 0) {
//            return search(node.left, e);
//        } else {
//            return search(node.right, e);
//        }
//    }
//
//    private RedBlackNode insert(T e) {
//
//    }
//
//    public boolean remove(T e) {
//
//    }
//
//    private void solveDoubleRed(RedBlackNode x) {
//
//    }
//
//    private void solveDoubleBlack(RedBlackNode x) {
//
//    }
//
//    private int updateHeight(RedBlackNode x) {
//
//    }
//
//    private class RedBlackNode {
//
//        private T element;
//
//        private boolean red;
//
//        private RedBlackNode parent;
//
//        private RedBlackNode left;
//
//        private RedBlackNode right;
//    }
//}
