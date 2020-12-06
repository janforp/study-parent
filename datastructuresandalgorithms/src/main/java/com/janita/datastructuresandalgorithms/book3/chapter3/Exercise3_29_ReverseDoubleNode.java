package com.janita.datastructuresandalgorithms.book3.chapter3;

import lombok.AllArgsConstructor;
import org.junit.Test;

/**
 * Exercise3_28_MyDeque
 *
 * @author zhucj
 * @since 20201224
 */
public class Exercise3_29_ReverseDoubleNode<T> {

    public static <T> DoubleNode<T> reverse(DoubleNode<T> head) {
        DoubleNode<T> current = head;
        DoubleNode<T> pre = null;
        while (current != null) {
            DoubleNode<T> next = current.next;
            current.next = pre;
            current.prev = next;
            pre = current;
            current = next;
        }
        return pre;
    }

    @AllArgsConstructor
    private static class DoubleNode<T> {

        private T data;

        private DoubleNode<T> prev;

        private DoubleNode<T> next;
    }

    @Test
    public void test() {
        DoubleNode<Integer> head = new DoubleNode<>(1, null, null);
        DoubleNode<Integer> node2 = new DoubleNode<>(2, head, null);
        head.next = node2;
        DoubleNode<Integer> node3 = new DoubleNode<>(3, node2, null);
        node2.next = node3;
        DoubleNode<Integer> reverseHead = reverse(head);
        System.out.println(reverseHead);
    }
}