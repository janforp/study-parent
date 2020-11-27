package com.janita.datastructuresandalgorithms.bookofdjh.chapter4_tree;

/**
 * 基于链表实现树
 *
 * @author zhucj
 * @since 20201126
 */
public class TreeLinkedList<E> implements Tree<E> {

    /**
     * 树根节点
     */
    private E element;

    /**
     * 父亲
     */
    private TreeLinkedList<E> parent;

    /**
     * 长子
     */
    private TreeLinkedList<E> firstChild;

    /**
     * 长子最大的弟弟
     */
    private TreeLinkedList<E> nextSibling;

    public TreeLinkedList(E element, TreeLinkedList<E> parent, TreeLinkedList<E> firstChild, TreeLinkedList<E> nextSibling) {
        this.element = element;
        this.parent = parent;
        this.firstChild = firstChild;
        this.nextSibling = nextSibling;
    }

    public TreeLinkedList() {
        this(null, null, null, null);
    }

    @Override
    public E getElem() {
        return element;
    }

    @Override
    public E setElem(E obj) {
        E old = this.element;
        this.element = obj;
        return old;
    }

    @Override
    public TreeLinkedList<E> getParent() {
        return parent;
    }

    @Override
    public TreeLinkedList<E> getFirstChild() {
        return firstChild;
    }

    @Override
    public TreeLinkedList<E> getNextSibling() {
        return nextSibling;
    }

    /**
     * 返回当前节点后代元素的数目，即以当前节点为根的子树的规模
     *
     * --- 观察结论四.12 一棵树的规模，等于根节点下所有子树规模之和再加一，也等于根节点的后代总数。
     * 基于这一事实，该算法首先通过 firstChild 引用找出根节点的长子，并沿着 nextSibling 引用顺次找到其余的孩子，递归地统计出各子树的规模。
     * 最后，只要将所有子树的规模累加起来，再计入根节点本身，就得到了整棵树的规模。当遇到没有任何孩子的节点(即原树的叶子)时，递归终止。
     * 如果不计入递归调用，该算法在每个节点上只需花费常数时间，
     * 因此若树的规模为 n，则总的 时间复杂度为 O(n)。
     * 读者也许注意到了，实际上，这一算法也能够在 O(n)时间内统计出树中所有子树的规模。
     *
     * @return 返回当前节点后代元素的数目，即以当前节点为根的子树的规模
     */
    @Override
    public int getSize() {
        //当前节点也是自己的后代
        int size = 1;
        //从长子开始
        TreeLinkedList<E> subtree = firstChild;
        while (subtree != null) {//依次循环(递归)当遇到没有任何孩子的节点(即原树的叶子)时，递归终止。
            size = size + subtree.getSize();//累加
            subtree = subtree.getNextSibling();//所有孩子的后代数目
        }
        return size;
    }

    /**
     * 返回当前节点的高度
     * 推论四.5 ：若 u 是 v 的孩子，则 height(v) ≥ height(u) + 1; height(v) = 1 + max height(u) 。u是v的孩子
     *
     * 因此，算法 getHeight(v)也是首先通过 firstChild 引用找出根节点的长子，并沿着 nextSibling 引用顺次找到其余的孩子，递归地计算出各子树的高度。
     * 最后，只要找出所有子树的最大高度， 计入根节点本身，就得到了根节点的高度(即树高)。请读者自行分析递归终止的条件。
     * 仿照对 getSize()方法的分析可知，getHeight(v)算法的运行时间也是 O(n)，其中 n 为 v 的后代 总数(即树的规模)。实际上，花费这样多的时间，同样可以统计出所有子树的高度。
     *
     * @return 返回当前节点的高度
     */
    @Override
    public int getHeight() {
        int height = -1;
        TreeLinkedList<E> subtree = firstChild;
        while (subtree != null) {//终止条件也是递归到叶子节点，其中每一个叶子节点的高度为1
            height = Math.max(height, subtree.getHeight());
            subtree = subtree.getNextSibling();
        }
        return height + 1;
    }

    /**
     * 推论四.6 若 s 是 p 的孩子，则 depth(s) = depth(p) + 1。
     * 根节点的高度为0，越往下高度越大
     *
     * 具体地，算法 getDepth(v)将从 v 的父亲开始，沿着 parent 指针不断上移，直到深度为 0 的树根。
     * 在这个过程中所遇到的每个节点，都是 v 的真祖先;反之，在这一过程中，v 的每一真祖先迟 早都会被找到。
     * 因此，根据总共上移的层数，就可以得到 v 在整棵树中的深度。
     * 由于该算法只需访问 v 的所有真祖先，而且在每个节点只需 O(1)时间，故其复杂度为 O(depth(v))。
     * 在最坏情况下，由 n 个节点组成的树的高度可以达到 n。此时，若针对最底层的叶子 调用 getDepth()算法，将需要 O(n)的时间。
     *
     * @return
     */
    @Override
    public int getDepth() {
        int depth = 0;
        //从父亲开始
        TreeLinkedList<E> p = parent;
        while (p != null) {//根节点是没有父节点的
            depth++;
            //访问各个真祖先
            p = p.parent;
        }
        //真祖先的数目，即为当前节点的深度
        return depth;
    }
}
