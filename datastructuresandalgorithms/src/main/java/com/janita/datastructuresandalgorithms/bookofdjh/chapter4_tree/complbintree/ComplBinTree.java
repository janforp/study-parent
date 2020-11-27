package com.janita.datastructuresandalgorithms.bookofdjh.chapter4_tree.complbintree;

import com.janita.datastructuresandalgorithms.bookofdjh.chapter4_tree.bintree.BinTree;
import com.janita.datastructuresandalgorithms.bookofdjh.chapter4_tree.bintree.BinTreePosition;

/**
 * ComplBinTree
 * 完全二叉树
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