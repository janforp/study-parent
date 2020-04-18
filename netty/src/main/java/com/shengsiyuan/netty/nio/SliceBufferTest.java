package com.shengsiyuan.netty.nio;

import java.nio.ByteBuffer;

/**
 * 类说明：SliceBufferTest
 *
 * @author zhucj
 * @since 20200423
 */
public class SliceBufferTest {

    public static void main(String[] args) {
        testSlice();
    }

    /**
     * Creates a new byte buffer whose content
     * is a shared subsequence of this buffer's content.
     */
    private static void testSlice() {

        ByteBuffer buffer = ByteBuffer.allocate(10);
        for (int i = 0; i < buffer.capacity(); i++) {
            buffer.put((byte) i);
        }

        buffer.position(2).limit(6);

        //返回 [2,6)
        ByteBuffer slice = buffer.slice();
        for (int i = 0; i < slice.capacity(); i++) {
            byte b = slice.get(i);
            b *= 2;
            slice.put(i, b);
        }

        buffer.position(0).limit(buffer.capacity());

        while (buffer.hasRemaining()) {
            System.out.println(buffer.get());
        }
    }
}
