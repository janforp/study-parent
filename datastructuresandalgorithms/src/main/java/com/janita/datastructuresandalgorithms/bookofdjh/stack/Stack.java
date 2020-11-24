package com.janita.datastructuresandalgorithms.bookofdjh.stack;

import com.janita.datastructuresandalgorithms.bookofdjh.stack.assist.ExceptionStackEmpty;

/**
 * Stack
 *
 * @author zhucj
 * @since 20201126
 */
public interface Stack<E> {

    int getSize();//返回栈中元素数目

    boolean isEmpty();//判断栈是否为空

    E top() throws ExceptionStackEmpty;//取栈顶元素(但不删除)  void push (Object ele);//入栈

    void push(E ele);//入栈

    E pop() throws ExceptionStackEmpty;//出栈
}