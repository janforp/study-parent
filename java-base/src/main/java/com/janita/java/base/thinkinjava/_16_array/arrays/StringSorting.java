package com.janita.java.base.thinkinjava._16_array.arrays;

import com.janita.java.base.thinkinjava._16_array.Generated;
import com.janita.java.base.thinkinjava._16_array.RandomGenerator;

import java.util.Arrays;
import java.util.Collections;

import static com.janita.java.base.thinkinjava.util.Print.print;

/**
 * 类说明：StringSorting
 *
 * @author zhucj
 * @since 20200528
 */
public class StringSorting {

    public static void main(String[] args) {
        String[] sa = Generated.array(new String[20], new RandomGenerator.String(5));
        print("Before sort: " + Arrays.toString(sa));
        Arrays.sort(sa);
        print("After sort: " + Arrays.toString(sa));
        Arrays.sort(sa, Collections.reverseOrder());
        print("Reverse sort: " + Arrays.toString(sa));
        Arrays.sort(sa, String.CASE_INSENSITIVE_ORDER);
        print("Case-insensitive sort: " + Arrays.toString(sa));
    }
}
