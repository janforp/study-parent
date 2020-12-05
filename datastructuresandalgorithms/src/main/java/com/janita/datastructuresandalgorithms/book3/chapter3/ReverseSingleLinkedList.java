package com.janita.datastructuresandalgorithms.book3.chapter3;

import org.junit.Assert;
import org.junit.Test;

import java.util.ArrayList;
import java.util.List;

/**
 * ReverseSingleLinkedList
 *
 * @author zhucj
 * @since 20201224
 */
public class ReverseSingleLinkedList {

    private static class SingNode<T> {

        private T data;

        private SingNode<T> next;

        public SingNode(T x) {
            data = x;
        }
    }

    /**
     * 反转单链表，如：
     * 1 -> 2 -> 3 -> null
     * 反转之后成为：
     * null <- 1 <- 2 <- 3
     *
     * @param head 链表头节点
     * @param <T>
     * @return
     */
    public static <T> SingNode<T> reverse(SingNode<T> head) {
        SingNode<T> current = head;
        SingNode<T> prev = null;
        while (current != null) {
            //下次遍历的元素
            SingNode<T> nextTemp = current.next;
            current.next = prev;
            prev = current;
            current = nextTemp;
        }
        return prev;
    }

    @Test
    public void reverse() {
        SingNode<Integer> head = new SingNode<>(1);
        head.next = new SingNode<>(2);
        head.next.next = new SingNode<>(3);
        head.next.next.next = new SingNode<>(4);
        SingNode<Integer> reverse = reverse(head);
        Assert.assertEquals(4, (int) reverse.data);
        Assert.assertEquals(3, (int) reverse.next.data);
        Assert.assertEquals(2, (int) reverse.next.next.data);
        Assert.assertEquals(1, (int) reverse.next.next.next.data);
    }

    /**
     * 使用 递归 实现单链表反转
     *
     * @param head 链表的 头节点
     * @return 返回反转后的 head 头结点
     */
    public static <T> SingNode<T> reverseByRec(SingNode<T> head) {
        if (head == null || head.next == null) {
            return head;
        }
        // 获取头结点的下个节点，使用temp临时节点存储
        SingNode<T> temp = head.next;
        // 递归调用
        SingNode<T> node = reverse(head.next);
        // 将头节点的下一个节点的指针域指向头节点
        temp.next = head;
        // 将头节点的指针域置为null
        head.next = null;
        return node;
    }

    @Test
    public void reverseByRec() {
        SingNode<Integer> head = new SingNode<>(1);
        head.next = new SingNode<>(2);
        head.next.next = new SingNode<>(3);
        head.next.next.next = new SingNode<>(4);
        SingNode<Integer> reverse = reverseByRec(head);
        Assert.assertEquals(4, (int) reverse.data);
        Assert.assertEquals(3, (int) reverse.next.data);
        Assert.assertEquals(2, (int) reverse.next.next.data);
        Assert.assertEquals(1, (int) reverse.next.next.next.data);
    }

    /**
     * 使用 遍历+辅助空间 进行链表反转
     *
     * @param head
     * @return 返回反转后的 head 头结点
     */
    public static <T> SingNode<T> reverseUseHelperList(SingNode<T> head) {
        // list集合 辅助空间
        List<SingNode<T>> list = new ArrayList<SingNode<T>>();

        while (head != null) {
            list.add(head);
            head = head.next;
        }

        for (int i = list.size() - 1; i > 0; i--) {
            SingNode<T> lastNode = list.get(i);
            SingNode<T> nodeBeforeLastNode = list.get(i - 1);
            lastNode.next = nodeBeforeLastNode;
            nodeBeforeLastNode.next = null;
        }
        // 返回头结点
        return list.get(list.size() - 1);
    }

    @Test
    public void reverseUseHelperList() {
        SingNode<Integer> head = new SingNode<>(1);
        head.next = new SingNode<>(2);
        head.next.next = new SingNode<>(3);
        head.next.next.next = new SingNode<>(4);
        SingNode<Integer> reverse = reverseUseHelperList(head);
        Assert.assertEquals(4, (int) reverse.data);
        Assert.assertEquals(3, (int) reverse.next.data);
        Assert.assertEquals(2, (int) reverse.next.next.data);
        Assert.assertEquals(1, (int) reverse.next.next.next.data);
    }
}
