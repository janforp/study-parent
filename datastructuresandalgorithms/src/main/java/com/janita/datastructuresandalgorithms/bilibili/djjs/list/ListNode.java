package com.janita.datastructuresandalgorithms.bilibili.djjs.list;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * ListNode
 *
 * @author zhucj
 * @since 20201126
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
public class ListNode<T> {

    private T data;

    private ListNode<T> pred;

    private ListNode<T> succ;
}
