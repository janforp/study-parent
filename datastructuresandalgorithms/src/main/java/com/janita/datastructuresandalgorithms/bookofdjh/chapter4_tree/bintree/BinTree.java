package com.janita.datastructuresandalgorithms.bookofdjh.chapter4_tree.bintree;

import com.janita.datastructuresandalgorithms.bookofdjh.chapter3.iterator.Iterator;

/**
 * 二叉树
 *
 * @author zhucj
 * @since 20201126
 */
public interface BinTree<E> {

    /**
     * 返回树根
     *
     * @return 返回树根
     */
    BinTreePosition<E> getRoot();

    /**
     * 判断是否树空
     *
     * @return 判断是否树空
     */
    boolean isEmpty();

    /**
     * 返回树的规模(即树根的后代数目)
     *
     * @return 返回树的规模(即树根的后代数目)
     */
    int getSize();

    /**
     * 返回树(根)的高度
     *
     * @return /返回树(根)的高度
     */
    int getHeight();

    /**
     * 前序遍历
     *
     * @return 前序遍历
     */
    Iterator<BinTreePosition<E>> elementsPreorder();

    /**
     * 中序遍历
     *
     * @return 中序遍历
     */
    Iterator<BinTreePosition<E>> elementsInorder();

    /**
     * 后序遍历
     *
     * @return 后序遍历
     */
    Iterator<BinTreePosition<E>> elementsPostorder();

    /**
     * 层次遍历
     *
     * @return 层次遍历
     */
    Iterator<BinTreePosition<E>> elementsLevelOrder();
}