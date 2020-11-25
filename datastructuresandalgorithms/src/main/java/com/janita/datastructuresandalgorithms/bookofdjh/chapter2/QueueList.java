package com.janita.datastructuresandalgorithms.bookofdjh.chapter2;

import com.janita.datastructuresandalgorithms.bookofdjh.queue.Queue;
import com.janita.datastructuresandalgorithms.bookofdjh.queue.assist.ExceptionQueueEmpty;
import com.janita.datastructuresandalgorithms.bookofdjh.queue.assist.ExceptionQueueFull;

/**
 * 基于单链表实现队列
 * 与栈一样，我们也可以借助单链表来实现队列ADT。同样地，出于效率方面的考虑，
 * 我们将以 单链表的首(末)节点作为队列的首(末)节点⎯⎯这样，可以回避单链表在尾部进行删除操作时 效率低下的缺陷。此外，还需要两个实例变量分别指示表的首、末节点
 *
 * @author zhucj
 * @since 20201126
 */
public class QueueList<E> implements Queue<E> {

    /**
     * 指向表首元素
     */
    private Node<E> head;

    /**
     * 指向表末元素
     */
    private Node<E> tail;

    /**
     * 队列中元素的数目
     */
    private int size;

    public QueueList() {
        head = tail = null;
        size = 0;
    }

    @Override
    public int getSize() {
        return size;
    }

    @Override
    public boolean isEmpty() {
        return size == 0;
    }

    @Override
    public E front() throws ExceptionQueueEmpty {
        if (isEmpty()) {
            throw new ExceptionQueueEmpty("空了");
        }
        return head.getElem();
    }

    /**
     * 队尾进，从单链表的尾部插入
     *
     * @param obj
     * @throws ExceptionQueueFull
     */
    @Override
    public void enqueue(E obj) throws ExceptionQueueFull {
        Node<E> node = new Node<>();
        node.setElem(obj);
        //新节点作为末节点插入，尾节点的next为null
        node.setNext(null);
        if (size == 0) {
            //若此前队列为空，则直接插入
            head = node;
        } else {
            //否则，将新节点接至队列末端
            //这块有个主意的地方，一旦rail设置了next属性，因为front节点与rail节点指向了同一个node节点，持有同一个结点的一个引用，因此front节点next属性也被填充
            tail.setNext(node);
        }
        //更新指向末节点引用
        tail = node;
        //更新规模
        size++;
    }

    /**
     * 队尾进，从单链表的尾部插入
     *
     * @param obj
     * @throws ExceptionQueueFull
     */
    public void enqueue2(E obj) throws ExceptionQueueFull {
        if (size == 0) {
            head = new Node<>(obj, null);
            tail = head;
            size++;
            return;
        }
        Node<E> node = new Node<>(obj, null);
        tail.setNext(node);
        tail = node;
        size++;
    }

    /**
     * 队头出，从单链表的头拿出一个元素
     *
     * @return
     * @throws ExceptionQueueEmpty
     */
    @Override
    public E dequeue() throws ExceptionQueueEmpty {
        if (isEmpty()) {
            throw new ExceptionQueueEmpty("空了");
        }
        E elem = head.getElem();
        head = head.getNext();
        size--;
        if (size == 0) {
            //若队列已空，须将末节点引用置空
            tail = null;
        }
        return elem;
    }

    @Override
    public void traversal() {
        Node<E> p = head;
        while (null != p) {
            System.out.println(p.getElem());
            p = p.getNext();
        }
    }
}
