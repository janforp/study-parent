package com.janita.datastructuresandalgorithms.bookofdjh.chapter2;

import com.janita.datastructuresandalgorithms.bookofdjh.stack.Stack;
import org.junit.Assert;
import org.junit.Test;

/**
 * NodeTest
 *
 * @author zhucj
 * @since 20201126
 */
public class NodeTest {

    @Test
    public void headerInsertTest() {
        Node<Integer> headerNode = new Node<>();
        headerNode.setElem(1);
        headerNode.setNext(new Node<>(2, headerNode));
        System.out.println(headerNode.getElem());
    }

    @Test
    public void stackListTest() {
        Stack<Integer> integerStack = new StackList<>();
        integerStack.push(1);
        integerStack.push(2);
        integerStack.push(3);
        Assert.assertEquals(3, integerStack.getSize());
        Assert.assertEquals(3, (int) integerStack.pop());
        Assert.assertEquals(2, (int) integerStack.pop());
        Assert.assertEquals(1, (int) integerStack.pop());
        Assert.assertTrue(integerStack.isEmpty());
    }
}