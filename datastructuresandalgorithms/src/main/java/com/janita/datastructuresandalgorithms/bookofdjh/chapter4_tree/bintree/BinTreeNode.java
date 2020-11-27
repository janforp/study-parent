package com.janita.datastructuresandalgorithms.bookofdjh.chapter4_tree.bintree;

import com.janita.datastructuresandalgorithms.bookofdjh.chapter2.QueueList;
import com.janita.datastructuresandalgorithms.bookofdjh.chapter3.DLNodeList;
import com.janita.datastructuresandalgorithms.bookofdjh.chapter3.List;
import com.janita.datastructuresandalgorithms.bookofdjh.chapter3.iterator.Iterator;
import com.janita.datastructuresandalgorithms.bookofdjh.queue.Queue;

/**
 * 基于链表节点实现二叉树节点,该类的每一个对象就是一个二叉树的节点
 *
 * @author zhucj
 * @since 20201126
 */
public class BinTreeNode<E> implements BinTreePosition<E> {

    /**
     * 该节点中存放的对
     */
    protected E element;

    /**
     * 父亲
     */
    protected BinTreePosition<E> parent;

    /**
     * 左孩子
     */
    protected BinTreePosition<E> lChild;

    /**
     * 右孩子
     */
    protected BinTreePosition<E> rChild;

    /**
     * 后代数目
     */
    protected int size;

    /**
     * 高度
     */
    protected int height;

    /**
     * 深度
     */
    protected int depth;

    public BinTreeNode() {
        this(null, null, true, null, null);
    }

    /**
     * 构造器
     *
     * @param e 节点内容
     * @param p this的父节点
     * @param asLChild 是否作为父节点的左孩子
     * @param l this的左孩子
     * @param r this的右孩子
     */
    public BinTreeNode(E e, BinTreePosition<E> p, boolean asLChild, BinTreePosition<E> l, BinTreePosition<E> r) {
        //初始化
        this.size = 1;
        this.height = 0;
        this.depth = 0;
        this.parent = null;
        this.lChild = null;
        this.rChild = null;

        //存放的对象
        this.element = e;

        //建立与父亲的关系
        if (null != p) {
            if (asLChild) {
                p.attachL(this);
            } else {
                p.attachR(this);
            }
        }

        //建立与孩子的关系
        if (null != l) {
            this.attachL(l);
        }
        if (null != r) {
            this.attachR(r);
        }
    }

    /**************************** Position接口方法 ********************************/

    @Override
    public E getElem() {
        return this.element;
    }

    @Override
    public E setElem(E e) {
        E old = this.element;
        this.element = e;
        return old;
    }

    /**************************** BinTreePosition接口方法 *************************/

    @Override
    public boolean hasParent() {
        return parent != null;
    }

    @Override
    public BinTreePosition<E> getParent() {
        return parent;
    }

    @Override
    public void setParent(BinTreePosition<E> p) {
        this.parent = p;
    }

    @Override
    public boolean isLeaf() {
        return !hasLChild() && !hasRChild();
    }

    @Override
    public boolean isLChild() {
        //若当前节点有父亲，而且是左孩子，则返回true;否则，返回false
        return hasParent() && this == getParent().getLChild();
    }

    @Override
    public boolean hasLChild() {
        return lChild != null;
    }

    @Override
    public BinTreePosition<E> getLChild() {
        return lChild;
    }

    @Override
    public void setLChild(BinTreePosition<E> c) {
        this.lChild = c;
    }

    @Override
    public boolean isRChild() {
        //若当前节点有父亲，而且是右孩子，则返回true;否则，返回false
        return hasParent() && getParent().getRChild() == this;
    }

    @Override
    public boolean hasRChild() {
        return rChild != null;
    }

    @Override
    public BinTreePosition<E> getRChild() {
        return rChild;
    }

    @Override
    public void setRChild(BinTreePosition<E> c) {
        this.rChild = c;
    }

