package com.janita.datastructuresandalgorithms.bookofdjh.chapter4_tree.bintree;

import com.janita.datastructuresandalgorithms.bookofdjh.chapter3.iterator.Iterator;

/**
 * BinTreeLinkedList
 *
 * @author zhucj
 * @since 20201126
 */
public class BinTreeLinkedList<E> implements BinTree<E> {

    /**
     * 根节点
     */
    private BinTreePosition<E> root;

    public BinTreeLinkedList() {
        this(null);
    }

    public BinTreeLinkedList(BinTreePosition<E> r) {
        this.root = r;
    }

    /**************************** BinaryTree接口方法 ****************************/

    @Override
    public BinTreePosition<E> getRoot() {
        return root;
    }

    @Override
    public boolean isEmpty() {
        return root == null;
    }

    @Override
    public int getSize() {
        return isEmpty() ?
                0 :
                root.getSize();
    }

    @Override
    public int getHeight() {
        return isEmpty() ?
                -1 :
                root.getHeight();
    }

    @Override
    public Iterator<BinTreePosition<E>> elementsPreorder() {
        return root.elementsPreorder();
    }

    @Override
    public Iterator<BinTreePosition<E>> elementsInorder() {
        return root.elementsInorder();
    }

    @Override
    public Iterator<BinTreePosition<E>> elementsPostorder() {
        return root.elementsPostorder();
    }

    @Override
    public Iterator<BinTreePosition<E>> elementsLevelOrder() {
        return root.elementsLevelOrder();
    }
}