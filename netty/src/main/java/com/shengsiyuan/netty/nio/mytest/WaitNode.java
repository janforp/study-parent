package com.shengsiyuan.netty.nio.mytest;

/**
 * 类说明：WaitNode
 *
 * @author zhucj
 * @since 20200423
 */
public class WaitNode {

    volatile Thread thread;

    volatile WaitNode next;

    WaitNode() {
        thread = Thread.currentThread();
    }

    public static void main(String[] args) {
        int count = 0;
        retry:
        for (int i = 0; i < 2; i++){
            for (int j = 0; j < 5; j++){
                count++;
                if (count == 3){
                    continue retry;
                }
                System.out.print(count+" ");
            }
        }
    }
}
