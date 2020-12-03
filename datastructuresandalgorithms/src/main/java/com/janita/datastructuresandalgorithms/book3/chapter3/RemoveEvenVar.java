package com.janita.datastructuresandalgorithms.book3.chapter3;

import org.junit.Test;

import java.util.Iterator;
import java.util.LinkedList;
import java.util.List;

/**
 * RemoveEvenVar
 *
 * @author zhucj
 * @since 20201224
 */
public class RemoveEvenVar {

    public static void removeEvenVar1(List<Integer> list) {
        int i = 0;
        while (i < list.size()) {
            if (list.get(i) % 2 == 0) {
                list.remove(i);
            } else {
                i++;
            }
        }
    }

    public static void removeEvenVar2(List<Integer> list) {
        Iterator<Integer> iterator = list.iterator();
        while (iterator.hasNext()) {
            Integer next = iterator.next();
            //在迭代器中无法删除，会报错
            list.remove(next);
        }
    }

    public static void removeEvenVar3(List<Integer> list) {
        Iterator<Integer> iterator = list.iterator();
        while (iterator.hasNext()) {
            Integer next = iterator.next();
            if (next % 2 == 0) {
                iterator.remove();
            }
        }
    }

    @Test
    public void test1() {
        List<Integer> list = new LinkedList<>();
        list.add(6);
        list.add(5);
        list.add(1);
        list.add(4);
        list.add(2);
        removeEvenVar1(list);
    }

    @Test
    public void test2() {
        List<Integer> list = new LinkedList<>();
        list.add(6);
        list.add(5);
        list.add(1);
        list.add(4);
        list.add(2);
        removeEvenVar2(list);
    }
}
