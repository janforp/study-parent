package com.janita.datastructuresandalgorithms.bookofdjh.chapter2.deque;

/**
 * 双端队列(Double-ended queue)，简称为Deque。顾名思义， 也就是前端与后端都支持插入和删除操作的队列
 *
 * @author zhucj
 * @since 20201126
 */
public interface Deque<E> {

    /**
     * 将对象 ele 作为 首 元素插入 输入:一个对象 输出:无
     *
     * @param ele
     */
    void insertFirst(E ele);

    /**
     * 将对象 ele 作为 末 元素插入 输入:一个对象 输出:无
     *
     * @param ele
     */
    void insertLast(E ele);

    /**
     * 若队列非空，则将首元素删除，并将其内容返回 否则，报错
     * 输入:无
     * 输出:对象
     *
     * @return
     */
    E removeFirst();

    /**
     * 若队列非空，则将末元素删除，并将其内容返回 否则，报错
     * 输入:无
     * 输出:对象
     *
     * @return
     */
    E removeLast();

    /**
     * 若队列非空，则返回首元素的内容 否则，报错
     * 输入:无
     * 输出:对象
     *
     * @return
     */
    E first();

    /**
     * 若队列非空，则返回末元素的内容 否则，报错
     * 输入:无
     * 输出:对象
     *
     * @return
     */
    E last();

    /**
     * 报告队列中的元素数目
     * 输入:无
     * 输出:非负整数
     *
     * @return
     */
    int getSize();

    /**
     * 判断队列是否为空
     * 输入:无
     * 输出:布尔量
     *
     * @return
     */
    boolean isEmpty();

    /**
     * 遍历
     */
    void traversal();
}