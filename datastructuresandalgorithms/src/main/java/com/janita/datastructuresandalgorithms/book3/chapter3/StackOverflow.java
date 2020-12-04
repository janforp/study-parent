package com.janita.datastructuresandalgorithms.book3.chapter3;

import java.util.Iterator;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

/**
 * 中缀表达式转化为后缀表达式
 *
 * @author zhucj
 * @since 20201224
 */
public class StackOverflow {

    private static int stack = 0;

    public static <T> void printList(Iterator<T> itr) {
        stack++;
        System.out.println("调用次数" + stack);
        if (!itr.hasNext()) {
            return;
        }
        System.out.println(itr.next());
        printList(itr);
    }

    public static <T> void printList2(Iterator<T> itr) {
        while (true) {
            if (!itr.hasNext()) {
                return;
            }
            System.out.println(itr.next());
        }
    }

    public static void main(String[] args) {
        List<Integer> list = Stream.iterate(1, i -> i + 1).limit(30000).collect(Collectors.toList());
        Iterator<Integer> iterator = list.iterator();
        printList(iterator);
    }
}