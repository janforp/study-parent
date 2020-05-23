package com.janita.java.base.thinkinjava._16_array.arrays;

import com.janita.java.base.thinkinjava._15_genericity.Generator;
import com.janita.java.base.thinkinjava._16_array.Generated;
import com.janita.java.base.thinkinjava._16_array.RandomGenerator;
import com.janita.java.base.thinkinjava.util.ConvertTo;

import java.util.Arrays;

import static com.janita.java.base.thinkinjava.util.Print.print;

/**
 * 类说明：ArraySearching
 *
 * @author zhucj
 * @since 20200528
 */
public class ArraySearching {

    public static void main(String[] args) {
        Generator<Integer> gen = new RandomGenerator.Integer(1000);
        int[] a = ConvertTo.primitive(Generated.array(new Integer[25], gen));
        Arrays.sort(a);
        print("Sorted array: " + Arrays.toString(a));
        while (true) {
            int r = gen.next();
            int location = Arrays.binarySearch(a, r);
            if (location >= 0) {
                print("Location of " + r + " is " + location + ", a[" + location + "] = " + a[location]);
                break; // Out of while loop
            }
        }
    }
}

class AlphabeticSearch {

    public static void main(String[] args) {
        String[] sa = Generated.array(new String[30], new RandomGenerator.String(5));
        Arrays.sort(sa, String.CASE_INSENSITIVE_ORDER);
        System.out.println(Arrays.toString(sa));
        int index = Arrays.binarySearch(sa, sa[10], String.CASE_INSENSITIVE_ORDER);
        System.out.println("Index: " + index + "\n" + sa[index]);
    }
}