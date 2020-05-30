package com.janita.java.base.thinkinjava._19_enum.multicouplers;

import com.janita.java.base.thinkinjava.util.Enums;

/**
 * RoShamBo
 *
 * @author zhucj
 * @since 20200528
 */
public class RoShamBo {

    public static <T extends Competitor<T>> void match(T a, T b) {
        Outcome compete = a.compete(b);
        System.out.println(a + " vs. " + b + ": " + compete);
    }

    public static <T extends Enum<T> & Competitor<T>> void play(Class<T> rsbClass, int size) {
        for (int i = 0; i < size; i++) {
            T randomA = Enums.random(rsbClass);
            T randomB = Enums.random(rsbClass);
            match(randomA, randomB);
        }
    }
}

