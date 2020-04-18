package com.shengsiyuan.netty.nio;

import java.nio.IntBuffer;
import java.security.SecureRandom;

/**
 * 类说明：NioTest1
 *
 * @author zhucj
 * @since 20200423
 */
public class NioIntBufferTest {

    public static void main(String[] args) {
        IntBuffer buffer = IntBuffer.allocate(10);
        for (int i = 0; i < buffer.capacity(); i++) {
            int randomNumber = new SecureRandom().nextInt(20);
            System.out.println("第" + i + "个放进去的 :" + randomNumber);
            //写入
            buffer.put(randomNumber);
        }
        System.out.println("-------------------");
        //翻转
        buffer.flip();

        //读取
        while (buffer.hasRemaining()) {
            System.out.println(buffer.get());
        }
    }
}