    @Override
    public int getSize() {
        //返回当前节点后代元素的数目,自己也是自己的后代
        return size;
    }

    /**
     * TODO 在孩子发生变化后，更新当前节点及其祖先的规模
     */
    @Override
    public void updateSize() {
        //当前节点
        size = 1;
        if (hasLChild()) {
            //左子树的规模
            size = size + getLChild().getSize();
        }
        if (hasRChild()) {
            //右子树的规模
            size = size + getRChild().getSize();
        }
        if (hasParent()) {
            //递归更新各个真祖先的规模记录
            getParent().updateSize();
        }
    }

    @Override
    public int getHeight() {
        return height;
    }

    /**
     * 在孩子发生变化后，更新当前节点及其祖先的高度
     */
    @Override
    public void updateHeight() {
        //先假设没有左、右孩子
        height = 0;
        if (hasLChild()) {
            height = Math.max(height, 1 + getLChild().getHeight());
        }
        if (hasRChild()) {
            height = Math.max(height, 1 + getRChild().getHeight());
        }
        if (hasParent()) {
            //递归更新各个真祖先的高度记录
            getParent().updateHeight();
        }
    }

    @Override
    public int getDepth() {
        return depth;
    }

    @Override
    public void updateDepth() {
        //当前节点
        depth = hasParent() ? 1 + parent.getDepth() : 0;
        //沿孩子引用逐层向下，递归地更新所有后代的深度记录
        if (hasLChild()) {
            getLChild().updateDepth();
        }
        if (hasRChild()) {
            getRChild().updateDepth();
        }
    }

    @Override
    public BinTreePosition<E> getPrev() {
        //按照中序遍历的次序，找到当前节点的直接前驱

        if (hasLChild()) {
            //若左子树非空，则其中的最大者即为当前节点的直接前驱
            return findMaxDescendant(getLChild());
        }

        //至此，当前节点没有左孩子
        if (isRChild()) {
            //若当前节点是右孩子，则父亲即为其直接前驱
            return getParent();
        }

        //至此，当前节点没有左孩子，而且是左孩子
        BinTreePosition<E> v = this;
        while (v.isLChild()) {//TODO
            v = v.getParent();//沿左孩子链一直上升
        }
        //至此，v或者没有父亲，或者是父亲的右孩子
        return v.getParent();
    }

    @Override
    public BinTreePosition<E> getSucc() {
        //按照中序遍历的次序，找到当前节点的直接后继

        if (hasRChild()) {
            //若右子树非空，则其中的最小者即为当前节点的直接后继
            return findMinDescendant(getRChild());
        }

        //至此，当前节点没有右孩子
        if (isLChild()) {
            //若当前节点是左孩子，则父亲即为其直接后继
            return getParent();
        }

        //至此，当前节点没有右孩子，而且是右孩子
        BinTreePosition<E> v = this;//从当前节点出发
        while (v.isRChild()) {//TODO
            v = v.getParent();//沿右孩子链一直上升
        }
        //至此，v或者没有父亲，或者是父亲的左孩子
        return v.getParent();
    }

    @Override
    public BinTreePosition<E> secede() {
        //断绝当前节点与其父亲的父子关系,并返回当前节点
        if (parent == null) {
            return this;
        }
        //至此，当前节点是有父节点的
        if (isLChild()) {
            parent.setLChild(null);//切断父亲指向当前节点的引用
        } else {
            parent.setRChild(null);
        }

        parent.updateSize();//更新当前节点及其祖先的规模
        parent.updateHeight();//更新当前节点及其祖先的高度

        parent = null;//切断当前节点指向原父亲的引用
        updateDepth();//更新节点及其后代节点的深度
        return this;
    }

