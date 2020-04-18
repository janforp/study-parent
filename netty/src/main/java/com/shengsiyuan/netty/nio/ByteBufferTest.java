package com.shengsiyuan.netty.nio;

import java.nio.ByteBuffer;

/**
 * 类说明：ByteBufferTest
 *
 * @author zhucj
 * @since 20200423
 */
public class ByteBufferTest {

    public static void main(String[] args) {
        testOne();
    }

    /**
     * 存入顺序需要与读取顺序一样
     * 类型化的 get,put
     * vm,./  =0p
     */
    private static void testOne() {
        ByteBuffer buffer = ByteBuffer.allocate(64);

        buffer.putInt(15);
        buffer.putLong(5000000L);
        buffer.putDouble(14.123456);
        buffer.putChar('你');
        buffer.putShort((short)2);
        buffer.putChar('我');

        buffer.flip();

        System.out.println(buffer.getInt());
        System.out.println(buffer.getLong());
        System.out.println(buffer.getDouble());
        System.out.println(buffer.getChar());
        System.out.println(buffer.getShort());
        System.out.println(buffer.getChar());
    }
}
