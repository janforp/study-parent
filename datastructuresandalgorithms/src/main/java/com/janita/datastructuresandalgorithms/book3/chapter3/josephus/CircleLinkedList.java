package com.janita.datastructuresandalgorithms.book3.chapter3.josephus;

import org.junit.Test;

/**
 * 循环链表的实现（特殊的单链表）
 *
 * @author liyanan
 * @date 2020/1/2 13:16
 */
public class CircleLinkedList {

    /**
     * 指向循环链表的第一个结点
     */
    private Node head;

    /**
     * 循环链表的长度
     */
    private int length;

    public CircleLinkedList() {
        head = null;
        length = 0;
    }

    public Node getHead() {
        return head;
    }

    public int getLength() {
        return length;
    }

    /**
     * 尾部插入
     *
     * @param newNode 一个单向链表节点
     */
    public void insert(Node newNode) {
        if (head == null) {
            // 循环链表为空时，head 指向新的节点
            head = newNode;//第一个元素就是头元素
        } else {//其他的都保持首位相连
            Node temp = head;
            // 找到循环链表最后一个节点
            while (temp.next != head) {
                temp = temp.next;
            }
            // 插入新的节点
            temp.next = newNode;
        }
        // 循环链表新的结点指向 NULL
        newNode.next = head;//因为插入的元素是列表的最后一个，位了保持首位相连，所以尾元素的next指向head
        // 循环链表长度加 1
        length++;
    }

    /**
     * 将新的结点插入到指定结点后
     *
     * @param preNode 指定节点
     * @param newNode 新的节点
     */
    public void insert(Node preNode, Node newNode) {
        newNode.next = preNode.next;
        preNode.next = newNode;
        length++;
    }

    /**
     * 删除数据域为指定值的元素
     *
     * @param e
     */
    public void del(int e) {
        //从头节点开始遍历查询
        Node delNode = head;
        while (length >= 0) {
            // 找到数据域为 e 的结点
            if (delNode.data == e) {
                if (delNode == head) {//如果被删除的元素的next为head
                    Node pre = findPre(delNode);
                    head = head.next;
                    pre.next = head;
                } else {
                    Node pre = findPre(delNode);
                    pre.next = delNode.next;
                    // 最后一个节点
                }
                // 循环链表长度减 1
                length--;
                return;
            }
            // 找到下一个结点
            delNode = delNode.next;
        }
    }

    private Node findPre(Node node) {
        Node temp = head;
        while (temp.next != node) {
            temp = temp.next;
        }
        return temp;
    }

    /**
     * 给定被删除元素的前一个结点，删除指定元素
     *
     * @param preNode
     */
    public Node del(Node preNode) {
        Node delNode = preNode.next;
        if (delNode == head) {
            // 维护 head 的指向
            head = head.next;
        }
        // 删除
        preNode.next = preNode.next.next;
        length--;
        return delNode;
    }

    /**
     * 随机访问第 k 个结点
     *
     * @param k
     * @return
     */
    public Node find(int k) {
        if (k <= 0) {
            throw new RuntimeException("输入的参数 k 必须大于 0");
        }
        int cnt = 1;
        Node temp = head;
        // 找到第 k 个元素
        while (cnt != k) {
            temp = temp.next;
            cnt++;
        }
        return temp;
    }

    /**
     * 打印循环链表所有有效节点
     *
     * @return
     */
    public String printCircleLinkedList() {
        StringBuilder sb = new StringBuilder();
        int cnt = 0;
        Node temp = head;
        while (head != null && cnt < getLength()) {
            sb.append(temp.data);
            sb.append(" -> ");
            temp = temp.next;
            cnt++;
        }
        sb.append(", length = ");
        sb.append(length);
        return sb.toString();
    }

    @Test
    public void test() {
        CircleLinkedList list = new CircleLinkedList();
        list.insert(new Node(1));
        list.insert(new Node(2));
        list.insert(new Node(3));
        System.out.println(list.printCircleLinkedList());
        list.del(3);
        System.out.println(list.printCircleLinkedList());
        list.insert(new Node(3));
        list.insert(new Node(4));
        System.out.println(list.printCircleLinkedList());
    }
}

/**
 * 模拟结点
 */
class Node {

    /**
     * 存储数据
     */
    public int data;

    /**
     * 指向下一个节点
     */
    public Node next;

    public Node() {
    }

    public Node(int data) {
        this.data = data;
    }
}