package com.janita.datastructuresandalgorithms.bookofdjh.chapter3;

import com.janita.datastructuresandalgorithms.bookofdjh.chapter2.Position;
import com.janita.datastructuresandalgorithms.bookofdjh.chapter3.iterator.Iterator;

/**
 * IteratorPosition
 *
 * @author zhucj
 * @since 20201126
 */
public class IteratorPosition<E> implements Iterator<Position<E>> {

    /**
     * 列表
     */
    private List<E> list;

    /**
     * 当前(下一个)位置
     */
    private Position<E> nextPosition;

    public IteratorPosition() {
        list = null;
    }

    public IteratorPosition(List<E> list) {
        this.list = list;
        if (list.isEmpty()) {
            nextPosition = null;
        } else {
            nextPosition = list.first();
        }
    }

    @Override
    public boolean haxNext() {
        return nextPosition != null;
    }

    @Override
    public Position<E> getNext() {
        if (!haxNext()) {
            throw new RuntimeException("意外:没有下一位置");
        }
        Position<E> currentPosition = nextPosition;
        if (currentPosition == list.last()) {
            nextPosition = null;
        } else {
            nextPosition = list.getNext(currentPosition);
        }
        return currentPosition;
    }
}