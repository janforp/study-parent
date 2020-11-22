package com.janita.datastructuresandalgorithms.bookofdjh.stack;

/**
 * StackArray
 *
 * @author zhucj
 * @since 20201126
 */
public class StackArray implements Stack {

    public static final int CAPACITY = 1024;//数组的默认容量

    protected int capacity;//数组的实际容量

    protected Object[] s;//对象数组

    protected int top = -1;//栈顶元素的位置

    public StackArray() {
        this(CAPACITY);
    }

    public StackArray(int capacity) {
        this.capacity = capacity;
        s = new Object[capacity];
    }

    @Override
    public int getSize() {
        return top + 1;
    }

    @Override
    public boolean isEmpty() {
        return top < 0;
    }

    @Override
    public Object top() throws ExceptionStackEmpty {
        if (isEmpty()) {
            throw new ExceptionStackEmpty("意外:栈空");
        }
        return s[top];
    }

    @Override
    public void push(Object ele) {
        if (getSize() == capacity) {
            throw new ExceptionStackFull("意外:栈溢出");
        }
        s[++top] = ele;
    }

    @Override
    public Object pop() throws ExceptionStackEmpty {
        if (isEmpty()) {
            throw new ExceptionStackEmpty("意外:栈空");
        }
        Object ele = s[top];
        s[top--] = null;
        return ele;
    }
}
