package com.shengsiyuan.netty.nio.heap;

import java.nio.ByteBuffer;
import java.nio.ReadOnlyBufferException;

/**
 * 类说明：只读
 *
 * @author zhucj
 * @since 20200423
 */
public class ReadOnlyBufferTest {

    public static void main(String[] args) {
        testReadOnly();
    }

    private static void testReadOnly() {
        ByteBuffer buffer = ByteBuffer.allocate(10);
        System.out.println(buffer.getClass());
        for (int i = 0; i < buffer.capacity(); i++) {
            buffer.put((byte) i);
        }

        //参数传递的时候就有用
        ByteBuffer readOnlyBuffer = buffer.asReadOnlyBuffer();
        ReadOnlyBufferException exception = null;
        try {
            readOnlyBuffer.put(1, (byte) 3);
        } catch (ReadOnlyBufferException e) {
            exception = e;
        }
        assert exception != null;
        System.out.println(readOnlyBuffer.getClass());
    }
}
