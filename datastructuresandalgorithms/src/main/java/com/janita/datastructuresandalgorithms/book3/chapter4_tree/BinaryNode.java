package com.janita.datastructuresandalgorithms.book3.chapter4_tree;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

/**
 * BinaryNode
 *
 * @author zhucj
 * @since 20201224
 */
@NoArgsConstructor
@AllArgsConstructor
@Getter
public class BinaryNode<T> {

    private T element;

    private BinaryNode<T> left;

    private BinaryNode<T> right;

    public BinaryNode(T element) {
        this(element, null, null);
    }
}
