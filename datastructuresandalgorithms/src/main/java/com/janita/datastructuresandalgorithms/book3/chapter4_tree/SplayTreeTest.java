package com.janita.datastructuresandalgorithms.book3.chapter4_tree;

import org.junit.Assert;
import org.junit.Test;

/**
 * SplayTreeMTest
 *
 * @author zhucj
 * @since 20201224
 */
public class SplayTreeTest {

    @Test
    public void testInsert() {
        SplayTree<Integer> tree = create(3);
        boolean contains = tree.contains(1);
        Assert.assertTrue(contains);
        tree.insert(20);
        tree.insert(40);
        tree.insert(60);
        tree.contains(3);
    }

    @Test
    public void testRemove() {
        SplayTree<Integer> tree = create(15);
        boolean contains = tree.contains(10);
        Assert.assertTrue(contains);//此时1-9在10的左侧，11-15在右侧
        String s = tree.toString();
        System.out.println(s);

        tree = create(15);
        tree.remove(10);
    }

    private SplayTree<Integer> create(int i) {
        SplayTree<Integer> tree = new SplayTree<>();
        for (int j = 1; j <= i; j++) {
            tree.insert(j);
        }
        return tree;
    }

    @Test
    public void testDr() {
        SplayTree<Integer> tree = create(7);
        boolean contains = tree.contains(1);
        Assert.assertTrue(contains);
    }
}