    @Override
    public BinTreePosition<E> attachL(BinTreePosition<E> c) {
        //将节点c作为当前节点的左孩子
        if (hasLChild()) {
            getLChild().secede();//摘除当前节点原先的左孩子
        }

        if (c != null) {
            c.secede();//c脱离原父亲
            lChild = c;
            c.setParent(this);//确立新的父子关系
            updateSize();//更新当前节点及其祖先的规模
            updateHeight();//更新当前节点及其祖先的高度

            c.updateDepth();//更新c及其后代节点的深度
        }
        return null;
    }

    @Override
    public BinTreePosition<E> attachR(BinTreePosition<E> c) {
        //将节点c作为当前节点的右孩子
        if (hasRChild()) {
            getRChild().secede();
        }

        if (c != null) {
            c.secede();
            rChild = c;
            c.setParent(this);
            updateSize();
            updateHeight();

            c.updateDepth();
        }
        return this;
    }

    @Override
    public Iterator<BinTreePosition<E>> elementsPreorder() {
        List<BinTreePosition<E>> list = new DLNodeList<>();
        preorder(list, this);
        return list.elements();
    }

    @Override
    public Iterator<BinTreePosition<E>> elementsInorder() {
        List<BinTreePosition<E>> list = new DLNodeList<>();
        inorder(list, this);
        return list.elements();
    }

    @Override
    public Iterator<BinTreePosition<E>> elementsPostorder() {
        List<BinTreePosition<E>> list = new DLNodeList<>();
        postorder(list, this);
        return list.elements();
    }

    @Override
    public Iterator<BinTreePosition<E>> elementsLevelOrder() {
        List<BinTreePosition<E>> list = new DLNodeList<>();
        levelOrder(list, this);
        return list.elements();
    }

    /**************************** 辅助方法 ****************************/

    private BinTreePosition<E> findMaxDescendant(BinTreePosition<E> v) {
        //在v的后代中，找出最小者
        if (null != v) {
            while (v.hasLChild()) {
                v = v.getLChild();//从v出发，沿左孩子链一直下降
            }
        }
        //至此，v或者为空，或者没有左孩子
        return v;
    }

    private BinTreePosition<E> findMinDescendant(BinTreePosition<E> v) {
        //在v的后代中，找出最大者
        if (null != v) {
            while (v.hasRChild()) {
                v = v.getRChild();//从v出发，沿右孩子链一直下降
            }
        }
        //至此，v或者为空，或者没有右孩子
        return v;
    }

    private void preorder(List<BinTreePosition<E>> list, BinTreePosition<E> v) {
        //前序遍历以v为根节的(子)树
        if (null == v) {
            return;//递归基:空树
        }
        list.insertLast(v);//访问v
        preorder(list, v.getLChild());//遍历左子树
        preorder(list, v.getRChild());//遍历右子树
    }

    private void inorder(List<BinTreePosition<E>> list, BinTreePosition<E> v) {
        //中序遍历以v为根节的(子)树
        if (null == v) {
            return;//递归基:空树
        }
        inorder(list, v.getLChild());//遍历左子树
        list.insertLast(v);//访问v
        inorder(list, v.getRChild());//遍历右子树
    }

    private void postorder(List<BinTreePosition<E>> list, BinTreePosition<E> v) {
        //后序遍历以v为根节的(子)树
        if (null == v) {
            return;//递归基:空树
        }
        postorder(list, v.getLChild());//遍历左子树
        postorder(list, v.getRChild());//遍历右子树
        list.insertLast(v);//访问v
    }

    private void levelOrder(List<BinTreePosition<E>> list, BinTreePosition<E> v) {
        //层次遍历以v为根节的(子)树
        Queue<BinTreePosition<E>> queue = new QueueList<>();//空队
        queue.enqueue(v);//根节点入队
        while (!queue.isEmpty()) {
            BinTreePosition<E> u = queue.dequeue();//出队
            list.insertLast(u);//访问v
            if (u.hasLChild()) {
                queue.enqueue(u.getLChild());
            }
            if (u.hasRChild()) {
                queue.enqueue(u.getRChild());
            }
        }
    }
}
