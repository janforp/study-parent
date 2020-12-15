package com.janita.datastructuresandalgorithms.book3.chapter4_tree;

import org.junit.Assert;
import org.junit.Test;

/**
 * SplayTreeMTest
 *
 * @author zhucj
 * @since 20201224
 */
public class SplayTreeMTest {

    @Test
    public void testInsert() {
        SplayTreeM<Integer> tree = create(3);
        boolean contains = tree.contains(1);
        Assert.assertTrue(contains);
        tree.insert(20);
        tree.insert(40);
        tree.insert(60);
        tree.contains(3);
    }

    private SplayTreeM<Integer> create(int i) {
        SplayTreeM<Integer> tree = new SplayTreeM<>();
        for (int j = 1; j <= i; j++) {
            tree.insert(j);
        }
        return tree;
    }
}
