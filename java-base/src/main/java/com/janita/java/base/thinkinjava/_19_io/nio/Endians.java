package com.janita.java.base.thinkinjava._19_io.nio;

import java.nio.ByteBuffer;
import java.nio.ByteOrder;
import java.util.Arrays;

import static com.janita.java.base.thinkinjava.util.Print.print;

/**
 * 类说明：Endians
 *
 * @author zhucj
 * @since 20200528
 */
public class Endians {

    public static void main(String[] args) {
        ByteBuffer byteBuffer = ByteBuffer.wrap(new byte[12]);
        byteBuffer.asCharBuffer().put("abcdef");
        print(Arrays.toString(byteBuffer.array()));
        byteBuffer.rewind();
        byteBuffer.order(ByteOrder.BIG_ENDIAN);
        byteBuffer.asCharBuffer().put("abcdef");
        print(Arrays.toString(byteBuffer.array()));
        byteBuffer.rewind();
        byteBuffer.order(ByteOrder.LITTLE_ENDIAN);
        byteBuffer.asCharBuffer().put("abcdef");
        print(Arrays.toString(byteBuffer.array()));
    }

}
