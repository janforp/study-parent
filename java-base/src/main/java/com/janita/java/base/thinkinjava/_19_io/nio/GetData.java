package com.janita.java.base.thinkinjava._19_io.nio;

import java.nio.ByteBuffer;
import java.nio.IntBuffer;

import static com.janita.java.base.thinkinjava.util.Print.print;
import static com.janita.java.base.thinkinjava.util.Print.printnb;

/**
 * 类说明：GetData
 *
 * @author zhucj
 * @since 20200528
 */
public class GetData {

    private static final int BSIZE = 1024;

    public static void main(String[] args) {
        ByteBuffer bb = ByteBuffer.allocate(BSIZE);
        // Allocation automatically zeroes the ByteBuffer:
        int i = 0;
        while (i++ < bb.limit()) {
            if (bb.get() != 0) {
                print("nonzero");
            }
        }
        print("i = " + i);
        bb.rewind();
        // Store and read a char array:
        bb.asCharBuffer().put("Howdy!");
        char c;
        while ((c = bb.getChar()) != 0) {
            printnb(c + " ");
        }
        print();
        bb.rewind();
        // Store and read a short:
        bb.asShortBuffer().put((short) 471142);
        print(bb.getShort());
        bb.rewind();
        // Store and read an int:
        bb.asIntBuffer().put(99471142);
        print(bb.getInt());
        bb.rewind();
        // Store and read a long:
        bb.asLongBuffer().put(99471142);
        print(bb.getLong());
        bb.rewind();
        // Store and read a float:
        bb.asFloatBuffer().put(99471142);
        print(bb.getFloat());
        bb.rewind();
        // Store and read a double:
        bb.asDoubleBuffer().put(99471142);
        print(bb.getDouble());
        bb.rewind();
    }
} /* Output:
i = 1025
H o w d y !
12390
99471142
99471142
9.9471144E7
9.9471142E7
*///:~

class IntBufferDemo {

    private static final int BSIZE = 1024;

    public static void main(String[] args) {
        ByteBuffer byteBuffer = ByteBuffer.allocate(BSIZE);
        IntBuffer intBuffer = byteBuffer.asIntBuffer();
        intBuffer.put(new int[] { 11, 42, 47, 99, 143, 811, 1016 });
        System.out.println(intBuffer.get(3));
        intBuffer.put(3, 1811);
        intBuffer.flip();
        while (intBuffer.hasRemaining()) {
            int i = intBuffer.get();
            System.out.println(i);
        }
    }
}
