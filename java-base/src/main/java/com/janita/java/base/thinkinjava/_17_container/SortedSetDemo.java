package com.janita.java.base.thinkinjava._17_container;

import java.util.Collections;
import java.util.Iterator;
import java.util.SortedSet;
import java.util.TreeSet;

import static com.janita.java.base.thinkinjava.util.Print.print;

/**
 * 类说明：SortedSetDemo
 *
 * @author zhucj
 * @since 20200528
 */
public class SortedSetDemo {

    public static void main(String[] args) {
        SortedSet<String> sortedSet = new TreeSet<String>();
        String[] strings = "one two three four five six seven eight".split(" ");
        Collections.addAll(sortedSet, strings);

        print(sortedSet);

        String low = sortedSet.first();
        String high = sortedSet.last();
        print(low);
        print(high);

        Iterator<String> it = sortedSet.iterator();
        for (int i = 0; i <= 6; i++) {
            if (i == 3) {
                low = it.next();
            }
            if (i == 6) {
                high = it.next();
            } else {
                it.next();
            }
        }
        print(low);
        print(high);

        SortedSet<String> subSet = sortedSet.subSet(low, high);
        print(subSet);

        SortedSet<String> headSet = sortedSet.headSet(high);
        print(headSet);

        SortedSet<String> tailSet = sortedSet.tailSet(low);
        print(tailSet);

    }
} /* Output:
[eight, five, four, one, seven, six, three, two]
eight
two
one
two
[one, seven, six, three]
[eight, five, four, one, seven, six, three]
[one, seven, six, three, two]
*///:~
