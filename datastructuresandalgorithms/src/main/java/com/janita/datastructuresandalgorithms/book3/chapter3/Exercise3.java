package com.janita.datastructuresandalgorithms.book3.chapter3;

import com.google.common.collect.Lists;
import org.junit.Assert;
import org.junit.Test;

import java.util.ArrayList;
import java.util.List;

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

    /**
     * @param arr 所有数据
     * @param n 数据的总数
     * @param m 经过m次传递后拿着土豆的人被清除
     * @param <T>
     * @return
     */
    private static <T extends JosephusPlayer> T josephusProblem(T[] arr, int n, int m) {
        int startIndex = 0;
        while (true) {
            int passNum = 0;
            for (int i = startIndex; i < arr.length; i++) {
                passNum++;
                T t = arr[startIndex];

            }
        }
    }

    private static class JosephusPlayer {

        private boolean out;
    }
}
