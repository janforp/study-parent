package com.janita.java.base.thinkinjava.util;

import com.janita.java.base.thinkinjava._15_genericity.Generator;
import com.janita.java.base.thinkinjava._16_array.RandomGenerator;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.LinkedHashSet;
import java.util.Set;

/**
 * 类说明：CollectionData
 *
 * @author zhucj
 * @since 20200528
 */
public class CollectionData<T> extends ArrayList<T> {

    public CollectionData(Generator<T> gen, int quantity) {
        for (int i = 0; i < quantity; i++) {
            T next = gen.next();
            add(next);
        }
    }

    // A generic convenience method:
    public static <T> CollectionData<T> list(Generator<T> gen, int quantity) {
        return new CollectionData<T>(gen, quantity);
    }
}

class Government implements Generator<String> {

    String[] foundation = ("strange women lying in ponds " +
            "distributing swords is no basis for a system of " +
            "government").split(" ");

    private int index;

    public String next() {
        return foundation[index++];
    }
}

class CollectionDataTest {

    public static void main(String[] args) {
        CollectionData<String> collectionData = new CollectionData<>(new Government(), 15);
        Set<String> set = new LinkedHashSet<String>(collectionData);
        // Using the convenience method:
        CollectionData<String> list = CollectionData.list(new Government(), 15);
        set.addAll(list);
        System.out.println(set);
    }
}

class CollectionDataGeneration {

    public static void main(String[] args) {
        // Convenience method
        RandomGenerator.String stringGenerator = new RandomGenerator.String(9);
        CollectionData<String> collectionData = CollectionData.list(stringGenerator, 10);
        System.out.println(new ArrayList<String>(collectionData));

        RandomGenerator.Integer integerGenerator = new RandomGenerator.Integer();
        CollectionData<Integer> integerCollectionData = new CollectionData<>(integerGenerator, 10);
        System.out.println(new HashSet<Integer>(integerCollectionData));
    }
}