package com.janita.datastructuresandalgorithms.bookofdjh.chapter4_tree.complbintree;

import com.janita.datastructuresandalgorithms.bookofdjh.chapter3.Vector;
import com.janita.datastructuresandalgorithms.bookofdjh.chapter4_tree.bintree.BinTreeNode;
import com.janita.datastructuresandalgorithms.bookofdjh.chapter4_tree.bintree.BinTreePosition;

/**
 * 基于秩实现的完全二叉树节点
 *
 * 定理四.2 在完全二叉树中，
 * 1.若节点 v 有左孩子，则 i(lchild(v)) = 2×i(v) + 1;
 * 2.若节点 v 有右孩子，则 i(rchild(v)) = 2×i(v) + 2;
 * 3.若节点 v 有父节点，则 i(parent(v)) = ⎣(i(v) - 1)/2⎦ = ⎡(i(v)/2⎤ - 1。
 *
 * @author zhucj
 * @since 20201126
 */
public class ComplBinTreeNodeRank<E> extends BinTreeNode<E> implements BinTreePosition<E> {

    /**
     * 所属的树
     */
    private Vector<BinTreePosition<E>> t;

    /**
     * 在所属树中的秩
     */
    private int rank;

    /**
     * 存放的对象
     */
    private E element;

    public ComplBinTreeNodeRank(Vector<BinTreePosition<E>> t, E obj) {
        this.element = obj;
        this.t = t;
        this.rank = t.getSize();
        t.insertAtRank(rank, this);
    }

    @Override
    public E getElem() {
        return element;
    }

    @Override
    public E setElem(E e) {
        E old = element;
        element = e;
        return old;
    }

    public boolean hasParent() {
        return rank != 0;
    }

    @Override
    public BinTreePosition<E> getParent() {
        return hasParent() ?
                //若节点 v 有父节点，则 i(parent(v)) = ⎣(i(v) - 1)/2⎦ = ⎡(i(v)/2⎤ - 1。
                t.getAtRank((rank - 1) / 2)
                : null;
    }

    @Override
    public boolean hasLChild() {
        return (1 + rank * 2) < t.getSize();
    }

    @Override
    public BinTreePosition<E> getLChild() {
        return hasLChild() ?
                //若节点 v 有左孩子，则 i(lchild(v)) = 2×i(v) + 1;
                t.getAtRank(1 + 2 * rank)
                : null;
    }

    @Override
    public boolean hasRChild() {
        //.若节点 v 有右孩子，则 i(rchild(v)) = 2×i(v) + 2;
        return (2 + rank * 2) < t.getSize();
    }

    @Override
    public BinTreePosition<E> getRChild() {
        return hasRChild() ?
                //.若节点 v 有右孩子，则 i(rchild(v)) = 2×i(v) + 2;
                t.getAtRank(2 + 2 * rank)
                : null;
    }

    @Override
    public int getSize() {
        int size = 1;
        if (hasLChild()) {
            size = size + getLChild().getSize();
        }
        if (hasRChild()) {
            size = size + getRChild().getSize();
        }
        return size;
    }

    @Override
    public int getHeight() {
        int hL = hasLChild() ? getLChild().getHeight() : -1;
        int hR = hasRChild() ? getRChild().getHeight() : -1;
        return 1 + Math.max(hL, hR);
    }

    @Override
    public int getDepth() {
        return hasParent() ?
                getParent().getDepth() + 1
                : 0;
    }
}
