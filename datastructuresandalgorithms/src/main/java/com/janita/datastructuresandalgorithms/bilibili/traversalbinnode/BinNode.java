package com.janita.datastructuresandalgorithms.bilibili.traversalbinnode;

import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;

/**
 * BinNode
 *
 * @author zhucj
 * @since 20210128
 */
@AllArgsConstructor
@NoArgsConstructor
public class BinNode<T> {

    public T data;

    public BinNode<T> left;

    public BinNode<T> right;

    @Override
    public String toString() {
        return "BinNode{" +
                "data=" + data +
                '}';
    }

    public static BinNode<String> build() {
        BinNode<String> a = new BinNode<>("a", null, null);
        BinNode<String> b = new BinNode<>("b", null, null);
        BinNode<String> c = new BinNode<>("c", null, null);
        BinNode<String> d = new BinNode<>("d", null, null);
        BinNode<String> e = new BinNode<>("e", null, null);
        BinNode<String> f = new BinNode<>("f", null, null);
        BinNode<String> g = new BinNode<>("g", null, null);
        BinNode<String> h = new BinNode<>("h", null, null);
        BinNode<String> i = new BinNode<>("i", null, null);
        BinNode<String> j = new BinNode<>("j", null, null);
        BinNode<String> k = new BinNode<>("k", null, null);
        BinNode<String> l = new BinNode<>("l", null, null);
        BinNode<String> m = new BinNode<>("m", null, null);
        BinNode<String> n = new BinNode<>("n", null, null);
        BinNode<String> o = new BinNode<>("o", null, null);
        BinNode<String> p = new BinNode<>("p", null, null);

        i.left = d;
        i.right = l;

        d.left = c;
        d.right = h;

        l.left = k;
        l.right = n;

        c.left = a;

        h.left = f;

        k.left = j;

        n.left = m;
        n.right = p;

        a.right = b;

        f.left = e;
        f.right = g;

        p.left = o;

        return i;
    }
}