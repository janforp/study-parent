package com.janita.java.base.thinkinjava._15_genericity.erased;

import java.util.ArrayList;

/**
 * 类说明：ErasedTypeEquivalence
 *
 * @author zhucj
 * @since 20200528
 */
public class ErasedTypeEquivalence {

    public static void main(String[] args) {

        Class<? extends ArrayList> c1 = new ArrayList<Integer>().getClass();
        Class<? extends ArrayList> c2 = new ArrayList<String>().getClass();
        System.out.println(c1 == c2);
    }
}
