package com.janita.datastructuresandalgorithms.bookofdjh._chapter2;

import com.janita.datastructuresandalgorithms.bookofdjh.stack.Stack;
import com.janita.datastructuresandalgorithms.bookofdjh.stack.assist.ExceptionStackEmpty;

/**
 * 基于单链表实现栈
 * 由于栈的操作只限于栈顶元素，而单链表只有对首元素才能在O(1)时间内完成插入和删除，
 * 故这里把单链表的首节点作为栈顶，其余元素依次排列。此外，为了保证getSize()方法也能够在O(1) 时间内完成，还需借助一个实例变量来动态记录栈中元素的数目
 *
 * @author zhucj
 * @since 20201126
 */
public class StackList<E> implements Stack<E> {

    /**
     * 指向栈顶元素
     */
    private Node<E> top;

    /**
     * 栈中元素的数目
     */
    private int size;

    public StackList() {
        top = null;
        size = 0;
    }

    @Override
    public int getSize() {
        return size;
    }

    @Override
    public boolean isEmpty() {
        return top == null;
    }

    @Override
    public E top() throws ExceptionStackEmpty {
        if (isEmpty()) {
            throw new ExceptionStackEmpty("空了");
        }
        return top.getElem();
    }

    @Override
    public void push(E ele) {
        if (ele == null) {
            throw new NullPointerException("空数据");
        }
        //创建一个新节点，将其作为首节点插入
        top = new Node<>(ele, top);
        //更新规模记录
        size++;
    }

    @Override
    public E pop() throws ExceptionStackEmpty {
        if (top == null) {
            throw new ExceptionStackEmpty("空了");
        }
        E elem = top.getElem();
        //更新首节点引用
        top = top.getNext();
        //更新规模记录
        size--;
        return elem;
    }
}
