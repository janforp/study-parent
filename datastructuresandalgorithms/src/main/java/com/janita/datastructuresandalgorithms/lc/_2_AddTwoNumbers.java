package com.janita.datastructuresandalgorithms.lc;

import org.junit.Assert;
import org.junit.Test;

/**
 * 给出两个 非空 的链表用来表示两个非负的整数。其中，它们各自的位数是按照 逆序 的方式存储的，并且它们的每个节点只能存储 一位 数字。
 *
 * 如果，我们将这两个数相加起来，则会返回一个新的链表来表示它们的和。
 *
 * 您可以假设除了数字 0 之外，这两个数都不会以 0 开头。
 *
 * 示例：
 *
 * 输入：(2 ->= 4 ->= 3) + (5 ->= 6 ->= 4)
 * 输出：7 ->= 0 ->= 8
 * 原因：342 + 465 = 807
 *
 * 来源：力扣（LeetCode）
 * 链接：https://leetcode-cn.com/problems/add-two-numbers
 * 著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
 *
 * @author zhucj
 * @since 20201224
 */
public class _2_AddTwoNumbers {

    /**
     * 2 ->= 4 ->= 3
     * 5 ->= 6 ->= 4
     *
     * 7 ->= 0 ->= 8
     *
     * @param l1
     * @param l2
     * @return
     */
    public ListNode addTwoNumbers(ListNode l1, ListNode l2) {
        if (l1 == null) {
            return l2;
        }
        if (l2 == null) {
            return l1;
        }
        ListNode result = null;
        int carry = 0;

        ListNode next = null;
        int value;
        while (l1 != null || l2 != null) {
            next = new ListNode();
            if (l1 != null && l2 != null) {
                value = l1.val + l2.val + carry;
            } else if (l1 != null) {
                value = l1.val + carry;
            } else {
                value = l2.val + carry;
            }
            if (value >= 10) {
                carry = 1;
                value = value % 10;
            }
            if (l1 != null) {
                l1 = l1.next;
            }
            if (l2 != null) {
                l2 = l2.next;
            }
            if (result == null) {
                result = new ListNode(value, next);
            } else {
                //TODO
            }
        }
        return null;
    }

    @Test
    public void test() {
        ListNode l1 = new ListNode(2);
        l1.next = new ListNode(4);
        l1.next.next = new ListNode(3);

        ListNode l2 = new ListNode(5);
        l2.next = new ListNode(6);
        l2.next.next = new ListNode(4);

        ListNode listNode = addTwoNumbers(l1, l2);
        Assert.assertEquals(7, listNode.val);
        Assert.assertEquals(0, listNode.next.val);
        Assert.assertEquals(8, listNode.next.next.val);
    }
}

class ListNode {

    int val;

    ListNode next;

    ListNode() {
    }

    ListNode(int val) {
        this.val = val;
    }

    ListNode(int val, ListNode next) {
        this.val = val;
        this.next = next;
    }
}
