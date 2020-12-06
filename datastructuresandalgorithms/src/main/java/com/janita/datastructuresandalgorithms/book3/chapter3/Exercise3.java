package com.janita.datastructuresandalgorithms.book3.chapter3;

import com.google.common.collect.Lists;
import com.janita.datastructuresandalgorithms.book3.chapter3.queue.MyLinkedListUseLazyDelete;
import com.janita.datastructuresandalgorithms.book3.chapter3.queue.MyList;
import org.junit.Assert;
import org.junit.Test;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.List;
import java.util.ListIterator;
import java.util.NoSuchElementException;

/**
 * Exercise
 *
 * @author zhucj
 * @since 20201224
 */
public class Exercise3 {

    public static <T> void printLots(List<T> l, List<Integer> p) {
        for (Integer idx : p) {
            System.out.println(l.get(idx));
        }
    }

    static class SNode<T> {

        private T data;

        private SNode<T> next;
    }

    static class DNode<T> {

        private T data;

        private SNode<T> prev;

        private SNode<T> next;
    }

    @Test
    public void exercise3_2() {

    }

    @Test
    public void exercise3_3() {
        MyLinkedList<Integer> linkedList = new MyLinkedList<>();
        linkedList.add(1);
        linkedList.add(2);
        Assert.assertFalse(linkedList.contains(3));
        Assert.assertTrue(linkedList.contains(1));
        Assert.assertFalse(linkedList.contains(null));
        linkedList.add(null);
        Assert.assertTrue(linkedList.contains(null));
    }

    @Test
    public void exercise3_4_5() {
        List<Integer> list = intersection(Lists.newArrayList(1, 2, 3, 4), Lists.newArrayList(3, 4, 5, 6));
        Assert.assertEquals(3, (int) list.get(0));
        Assert.assertEquals(4, (int) list.get(1));
    }

    /**
     * 给定两个已排序的表L1,L2,只使用表的基本操作编写L1 ∩ L2(L1,L2的交集)
     *
     * @param leftOrderList 升序排列集合
     * @param rightOrderList 升序排列集合
     * @param <T>
     * @return
     */
    private static <T extends Comparable<? super T>> List<T> intersection(List<T> leftOrderList, List<T> rightOrderList) {
        List<T> list = new ArrayList<>();
        int i = 0;
        int j = 0;
        //2个集合任意一个下标越界，则循环结束
        while (i < leftOrderList.size() && j < rightOrderList.size()) {
            T left = leftOrderList.get(i);
            T right = rightOrderList.get(j);
            //两个值相同，则添加到集合
            if (right.compareTo(left) == 0) {
                list.add(left);
                i++;
                continue;
            }
            //如果左边集合的数据更大，则右侧集合下标向前推进
            if (left.compareTo(right) > 0) {
                j++;
                continue;
            }
            //如果右边集合的数据更大，则左侧集合下标向前推进
            if (left.compareTo(right) < 0) {
                i++;
            }
        }
        return list;
    }

    private static <T extends Comparable<? super T>> List<T> union(List<T> leftOrderList, List<T> rightOrderList) {
        leftOrderList.addAll(rightOrderList);
        return leftOrderList;
    }

    @Test
    public void exercise3_6() {
        //约瑟夫问题
        List<Integer> list = intersection(Lists.newArrayList(1, 2, 3, 4), Lists.newArrayList(3, 4, 5, 6));
        Assert.assertEquals(3, (int) list.get(0));
        Assert.assertEquals(4, (int) list.get(1));
    }

    @Test
    public void tes3_6t() {
        int winner = JosephusPlayer.josephusProblem(Lists.newArrayList(1, 2, 3, 4, 5, 6, 7, 8, 9, 10), 2);
        Assert.assertEquals(4, winner);

        winner = JosephusPlayer.josephusProblem(Lists.newArrayList(1, 2, 3, 4, 5, 6), 3);
        Assert.assertEquals(5, winner);
    }

    private static class JosephusPlayer {

        /**
         * 从列表的第一个个元素开始计数，第m个元素，删除 m + 1 元素，然后继续从m后开始数
         *
         * @param playerList 所有数据
         * @param m 经过m次传递后拿着土豆的人被清除
         * @param <T>
         * @return
         */
        private static <T> T josephusProblem(List<T> playerList, int m) {
            //不考虑极端情况
            int index = 0;
            while (playerList.size() > 1) {
                index = (index + m) % playerList.size();
                T remove = playerList.remove(index);
                System.out.println(remove + "被移除");
            }
            System.out.println(playerList.get(0) + "赢了");
            return playerList.get(0);
        }
    }

    private static void removeFirstHalf(List<Integer> lst) {
        int theSIze = lst.size() / 2;
        for (int i = 0; i < theSIze; i++) {
            lst.remove(0);
        }
    }

    private static void removeFirstHalfUseIterator(List<Integer> lst) {
        int theSIze = lst.size() / 2;
        Iterator<Integer> iterator = lst.iterator();
        int start = 1;
        while (iterator.hasNext() && start <= theSIze) {
            iterator.next();
            iterator.remove();
            start++;
        }
    }

