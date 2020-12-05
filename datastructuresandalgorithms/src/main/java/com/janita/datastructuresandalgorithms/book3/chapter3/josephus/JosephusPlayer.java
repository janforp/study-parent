package com.janita.datastructuresandalgorithms.book3.chapter3.josephus;

/**
 * JosephusPlayer
 *
 * @author zhucj
 * @since 20201224
 */
public class JosephusPlayer {

}

/**
 * 使用单向循环链表解决约瑟夫问题
 *
 * @author liyanan
 * @date 2020/1/2 17:05
 */
class JosephProblem {

    private CircleLinkedList circleLinkedList = new CircleLinkedList();

    /**
     * @param total 总人数
     * @return 链表头结点
     */
    public Node createCircle(int total) {
        for (int i = 1; i <= total; i++) {
            Node node = new Node(i);
            circleLinkedList.insert(node);
        }
        return circleLinkedList.getHead();
    }

    /**
     * 处决
     *
     * @param start 开始计数的人，如果只有一个， start = 1
     * @param num 每次跳过的人树
     * @return
     */
    public Node run(int start, int num, int total) {
        createCircle(total);
        // 找到开始报数的人
        Node startNode = circleLinkedList.find(start);
        Node temp = startNode;
        while (circleLinkedList.getLength() > 1) {
            // 找到被删除人的前一个结点
            for (int i = 1; i < num - 1; i++) {
                temp = temp.next;
            }
            // 处决要删除的人
            Node del = circleLinkedList.del(temp);
            // 下一个人开始报数
            temp = temp.next;
            System.out.print(del.data + " ");
        }
        System.out.println();
        // 返回最后幸存的人
        return circleLinkedList.getHead();
    }

    public static void main(String[] args) {
        JosephProblem joseph = new JosephProblem();
        Node node = joseph.run(1, 5, 9);
        System.out.println("最后幸存的人:" + node.data);
    }
}

