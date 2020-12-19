package com.janita.datastructuresandalgorithms.book3.chapter4_tree;

import org.junit.Test;

/**
 * BTreeTest
 *
 * @author zhucj
 * @since 20201224
 */
public class BTreeTest {

    @Test
    public void test() {
        BTree<Integer> tree = new BTree<>(3);
        tree.insert(97);
        tree.insert(84);
        tree.insert(79);

        tree.insert(80);
    }
}