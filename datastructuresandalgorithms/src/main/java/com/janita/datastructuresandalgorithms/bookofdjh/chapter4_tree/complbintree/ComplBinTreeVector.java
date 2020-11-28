package com.janita.datastructuresandalgorithms.bookofdjh.chapter4_tree.complbintree;

import com.janita.datastructuresandalgorithms.bookofdjh.chapter3.ExtArrayVector;
import com.janita.datastructuresandalgorithms.bookofdjh.chapter3.Sequence;
import com.janita.datastructuresandalgorithms.bookofdjh.chapter3.Vector;
import com.janita.datastructuresandalgorithms.bookofdjh.chapter4_tree.bintree.BinTreePosition;
import com.janita.datastructuresandalgorithms.bookofdjh.chapter4_tree.bintree.LinkedListBinTree;

/**
 * 代码四.10 基于向量实现的完全二叉树
 * TODO
 *
 * @author zhucj
 * @since 20201126
 */
public class ComplBinTreeVector<E> extends LinkedListBinTree<E> implements ComplBinTree<E> {

    private Vector<BinTreePosition<E>> t;

    public ComplBinTreeVector() {
        this.t = new ExtArrayVector<>();
        this.root = null;
    }

    public ComplBinTreeVector(Sequence<E> s) {
        this();
        if (s != null) {
            while (!s.isEmpty()) {
                addLast(s.removeFirst());
            }
        }
    }

    @Override
    public BinTreePosition<E> getRoot() {
        return t.isEmpty() ? null : posOfNode(0);
    }

    @Override
    public boolean isEmpty() {
        return t.isEmpty();
    }

    @Override
    public int getSize() {
        return t.getSize();
    }

    @Override
    public int getHeight() {
        return isEmpty() ? -1 : getRoot().getHeight();
    }

    @Override
    public BinTreePosition<E> addLast(E e) {
        BinTreePosition<E> node = new ComplBinTreeNodeRank<>(t, e);
        root = t.getAtRank(0);
        return node;
    }

    @Override
    public E delLast() {
        if (isEmpty()) {
            return null;//若树(堆)已空，无法删除
        }
        if (1 == getSize()) {
            root = null;//若删除最后一个节点，则树空
        }
        return t.removeAtRank(t.getSize() - 1).getElem();
    }

    @Override
    public BinTreePosition<E> posOfNode(int i) {
        return t.getAtRank(i);
    }
}
