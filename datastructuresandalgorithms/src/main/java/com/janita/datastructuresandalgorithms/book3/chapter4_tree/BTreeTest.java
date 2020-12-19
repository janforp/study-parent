package com.janita.datastructuresandalgorithms.book3.chapter4_tree;

import org.junit.Assert;
import org.junit.Test;

import java.util.Arrays;
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

    @Test
    public void test4() {
        BTree<Integer> tree = create();
        tree.insert(23);
        tree.insert(29);
        tree.insert(45);

        BTree.BTNode<Integer> root = tree.root;
        Assert.assertEquals(36, (int) root.keys.get(0));
        Assert.assertEquals(53, (int) root.keys.get(1));

        Vector<BTree.BTNode<Integer>> children = root.children;
        Assert.assertEquals(3, children.size());

        BTree.BTNode<Integer> _23 = children.get(0);
        BTree.BTNode<Integer> _45 = children.get(1);
        BTree.BTNode<Integer> _77_89 = children.get(2);

        Assert.assertEquals(1, _23.keys.size());
        Assert.assertEquals(45, (int) _45.keys.get(0));

        Assert.assertEquals(77, (int) _77_89.keys.get(0));
        Assert.assertEquals(89, (int) _77_89.keys.get(1));

        Assert.assertEquals(19, (int) _23.children.get(0).keys.get(0));
        Assert.assertEquals(29, (int) _23.children.get(1).keys.get(0));

        Assert.assertEquals(75, (int) _77_89.children.get(0).keys.get(0));
        Assert.assertEquals(97, (int) _77_89.children.get(2).keys.get(0));

        Assert.assertEquals(79, (int) _77_89.children.get(1).keys.get(0));

        Assert.assertEquals(79, (int) _77_89.children.get(1).keys.get(0));
        Assert.assertEquals(84, (int) _77_89.children.get(1).keys.get(1));
    }

    private static Vector<Integer> ofKeys(Integer... es) {
        Vector<Integer> vector = new Vector<>(Arrays.asList(es));
        return vector;
    }

    private static Vector<BTree.BTNode<Integer>> ofChildren(int size, BTree.BTNode<Integer>... children) {
        Vector<BTree.BTNode<Integer>> vector = new Vector<>(Arrays.asList(children).subList(0, size));
        return vector;
    }

    private BTree<Integer> create() {

        BTree.BTNode<Integer> _53 = new BTree.BTNode<>();

        BTree.BTNode<Integer> _77_89 = new BTree.BTNode<>();

        BTree.BTNode<Integer> _75 = new BTree.BTNode<>();

        BTree.BTNode<Integer> _79_84 = new BTree.BTNode<>();

        BTree.BTNode<Integer> _97 = new BTree.BTNode<>();

        BTree.BTNode<Integer> _36 = new BTree.BTNode<>();

        BTree.BTNode<Integer> _19 = new BTree.BTNode<>();

        BTree.BTNode<Integer> _41_51 = new BTree.BTNode<>();

        _19.parent = _36;
        _41_51.parent = _36;

        _75.parent = _77_89;
        _79_84.parent = _77_89;
        _97.parent = _77_89;

        _36.parent = _53;
        _77_89.parent = _53;

        _53.children = ofChildren(2, _36, _77_89);
        _36.children = ofChildren(2, _19, _41_51);
        _77_89.children = ofChildren(3, _75, _79_84, _97);

        _19.children = ofChildren(2, null, null);
        _41_51.children = ofChildren(3, null, null, null);
        _75.children = ofChildren(2, null, null);
        _79_84.children = ofChildren(3, null, null, null);
        _97.children = ofChildren(2, null, null);

        _19.keys = ofKeys(19);
        _41_51.keys = ofKeys(41, 51);
        _36.keys = ofKeys(36);
        _75.keys = ofKeys(75);
        _79_84.keys = ofKeys(79, 84);
        _97.keys = ofKeys(97);

        _77_89.keys = ofKeys(77, 89);
        _53.keys = ofKeys(53);

        BTree<Integer> tree = new BTree<>(3);
        tree.root = _53;
        tree.size = 11;
        return tree;
    }
}