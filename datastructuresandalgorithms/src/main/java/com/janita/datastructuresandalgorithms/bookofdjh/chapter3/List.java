package com.janita.datastructuresandalgorithms.bookofdjh.chapter3;

import com.janita.datastructuresandalgorithms.bookofdjh.chapter2.Position;

import java.util.Iterator;

/**
 * List
 *
 * @author zhucj
 * @since 20201126
 */
public interface List<E> {

    int getSize();

    boolean isEmpty();

    Position<E> first();

    Position<E> last();

    Position<E> getNext(Position<E> p);

    Position<E> getPrev(Position<E> p);

    Position<E> insertFirst(E e);

    Position<E> insertLast(E e);

    Position<E> insertAfter(Position<E> p, E e);

    /**
     * 算法:insertBefore(p, e)
     * 输入:位置p和元素e
     * 输出:将e插入至p之前后，返回插入的位置
     * {
     * 创建一个新节点v;
     * v.setElement(e);//用v来存放e
     * v.setNext(p);//以p为v的直接后继
     * v.setPrev(p.getPrev);//以p的直接前驱为v的直接前驱
     * (p.getPrev()).setNext(v);//p的直接前驱应该以v为直接后继
     * p.setPrev(v);//作为v的直接后继，p应该以v为直接前驱
     * return((Position)v);//v作为一个位置被返回
     * }
     *
     * @param p
     * @param e
     * @return
     */
    Position<E> insertBefore(Position<E> p, E e);

    /***
     * 为了删除存放于位置p的元素e，我们可以将p的直接前驱与 直接后继相互链接起来，从而将p剔除出去。一旦p不再被任何引用指向，内存回收器就会自动将其 空间回收
     * 算法:remove(p) 输入:位置p
     * 输出:删除存放于位置p处的元素e，并返回该元素
     * {
     * bak = p.element;//复制待删除的元素
     * (p.getNext()).setPrev(p.getPrev);//令p的直接后继以p的直接前驱为直接前驱
     * (p.getPrev()).setNext(p.getNext);//令p的直接前驱以p的直接后继为直接后继
     * p.setNext(null);//切断p的后继引用
     * p.setPrev(null);//切断p的前躯引用
     * return bak;//返回先前保留的备份
     * }
     * 得益于哨兵节点的设置，我们的算法可以不用考虑各种退化情况
     *
     * @param p
     * @return
     */
    E remove(Position<E> p);

    E removeFirst();

    E removeLast();

    E replace(Position<E> p, E e);

    Iterator<E> positions();

    Iterator<E> elements();
}