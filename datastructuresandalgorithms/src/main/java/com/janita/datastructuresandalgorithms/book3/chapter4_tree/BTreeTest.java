package com.janita.datastructuresandalgorithms.book3.chapter4_tree;

import org.junit.Assert;
import org.junit.Test;

import java.util.Vector;

/**
 * BTreeTest
 *
 * @author zhucj
 * @since 20201224
 */
public class BTreeTest {

    @Test
    public void testInsertFirstEle() {
        BTree<Integer> tree = new BTree<>(3);
        tree.insert(97);
        BTree.BTNode<Integer> root = tree.root;
        Vector<Integer> keys = root.keys;
        Assert.assertEquals(1, keys.size());
        Assert.assertEquals(97, (int) keys.get(0));
    }

    @Test
    public void testInsert2E() {
        BTree<Integer> tree = new BTree<>(3);
        tree.insert(97);
        tree.insert(84);
        Assert.assertEquals(2, tree.size);
        BTree.BTNode<Integer> root = tree.root;
        Vector<Integer> rootKeys = root.keys;
        Assert.assertNotNull(rootKeys);
        Assert.assertEquals(84, (int) rootKeys.get(0));
        Assert.assertEquals(97, (int) rootKeys.get(1));
        Vector<BTree.BTNode<Integer>> rootChildren = root.children;
        Assert.assertNotNull(rootChildren);
    }

    @Test
    public void testInsert3E() {
        //最多3阶。，最少一阶
        //最多2个关键码，最少一个关键码
        BTree<Integer> tree = new BTree<>(3);
        tree.insert(97);
        tree.insert(84);
        tree.insert(80);
        Assert.assertEquals(3, tree.size);
        BTree.BTNode<Integer> root = tree.root;
        Vector<Integer> rootKeys = root.keys;
        Assert.assertNotNull(rootKeys);
        Assert.assertEquals(84, (int) rootKeys.get(0));
        Vector<BTree.BTNode<Integer>> rootChildren = root.children;
        Assert.assertNotNull(rootChildren);
        Assert.assertEquals(80, (int) rootChildren.get(0).keys.get(0));
        Assert.assertEquals(97, (int) rootChildren.get(1).keys.get(0));
    }

    @Test
    public void testInsert3E_() {
        //最多3阶。，最少一阶
        //最多2个关键码，最少一个关键码
        BTree<Integer> tree = new BTree<>(3);
        tree.insert(97);
        tree.insert(80);
        tree.insert(84);
        Assert.assertEquals(3, tree.size);
        BTree.BTNode<Integer> root = tree.root;
        Vector<Integer> rootKeys = root.keys;
        Assert.assertNotNull(rootKeys);
        Assert.assertEquals(84, (int) rootKeys.get(0));
        Vector<BTree.BTNode<Integer>> rootChildren = root.children;
        Assert.assertNotNull(rootChildren);
        Assert.assertEquals(80, (int) rootChildren.get(0).keys.get(0));
        Assert.assertEquals(97, (int) rootChildren.get(1).keys.get(0));
    }
}