package com.janita.datastructuresandalgorithms.bookofdjh.chapter4_tree.complbintree;

import com.janita.datastructuresandalgorithms.bookofdjh.chapter4_tree.bintree.BinTree;
import com.janita.datastructuresandalgorithms.bookofdjh.chapter4_tree.bintree.BinTreePosition;

/**
 * ComplBinTree
 * 完全二叉树：
 * 只要给定规模 n，完全二叉树的结构就已完全确定。因此我们可以从 0 开始到 n-1， 按照层次遍历的次序对各节点进行编号，并按照这一编号将各节点(的引用)组织为一个向量。由 于各节点的编号是连续的，故总共只需 O(n)的空间，就空间效率而言，这几乎没有任何浪费
 *
 * * 定理四.2 在完全二叉树中，
 * * 1.若节点 v 有左孩子，则 i(lchild(v)) = 2×i(v) + 1;
 * * 2.若节点 v 有右孩子，则 i(rchild(v)) = 2×i(v) + 2;
 * * 3.若节点 v 有父节点，则 i(parent(v)) = ⎣(i(v) - 1)/2⎦ = ⎡(i(v)/2⎤ - 1。
 *
 * 代码四.8 完全二叉树接口
 *
 * 可以看出，在BinTree接口(代码四.4)的基础上，这里增加了addLast()和delLast()两个操作。 借助这两个操作，我们可以在完全二叉树中插入或删除末节点。
 * 这里所谓的“末 节点”，是指完全二叉树的层次遍历序列中的末节点。
 *
 * 观察结论四.15 若基于可扩充向量来实现完全二叉树，则就分摊复杂度而言，每次 addLast()和delLast()操作都可以在 O(1)时间内完成。
 *
 * @author zhucj
 * @since 20201126
 */
public interface ComplBinTree<E> extends BinTree<E> {

    /**
     * 生成并返回一个存放e的外部节点，该节点成为新的末节点
     *
     * @param e
     * @return
     */
    BinTreePosition<E> addLast(E e);

    /**
     * 删除末节点，并返回其中存放的内容
     *
     * @return 其中存放的内容
     */
    E delLast();

    /**
     * 返回按照层次遍历编号为i的节点的位置，0 <= i < size()
     *
     * @param i
     * @return
     */
    BinTreePosition<E> posOfNode(int i);
}