package com.janita.java.base.thinkinjava._18_io.nio;

import java.io.RandomAccessFile;
import java.nio.ByteBuffer;
import java.nio.CharBuffer;
import java.nio.MappedByteBuffer;
import java.nio.channels.FileChannel;

import static com.janita.java.base.thinkinjava.util.Print.print;
import static com.janita.java.base.thinkinjava.util.Print.printnb;

/**
 * UsingBuffers
 *
 * @author zhucj
 * @since 20200528
 */
public class UsingBuffers {

    private static void symmetricScramble(CharBuffer buffer) {
        while (buffer.hasRemaining()) {
            buffer.mark();
            char c1 = buffer.get();
            char c2 = buffer.get();
            buffer.reset();
            buffer.put(c2).put(c1);
        }
    }

    public static void main(String[] args) {
        char[] data = "UsingBuffers".toCharArray();
        ByteBuffer bb = ByteBuffer.allocate(data.length * 2);
        CharBuffer cb = bb.asCharBuffer();
        cb.put(data);
        print(cb.rewind());
        symmetricScramble(cb);
        print(cb.rewind());
        symmetricScramble(cb);
        print(cb.rewind());
    }
} /* Output:
UsingBuffers
sUniBgfuefsr
UsingBuffers
*///:~

class LargeMappedFiles {

    static int length = 0x8FFFFFF; // 128 MB

    public static void main(String[] args) throws Exception {
        RandomAccessFile randomAccessFile = new RandomAccessFile("test.dat", "rw");
        FileChannel channel = randomAccessFile.getChannel();

        MappedByteBuffer out = channel.map(FileChannel.MapMode.READ_WRITE, 0, length);

        for (int i = 0; i < length; i++) {
            out.put((byte) 'x');
        }
        print("Finished writing");
        for (int i = length / 2; i < length / 2 + 6; i++) {
            printnb((char) out.get(i));
        }
    }
}