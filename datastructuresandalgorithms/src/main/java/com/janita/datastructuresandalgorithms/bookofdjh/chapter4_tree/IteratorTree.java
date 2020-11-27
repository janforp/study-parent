package com.janita.datastructuresandalgorithms.bookofdjh.chapter4_tree;

import com.janita.datastructuresandalgorithms.bookofdjh.chapter2.Position;
import com.janita.datastructuresandalgorithms.bookofdjh.chapter2.QueueList;
import com.janita.datastructuresandalgorithms.bookofdjh.chapter3.List;
import com.janita.datastructuresandalgorithms.bookofdjh.chapter3.iterator.Iterator;
import com.janita.datastructuresandalgorithms.bookofdjh.queue.Queue;

import java.util.NoSuchElementException;

/**
 * 定理四.1 树的前序、后序及层次遍历，均可在 O(n)时间内完成，其中 n 为树本身的规模。
 *
 * @author zhucj
 * @since 20201126
 */
public class IteratorTree<E> implements Iterator<E> {

    /**
     * 列表
     */
    private List<Tree<E>> list;

    /**
     * 当前(下一个)元素的位置
     */
    private Position<Tree<E>> nextPosition;

    public IteratorTree() {
        list = null;
    }

    /**
     * 前序遍历
     */
    public void elementsPreorderIterator(Tree<E> tree) {
        if (tree == null) {
            //递归基
            return;
        }
        //首先输出当前节点,放入队列（FIFO）
        list.insertLast(tree);
        TreeLinkedList<E> subtree = tree.getFirstChild();
        while (subtree != null) {
            this.elementsPreorderIterator(subtree);
            subtree = subtree.getNextSibling();
        }
    }

    /**
     * 后序遍历
     */
    public void elementsPostorderIterator(Tree<E> tree) {
        if (tree == null) {
            //递归基
            return;
        }
        //从当前节点的长子开始
        TreeLinkedList<E> subTree = tree.getFirstChild();
        while (subTree != null) {//依次对当前节点的各个孩子
            this.elementsPostorderIterator(subTree);//做后序遍历
            subTree = subTree.getNextSibling();
        }
        list.insertLast(tree);//当所有后代都访问过后，最后才访问当前节点
    }

    /**
     * 层次遍历
     */
    public void levelTraversalIterator(Tree<E> tree) {
        if (tree == null) {
            return;
        }
        Queue<Tree<E>> queue = new QueueList<>();//空队
        queue.enqueue(tree);//根节点入队
        while (!queue.isEmpty()) {
            //在队列重新变空之前
            Tree<E> toIteratorTree = queue.dequeue();//取出队列首节点
            list.insertLast(toIteratorTree);//将新出队的节点接入迭代器中
            TreeLinkedList<E> subTree = toIteratorTree.getFirstChild();//从tree的第一个孩子起
            while (subTree != null) {//依次找出所有孩子，并
                queue.enqueue(subTree);//将其加至队列中
                subTree = subTree.getNextSibling();
            }
        }
    }

    @Override
    public boolean haxNext() {
        return nextPosition != null;
    }

    @Override
    public E getNext() {
        if (!haxNext()) {
            throw new NoSuchElementException("No next position");
        }
        Position<Tree<E>> currentPosition = nextPosition;
        if (currentPosition == list.last()) {//若已到达尾元素，则
            nextPosition = null;//不再有下一元素
        } else {//否则
            nextPosition = list.getNext(currentPosition);//转向下一元素
        }
        return currentPosition.getElem().getElem();
    }
}