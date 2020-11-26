package com.janita.datastructuresandalgorithms.bookofdjh.chapter3;

import com.janita.datastructuresandalgorithms.bookofdjh.chapter2.Position;
import com.janita.datastructuresandalgorithms.bookofdjh.chapter2.deque.DLNode;

/**
 * 实现序列最自然、最直接的方式，就是利用双向链表{@link com.janita.datastructuresandalgorithms.bookofdjh.chapter2.deque.DLNode}。
 * 这样，来自列表 ADT 的每个方法都依然 可以保持原先 O(1)的时间复杂度。
 * 当然，来自向量 ADT 的方法尽管也可以借助双向链表来实现，但 其效率却会受到影响。
 * 实际上，如果希望保持原列表 ADT 中各方法的高效率(即通过位置类来确定 操作元素的位置)，就不可能显式地在序列中保留和维护各元素的秩。
 * 为了完成诸如 getAtRank(r) 之类的操作，我们不得不从列表的某一端起逐一扫描各个元素，直到发现秩为 r 的元素。于是，在 最坏情况下，这些操作的时间复杂度将注定为Ω(n)。
 *
 * @author zhucj
 * @since 20201126
 */
public class DLNodeSequence<E> extends DLNodeList<E> implements Sequence<E> {

    protected void check(int r, int n) {
        if (r < 0 || r >= n) {
            throw new IllegalArgumentException("意外:非法的秩\" + r + \"，应该属于[0, \" + n +\")");
        }
    }

    /**
     * TODO 要理解下 等价于{@link DLNodeSequence#rank2Pos2(int)}
     *
     * @param r
     * @return
     */
    @Override
    public Position<E> rank2Pos(int r) {
        DLNode<E> node;
        check(r, getSize());
        if (r <= getSize() / 2) {//若秩较小，则
            node = header.getNext();//从前端开始
            for (int i = 0; i < r; i++) {
                node = node.getNext();//逐一扫描
            }
        } else {
            node = trailer.getPrev();//从后端开始
            for (int i = 1; i < getSize() - r; i++) {
                node = node.getPrev();//逐一扫描
            }
        }
        return node;
    }

    public Position<E> rank2Pos2(int r) {
        check(r, getSize());
        DLNode<E> node;
        int mi = getSize() / 2;
        int i;
        if (r <= mi) {
            i = 0;
            node = header.getNext();
            while (++i <= r) {
                node = node.getNext();
            }
        } else {
            i = getSize() - 1;
            node = trailer.getPrev();
            while (--i >= r) {
                node = node.getPrev();
            }
        }
        return node;
    }

    @Override
    public int pso2Rank(Position<E> p) {
        DLNode<E> node = header.getNext();
        int r = 0;
        while (node != trailer) {
            if (node == p) {
                return r;
            }
            r++;
            node = node.getNext();
        }
        throw new RuntimeException("意外:作为参数的位置不属于序列");
    }

    @Override
    public E getAtRank(int r) {
        check(r, getSize());
        return rank2Pos(r).getElem();
    }

    @Override
    public E replaceAtRank(int r, E obj) {
        check(r, getSize());
        return replace(rank2Pos(r), obj);
    }

    @Override
    public E insertAtRank(int r, E obj) {
        check(r, getSize() + 1);
        if (getSize() == r) {
            insertFirst(obj);
            return obj;
        }
        insertBefore(rank2Pos(r), obj);
        return obj;
    }

    @Override
    public E removeAtRank(int r) {
        check(r, getSize());
        return remove(rank2Pos(r));
    }
}
