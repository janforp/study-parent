package com.janita.java.base.thinkinjava._17_container;

import java.util.AbstractList;

/**
 * 类说明：CountingIntegerList
 *
 * @author zhucj
 * @since 20200528
 */
public class CountingIntegerList extends AbstractList<Integer> {

    private int size;

    public CountingIntegerList(int size) {
        this.size = Math.max(size, 0);
    }

    @Override
    public Integer get(int index) {
        return index;
    }

    @Override
    public int size() {
        return size;
    }

    public static void main(String[] args) {
        CountingIntegerList integerList = new CountingIntegerList(30);
        String toString = integerList.toString();
        System.out.println(toString);
    }
} /* Output:
[0, 1, 2, 3, 4, 5, 6, 7, 8, 9, 10, 11, 12, 13, 14, 15, 16, 17, 18, 19, 20, 21, 22, 23, 24, 25, 26, 27, 28, 29]
*///:~