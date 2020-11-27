package com.janita.datastructuresandalgorithms.bookofdjh.chapter4_tree.bintree;

import com.janita.datastructuresandalgorithms.bookofdjh.chapter2.QueueList;
import com.janita.datastructuresandalgorithms.bookofdjh.chapter3.DLNodeList;
import com.janita.datastructuresandalgorithms.bookofdjh.chapter3.List;
import com.janita.datastructuresandalgorithms.bookofdjh.chapter3.iterator.Iterator;
import com.janita.datastructuresandalgorithms.bookofdjh.queue.Queue;

/**
 * 基于链表节点实现二叉树节点,该类的每一个对象就是一个二叉树的节点
 * 代码四.6 基于链表的二叉树节点实现
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
     * 在孩子发生变化后，更新当前节点及其祖先的规模
     *
     * 若当前节点的孩子发生变化，比如原有的某个孩子被删除或者有新的孩子插入，就需要更新当前节点及其祖先的规模记录，以便后续的查询，updateSize()方法的功能正在于此。
     * 请注意，在这里， 我们允许直接对任何节点执行这一操作。
     *
     * 若节点 v 的左、右孩子分别为 lc 和 rc，则 size(v) = 1 + size(lc) + size(rc)。
     * 因此，一旦其左、右子树的规模都已确定，我们就可以在O(1)时间内得到以节点v为根的子树规模。当然，此后还需要从v出发沿parent引用逆行向上，依次更新各个祖先的规模记录
     *
     * 算法:updateSize(v)
     * 输入:二叉树中任一节点v
     * 输出:更新v的后代规模记录
     * {
     * 令size(v) = 1 + size(lc) + size(rc);//由观察结论四.13
     * 若v的父亲p存在，则调用updateSize(p)，递归地更新父亲的规模记录;//尾递归，可改写为迭代形式
     * }
     *
     * 若节点 v 的深度为 depth(v)，则总共需要修改 depth(v)+1 个节点的规模。(因为根节点的深度为0，所以 + 1)
     * 为了更新一个节点的规模记录，只需执行两次 getSize()操作并做两次加法，故 updateSize()算法的总体运行时间为 O(depth(v)+1)。
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
        //上面是计算当前节点的规模，下面是更新父节点的规模
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
     *
     * 只需读出左、右孩子的高度，取二者中的大者，再计入当前节点本身，就 得到了当前节点v的新高度。
     *
     * 当然，此后也需要从v出发沿parent引用逆行向上，依次更新各个祖先 的高度记录。这一过程可以描述为 算法四.5:
     *
     * 算法:updateHeight(v)
     * 输入:二叉树中任一节点v
     * 输出:更新v的高度记录
     * {
     * height(v) = 0;//先假设没有左、右孩子
     * 若v有左孩子lc，则令:height(v) = Max(height(v), 1 + height(lc));
     * 若v有右孩子lc，则令:height(v) = Max(height(v), 1 + height(rc));
     * 若v的父亲p存在，则调用updateHeight(p)，递归地更新父亲的高度记录;
     * }
     *
     * 同样地，若节点 v 的深度为 depth(v)，则总共需要修改 depth(v)+1 个节点的高度记录。
     * 更新每 一节点本身的高度记录，只需执行两次 getHeight()操作、两次加法以及两次取最大操作，不过常数 时间，故 updateHeight()算法的总体运行时间也是 O(depth(v)+1)。
     * 这一算法还可以进一步优化。我们注意到，在逆行向上依次更新各祖先高度的过程中，一旦发 现某个祖先的高度没有发生变化，算法即可提前终止。
     * TODO 请读者按此思路自行改进 {@link BinTreeNode#updateHeightBetter()}
     */
    @Override
    public void updateHeight() {
        //先假设没有左、右孩子，则叶子节点的高度=0
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

    public void updateHeightBetter() {
        //先假设没有左、右孩子，则叶子节点的高度=0
        height = 0;
        if (hasLChild()) {
            height = Math.max(height, 1 + getLChild().getHeight());
        }
        if (hasRChild()) {
            height = Math.max(height, 1 + getRChild().getHeight());
        }
        if (!hasParent()) {
            return;
        }
        BinTreePosition<E> parentOfThis = parent.getParent();
        //一旦发 现某个祖先的高度没有发生变化，算法即可提前终止。
        boolean change = true;
        while (change) {
            int heightOld = parentOfThis.getHeight();
            parentOfThis.updateHeight();
            int heightNew = parent.getHeight();
            //递归更新各个真祖先的高度记录
            if (heightOld == heightNew) {
                change = false;
            }
            parentOfThis = parent.getParent();
        }
    }

    @Override
    public int getDepth() {
        return depth;
    }

    /**
     * 在父亲节点发生变化后，有必要更新当前节点的深度记录。
     *
     * 只需读出新的父亲节点的深度，再加上一即得到当前节点新的深度。
     * 当然，
     * 此后还需要沿着lChild和rChild引用，逐层向下递归地更新每一后代的深度记录。
     *
     * 算法:updateDepth(v)
     * 输入:二叉树中任一节点v
     * 输出:更新v的深度记录
     * {
     * 若v的父亲节点p存在，则令depth(v) = depth(p)+1;
     * 否则，令depth(v) = 0;
     * 若v的左孩子lc存在，则调用updateDepth(lc);//沿孩子引用逐层向下，
     * 若v的右孩子rc存在，则调用updateDepth(rc);//递归地更新所有后代的深度记录
     * }
     *
     * 若节点 v 的后代规模为 size(v)，则总共需要修改 size(v)个节点的深度记录。
     * 鉴于单个节点的深度记录可以在常数时间内得到更新，故 updateDepth()算法的总体运行时间为 O(size(v))。
     * 与 updateHeight()算法类似，上述 updateDepth()算法也可以进行优化。
     */
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

    /**
     * 二叉树表示中，只要规定“左(右)子树必须 完全居于根节点的(左)右侧”，则所有节点在水平轴上投影的自左向右次序，
     * 恰好与中序遍历序列的次序吻合。从这个意义上说，中序遍历就是按照自左向右的次序访问各个节点。
     *
     * 按照这一思路，对于任一二叉树T，根据其中序遍历序列S(T)，我们都可以在其中所有节点之 间定义出一个线性次序。因此，除首(末)节点外的每一节点都有唯一的直接前驱(后继)。
     */
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

    /**
     * 该 方法的功能是，将以某一节点为根的子树从母树中分离出来。
     *
     * 算法:secede(v)
     * 输入:二叉树中任一节点v
     * 输出:将以v为根的子树丛母树中分离出来
     * {
     * 若v有父亲 {
     * 1.切断父亲指向v的引用;
     * 2.调用updateSize(v)和updateHeight(v)，更新v及其祖先的规模记录和高度记录;
     * 3.切断v指向父亲的引用;
     * 4.调用updateDepth(v)，更新v及其后代的深度记录;
     * }
     * }
     *
     * @return
     */
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

    /**
     * 算法:attachL(p, c)
     * 输入:两个二叉树节点p与c
     * 输出:将c作为左孩子，与p联接起来
     * {
     * 0.若p已经有左孩子lc，则首先调用secede(lc)将其摘除;
     * 0.调用secede(c)，使c及其后代脱离原属母树;
     * 1,2.设置相应的引用，在p和c之间建立父子关系;
     * 3.调用updateSize(p)和updateHeight(p)，更新节点p及其祖先的规模和高度;
     * 4.调用updateDepth(c)，更新c及其后代节点的深度;
     * }
     *
     * @param c c
     * @return
     */
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
        return this;
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

    /**
     * 作为树的一种特例，二叉树自然继承了一般树结构的前序、后序以及层次等遍历方法。这三个
     * 遍历算法的实现与普通树大同小异，这里不再赘述。
     * 需要特别指出的是，对二叉树还可以定义一个新的遍历方法⎯⎯中序遍历(Inorder traversal)。
     * 顾名思义，在访问每个节点之前，
     * 1.首先遍历其左子树;
     * 2.待该节点被访问过后，
     * 3.才遍历其右子树。
     * 类似地，由中序遍历确定的节点序列，称作中序遍历序列。第七章将要介绍的查找树，正是建立在中 序遍历序列的基础之上，由此也可见这一遍历算法的重要性。
     *
     * 算法:InorderTraversal(v)
     * 输入:二叉树节点v
     * 输出:v所有后代的中序遍历序列
     * {
     * if (null != v) {//设lc、rc分别为v的左、右孩子
     * 调用InorderTraversal(lc)对v的左子树做中序遍历;
     * 访问并输出v;
     * 调用InorderTraversal(rc)对v的右子树做中序遍历;
     * }
     * }
     */
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
