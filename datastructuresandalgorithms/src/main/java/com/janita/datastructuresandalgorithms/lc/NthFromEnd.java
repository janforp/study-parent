package com.janita.datastructuresandalgorithms.lc;

import org.junit.Assert;
import org.junit.Test;

/**
 * 漫画：如何找到链表的倒数第n个结点
 *
 * https://zhuanlan.zhihu.com/p/272053968
 *
 * @author zhucj
 * @since 20201224
 */
public class NthFromEnd {

    public static Node findNthFromEnd(Node head, int n) {
        Node start = head;
        Node end = head;

        //把p2指针移动到正数第n个结点
        for (int i = 1; i < n; i++) {
            end = end.next;
            if (end == null) {
                throw new IllegalArgumentException("参数n超出链表长度！");
            }
        }

        //p1和p2一起右移，直到p2指向链表尾结点
        //上面的遍历+下面的遍历共完成了一次遍历
        while (end.next != null) {
            start = start.next;
            end = end.next;
        }
        return start;
    }

    //快速创建链表
    private static Node buildLinkList(int[] array) {
        Node head = new Node(array[0]);
        Node p = head;
        for (int i = 1; i < array.length; i++) {
            p.next = new Node(array[i]);
            p = p.next;
        }
        return head;
    }

    //链表节点
    private static class Node {

        int data;

        Node next;

        Node(int data) {
            this.data = data;
        }
    }

    @Test
    public void test() {
        int[] inputs = { 5, 3, 7, 2, 4, 1, 9, 8 };
        Node head = buildLinkList(inputs);
        Node node = findNthFromEnd(head, 3);
        System.out.println("链表倒数第3个元素是：" + node.data);
        Assert.assertEquals(1, node.data);
    }
}
