package com.shengsiyuan.netty.nio.charset;

import java.io.IOException;
import java.io.RandomAccessFile;
import java.nio.ByteBuffer;
import java.nio.CharBuffer;
import java.nio.MappedByteBuffer;
import java.nio.channels.FileChannel;
import java.nio.charset.Charset;
import java.nio.charset.StandardCharsets;

/**
 * 类说明：FileContentCopyTest
 *
 * @author zhucj
 * @since 20200423
 */
public class FileContentCopyTest {

    public static void main(String[] args) throws IOException {
        RandomAccessFile readFile = new RandomAccessFile("/Users/janita/code/studyCode/sp/netty/src/main/java/com/shengsiyuan/netty/nio/charset/files/input.txt", "r");
        RandomAccessFile writeFile = new RandomAccessFile("/Users/janita/code/studyCode/sp/netty/src/main/java/com/shengsiyuan/netty/nio/charset/files/output.txt", "rw");

        FileChannel readChannel = readFile.getChannel();
        FileChannel writeChannel = writeFile.getChannel();


        MappedByteBuffer mappedByteBuffer = readChannel.map(FileChannel.MapMode.READ_ONLY, 0, readFile.length());

        Charset charset = StandardCharsets.UTF_8;
        CharBuffer charBuffer = charset.decode(mappedByteBuffer);
        ByteBuffer writeBuffer = charset.encode(charBuffer);
        writeChannel.write(writeBuffer);
        readFile.close();
        writeFile.close();
    }

}
