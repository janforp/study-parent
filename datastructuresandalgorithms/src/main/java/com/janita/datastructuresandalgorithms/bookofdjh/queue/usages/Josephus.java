package com.janita.datastructuresandalgorithms.bookofdjh.queue.usages;

import com.janita.datastructuresandalgorithms.bookofdjh.queue.LoopQueueArray;
import com.janita.datastructuresandalgorithms.bookofdjh.queue.Queue;

/**
 * Josephus
 *
 * @author zhucj
 * @since 20201126
 */
public class Josephus {

    //利用队列结构模拟Josophus环
    public static String josephus(Queue<String> Q, int k) {
        if (Q.isEmpty()) {
            return null;
        }
        while (Q.getSize() > 1) {//不断迭代
            Q.traversal();//显示当前的环
            for (int i = 0; i < k; i++)//将山芋向前传递k次
            {
                Q.enqueue(Q.dequeue());
            }
            Object e = Q.dequeue();//拿着山芋的孩子退出
            System.out.println("\n\t" + e + "退出");
        }
        return Q.dequeue();//最后剩下的那个孩子 }
    }

    //将一组对象组织为一个队列
    public static Queue<String> buildQueue(String[] a) {
        Queue<String> Q = new LoopQueueArray<>();
        for (String s : a) {
            Q.enqueue(s);
        }
        return Q;
    }

    //测试用main方法
    public static void main(String[] args) {
        String[] kid = { "Alice", "Bob", "Cindy", "Doug", "Ed",
                "Fred", "Gene", "Hope", "Irene", "Jack",
                "Kim", "Lance", "Mike", "Nancy", "Ollie" };
        System.out.println("最终的幸运者是" + josephus(buildQueue(kid), 5));
    }
}
