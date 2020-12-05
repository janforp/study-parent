package com.janita.datastructuresandalgorithms.book3.chapter3;

import org.junit.Assert;
import org.junit.Test;

/**
 * 习题3.11
 * 单链表
 * 只有表头的引用
 *
 * @author zhucj
 * @since 20201224
 */
public class MySingleLinkedList<T> {

    private SingNode<T> head;

    public int size() {
        SingNode<T> temp = head;

        int size = 0;
        while (temp != null) {
            temp = temp.next;
            size++;
        }
        return size;
    }

    public void printList() {
        SingNode<T> temp = head;

        StringBuilder printStr = new StringBuilder();
        while (temp != null) {
            printStr.append(temp.data).append(",");
            temp = temp.next;
        }
        System.out.println(printStr);
    }

    public boolean contains(T x) {
        SingNode<T> temp = head;
        if (x == null) {
            while (temp != null) {
                if (temp.data == null) {
                    return true;
                }
                temp = temp.next;
            }
        } else {
            while (temp != null) {
                if (temp.data == x) {
                    return true;
                }
                temp = temp.next;
            }
        }
        return false;
    }

    public boolean addIfAbsent(T x) {
        boolean contains = contains(x);
        if (contains) {
            return false;
        }
        if (head == null) {
            head = new SingNode<>(x);
            return true;
        }
        SingNode<T> temp = head;
        while (temp.next != null) {
            temp = temp.next;
        }
        temp.next = new SingNode<>(x);
        return true;
    }

    public void removeIfExist(T x) {
        if (head == null) {
            return;
        }
        if (head.data == x) {
            head = head.next;
            return;
        }
        SingNode<T> node = head;
        while (node.next != null) {
            if (node.next.data == x) {
                node.next = node.next.next;
                return;
            }
            node = node.next;
        }
    }

    private static class SingNode<T> {

        private T data;

        private SingNode<T> next;

        public SingNode(T x) {
            data = x;
        }
    }

    @Test
    public void test() {
        MySingleLinkedList<Integer> list = new MySingleLinkedList<>();
        list.addIfAbsent(1);
        list.addIfAbsent(2);
        list.addIfAbsent(3);
        list.removeIfExist(2);
        list.printList();
        Assert.assertEquals(2, list.size());
        list.printList();
        list.removeIfExist(1);
        list.printList();
        list.removeIfExist(1);
        list.printList();
        list.removeIfExist(2);
        list.printList();
        list.removeIfExist(3);
        list.printList();
        list.addIfAbsent(1000);
        list.printList();
    }
}
