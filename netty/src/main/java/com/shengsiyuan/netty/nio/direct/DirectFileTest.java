package com.shengsiyuan.netty.nio.direct;

import java.io.IOException;
import java.io.RandomAccessFile;
import java.nio.ByteBuffer;
import java.nio.MappedByteBuffer;
import java.nio.channels.FileChannel;
import java.util.ArrayList;
import java.util.List;

/**
 * 类说明：DirectFileTest
 *
 * @author zhucj
 * @since 20200423
 */
public class DirectFileTest {

    public static void main(String[] args) throws IOException {
        testMap();
    }

    private static void testMap() throws IOException {
        RandomAccessFile randomAccessFile
                = new RandomAccessFile
                ("/Users/janita/code/studyCode/sp/netty/src/main/java/com/shengsiyuan/netty/nio/direct/files/rw2.txt", "rw");
        FileChannel fileChannel = randomAccessFile.getChannel();
        //内存映射文件
        MappedByteBuffer mappedByteBuffer = fileChannel.map(FileChannel.MapMode.READ_WRITE, 0, 5);
        mappedByteBuffer.put(0, (byte) 'a');
        mappedByteBuffer.put(3, (byte) 'b');

        randomAccessFile.close();
    }

    private static void testOne() throws IOException {
        //TODO
        RandomAccessFile randomAccessFile = new RandomAccessFile("/Users/janita/code/studyCode/sp/netty/src/main/java/com/shengsiyuan/netty/nio/direct/files/rw2.txt", "rw");
        FileChannel fileChannel = randomAccessFile.getChannel();
        ByteBuffer writeBuffer = ByteBuffer.allocate("hhhh".getBytes().length);
        writeBuffer.put("hhhh".getBytes());
        fileChannel.write(writeBuffer, 0);
        fileChannel.force(true);

        ByteBuffer readBuffer = ByteBuffer.allocate(100);
        int read = fileChannel.read(readBuffer, 0);

        List<Byte> list = new ArrayList<>();
        int i = 0;
        while (readBuffer.hasRemaining()) {
            byte b = readBuffer.get();
            list.add(b);
        }
    }
}
