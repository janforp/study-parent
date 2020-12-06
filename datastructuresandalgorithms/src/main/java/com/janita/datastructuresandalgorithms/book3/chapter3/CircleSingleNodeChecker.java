package com.janita.datastructuresandalgorithms.book3.chapter3;

import org.junit.Assert;
import org.junit.Test;

/**
 * CircleSingleNodeChecker
 *
 * @author zhucj
 * @since 20201224
 */
public class CircleSingleNodeChecker {

    /**
     * 判断单链表是否有环
     *
     * @param head
     * @param <T>
     * @return
     */
    public static <T> boolean isCircle(SingleNode<T> head) {
        SingleNode<T> slow = head;
        SingleNode<T> fast = head;
        while (fast != null && fast.next != null) {
            //两个指针先跑起来,即使在第一个元素就是环的入口也总是会相交
            slow = slow.next;
            fast = fast.next.next;
            if (slow == fast) {
                return true;
            }
        }
        return false;
    }

    @Test
    public void test() {
        SingleNode<Integer> head = new SingleNode<>(1);
        SingleNode<Integer> node2 = new SingleNode<>(2);
        SingleNode<Integer> node3 = new SingleNode<>(3);
        SingleNode<Integer> node4 = new SingleNode<>(4);
        SingleNode<Integer> node5 = new SingleNode<>(5);
        SingleNode<Integer> node6 = new SingleNode<>(6);

        head.next = node2;
        node2.next = node3;
        node3.next = node4;
        node4.next = node5;
        node5.next = node6;
        node6.next = node3;

        Assert.assertTrue(isCircle(head));

        node6.next = null;
        Assert.assertFalse(isCircle(head));

        node6.next = head;
        Assert.assertTrue(isCircle(head));
    }

    private static class SingleNode<T> {

        private T data;

        private SingleNode<T> next;

        public SingleNode(T x) {
            data = x;
        }

        @Override
        public String toString() {
            return "{" +
                    "data=" + data +
                    '}';
        }
    }
}
