package com.janita.java.base.thinkinjava._16_array.arrays;

import com.janita.java.base.thinkinjava._15_genericity.Generator;
import com.janita.java.base.thinkinjava._16_array.Generated;

import java.util.Arrays;
import java.util.Collections;
import java.util.Comparator;
import java.util.Random;

import static com.janita.java.base.thinkinjava.util.Print.print;

/**
 * 类说明：CompType
 *
 * @author zhucj
 * @since 20200528
 */
public class CompType implements Comparable<CompType> {

    int i;

    int j;

    private static int count = 1;

    public CompType(int n1, int n2) {
        i = n1;
        j = n2;
    }

    public String toString() {
        String result = "[i = " + i + ", j = " + j + "]";
        if (count++ % 3 == 0) {
            result += "\n";
        }
        return result;
    }

    public int compareTo(CompType rv) {
        //(x < y) ? -1 : ((x == y) ? 0 : 1)
        return (Integer.compare(i, rv.i));
    }

    private static Random r = new Random(47);

    public static Generator<CompType> generator() {
        return () -> new CompType(r.nextInt(100), r.nextInt(100));
    }

    public static void main(String[] args) {
        CompType[] a = Generated.array(new CompType[12], generator());
        print("before sorting:");
        print(Arrays.toString(a));
        Arrays.sort(a);
        print("after sorting:");
        print(Arrays.toString(a));
    }
}

class Reverse {

    public static void main(String[] args) {
        CompType[] a = Generated.array(new CompType[12], CompType.generator());
        print("before sorting:");
        print(Arrays.toString(a));
        Arrays.sort(a, Collections.reverseOrder());
        print("after sorting:");
        print(Arrays.toString(a));
    }
}

class CompTypeComparator implements Comparator<CompType> {

    public int compare(CompType o1, CompType o2) {
        return (Integer.compare(o1.j, o2.j));
    }
}

class ComparatorTest {

    public static void main(String[] args) {
        CompType[] a = Generated.array(new CompType[12], CompType.generator());
        print("before sorting:");
        print(Arrays.toString(a));
        Arrays.sort(a, new CompTypeComparator());
        print("after sorting:");
        print(Arrays.toString(a));
    }
}