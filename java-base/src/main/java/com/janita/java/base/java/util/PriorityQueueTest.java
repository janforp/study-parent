package com.janita.java.base.java.util;

import lombok.AllArgsConstructor;
import lombok.Data;

import java.util.PriorityQueue;
import java.util.Queue;

/**
 * 类说明：PriorityQueueTest
 *
 * @author zhucj
 * @since 20200528
 */
public class PriorityQueueTest {

    public static void main(String[] args) {
        Queue<Item> itemQueue = Item.get();
        Queue<ItemReverse> itemReverses = ItemReverse.get();
        System.out.println(itemQueue);
        System.out.println(itemReverses);
    }

    private static Queue<Integer> getQueue() {
        PriorityQueue<Integer> queue = new PriorityQueue<>();
        queue.add(6);
        queue.add(5);
        queue.add(4);
        queue.add(3);
        queue.add(2);
        queue.add(1);
        return queue;
    }
}

@Data
@AllArgsConstructor
class Item implements Comparable<Item> {

    private Integer i;

    public static Queue<Item> get() {
        PriorityQueue<Item> queue = new PriorityQueue<>();
        queue.add(new Item(1));
        queue.add(new Item(2));
        queue.add(new Item(3));
        queue.add(new Item(4));
        queue.add(new Item(5));
        queue.add(new Item(6));
        return queue;
    }

    @Override
    public String toString(){
        return i.toString();
    }

    @Override
    public int compareTo(Item o) {
        return o.i.compareTo(i);
    }
}

@Data
@AllArgsConstructor
class ItemReverse implements Comparable<ItemReverse> {

    private Integer i;

    public static Queue<ItemReverse> get() {
        PriorityQueue<ItemReverse> queue = new PriorityQueue<>();
        queue.add(new ItemReverse(1));
        queue.add(new ItemReverse(2));
        queue.add(new ItemReverse(3));
        queue.add(new ItemReverse(4));
        queue.add(new ItemReverse(5));
        queue.add(new ItemReverse(6));
        return queue;
    }

    @Override
    public String toString(){
        return i.toString();
    }

    @Override
    public int compareTo(ItemReverse o) {
        return i.compareTo(o.i);
    }
}