package com.janita.java.base.thinkinjava._17_container;

import com.janita.java.base.thinkinjava.util.Print;

import java.util.LinkedList;

import static com.janita.java.base.thinkinjava.util.Print.print;
import static com.janita.java.base.thinkinjava.util.Print.printnb;

/**
 * 类说明：使用 LinkedList 来实现一个双端队列
 *
 * @author zhucj
 * @since 20200528
 */
public class Deque<T> {

    /**
     * 组合一个 LinkedList
     */
    private LinkedList<T> deque = new LinkedList<T>();

    public void addFirst(T e) {
        deque.addFirst(e);
    }

    public void addLast(T e) {
        deque.addLast(e);
    }

    public T getFirst() {
        return deque.getFirst();
    }

    public T getLast() {
        return deque.getLast();
    }

    public T removeFirst() {
        return deque.removeFirst();
    }

    public T removeLast() {
        return deque.removeLast();
    }

    public int size() {
        return deque.size();
    }

    public String toString() {
        return deque.toString();
    }
    // And other methods as necessary...
}

class DequeTest {

    static void fillTest(Deque<Integer> deque) {
        //向头部添加7个元素
        for (int i = 20; i < 27; i++) {
            //26, 25, 24, 23, 22, 21, 20
            deque.addFirst(i);
        }
        //向尾部添加5个元素
        for (int i = 50; i < 55; i++) {
            //50, 51, 52, 53, 54
            deque.addLast(i);
        }
        //[26, 25, 24, 23, 22, 21, 20, 50, 51, 52, 53, 54]
    }

    public static void main(String[] args) {
        Deque<Integer> selfDefineDeque = new Deque<Integer>();
        fillTest(selfDefineDeque);
        print(selfDefineDeque);

        while (selfDefineDeque.size() != 0) {
            printnb(selfDefineDeque.removeFirst() + " ");
        }

        print();
        System.out.println();

        //试图再次添加元素
        fillTest(selfDefineDeque);
        while (selfDefineDeque.size() != 0) {
            printnb(selfDefineDeque.removeLast() + " ");
        }
    }
} /* Output:
[26, 25, 24, 23, 22, 21, 20, 50, 51, 52, 53, 54]
26 25 24 23 22 21 20 50 51 52 53 54
54 53 52 51 50 20 21 22 23 24 25 26
*///:~