    @Test
    public void exercise3_8() {
        List<Integer> list = new ArrayList<>();
        list.add(1);
        list.add(2);
        list.add(3);
        list.add(4);
        removeFirstHalf(list);
        for (Integer integer : list) {
            System.out.println(integer);
        }

        list = new ArrayList<>();
        list.add(1);
        list.add(2);
        list.add(3);
        list.add(4);
        removeFirstHalfUseIterator(list);
        for (Integer integer : list) {
            System.out.println(integer);
        }

        list = new LinkedList<>();
        list.add(1);
        list.add(2);
        list.add(3);
        list.add(4);
        removeFirstHalf(list);
        for (Integer integer : list) {
            System.out.println(integer);
        }

        list = new LinkedList<>();
        list.add(1);
        list.add(2);
        list.add(3);
        list.add(4);
        removeFirstHalfUseIterator(list);
        for (Integer integer : list) {
            System.out.println(integer);
        }
    }

    @Test
    public void exercise3_13() {
        MyArrayList<Integer> list = new MyArrayList<>();
        list.add(1);
        list.add(2);
        list.add(3);
        list.add(4);
        list.add(5);
        ListIterator<Integer> listIterator = list.listIterator();
        while (listIterator.hasNext()) {
            Integer next = listIterator.next();
            System.out.println(next);
        }
        while (listIterator.hasPrevious()) {
            Integer previous = listIterator.previous();
            System.out.println(previous);
        }
    }

    @Test
    public void exercise3_14() {
        MyLinkedList<Integer> list = new MyLinkedList<>();
        list.add(1);
        list.add(2);
        list.add(3);
        list.add(4);
        list.add(5);
        ListIterator<Integer> listIterator = list.listIterator();
        while (listIterator.hasNext()) {
            Integer next = listIterator.next();
            System.out.println(next);
        }
        while (listIterator.hasPrevious()) {
            Integer previous = listIterator.previous();
            System.out.println(previous);
        }
    }

    @Test
    public void exercise3_15() {
        MyLinkedList<Integer> list = new MyLinkedList<>();
        list.add(1);
        list.add(2);
        list.add(3);
        list.add(4);
        list.add(5);

        MyLinkedList<Integer> list2 = new MyLinkedList<>();
        list2.add(10);
        list2.add(100);

        list.splice(list2);

        Assert.assertEquals(7, list.size());
        for (Integer integer : list) {
            System.out.println(integer);
        }
    }

    @Test
    public void exercise3_16() {
        MyArrayList<Integer> list = new MyArrayList<>();
        list.add(1);
        list.add(2);
        list.add(3);
        list.add(4);
        list.add(5);

        Iterator<Integer> reverseIterator = list.reverseIterator();
        while (reverseIterator.hasNext()) {
            System.out.println(reverseIterator.next());
        }
    }

    @Test
    public void exercise3_18() {
        MyLinkedList<Integer> list = new MyLinkedList<>();
        list.add(1);
        list.add(2);
        list.add(3);
        list.add(4);
        list.add(5);

        list.addFirst(0);
        Assert.assertEquals(0, (int) list.get(0));
        list.addLast(6);
        Assert.assertEquals(6, (int) list.getLast());
        list.removeFirst();
        Assert.assertEquals(1, (int) list.getFirst());
        list.removeLast();
        Assert.assertEquals(5, (int) list.getLast());
    }

    @Test
    public void exercise3_20() {
        MyList<Integer> list = new MyLinkedListUseLazyDelete<>();
        list.add(1);
        list.add(2);
        list.add(3);
        list.add(4);
        list.add(5);
        list.add(6);
        Integer remove = list.remove(0);
        Assert.assertEquals(1, (int) remove);

        Assert.assertEquals(5, list.size());
        Assert.assertEquals(2, (int) list.getFirst());
        Integer first = list.removeFirst();
        Assert.assertEquals(2, (int) first);
        Assert.assertEquals(4, list.size());
        list.add(7);
        Assert.assertEquals(5, list.size());

        Integer last = list.removeLast();
        Assert.assertEquals(7, (int) last);
        list.add(8);
        Assert.assertEquals(8, (int) list.getLast());
        Assert.assertEquals(3, (int) list.getFirst());

        list.removeFirst();
        list.removeFirst();
        list.removeFirst();
        list.removeFirst();
        list.removeFirst();

        Assert.assertTrue(list.isEmpty());

        NoSuchElementException exception = null;
        try {
            list.removeFirst();
        } catch (NoSuchElementException e) {
            exception = e;
        }
        Assert.assertNotNull(exception);
    }

    @Test
    public void exercise3_25() {
        Exercise3_25_MyIntStack myIntStack = new Exercise3_25_MyIntStack(10);
        int[] a = { 2, -4, -5, 4, -100, 7, -2, -9, -3 };
        for (int i = 0; i < a.length; i++) {
            myIntStack.push(a[i]);
        }
        Assert.assertEquals(-100, myIntStack.findMin());
    }

    @Test
    public void exercise3_28() {
        Exercise3_28_MyDeque<Integer> deque = new Exercise3_28_MyDeque<>();
        deque.push(1);
        int pop = deque.pop();
        Assert.assertEquals(1, pop);

        deque.push(1);
        int eject = deque.eject();
        Assert.assertEquals(1, eject);

        deque.push(1);
        deque.inject(2);
        deque.push(3);
        deque.inject(4);
        //3124
        Assert.assertEquals(3, (int) deque.pop());
        Assert.assertEquals(1, (int) deque.pop());
        Assert.assertEquals(2, (int) deque.pop());
        Assert.assertEquals(4, (int) deque.pop());

        deque.push(1);
        deque.inject(2);
        deque.push(3);
        deque.inject(4);

        deque.printReverse();
        //3124
        Assert.assertEquals(4, (int) deque.eject());
        Assert.assertEquals(2, (int) deque.eject());
        Assert.assertEquals(1, (int) deque.eject());
        Assert.assertEquals(3, (int) deque.eject());
    }
}
