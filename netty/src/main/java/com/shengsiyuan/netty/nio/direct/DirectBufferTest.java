package com.shengsiyuan.netty.nio.direct;

import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.nio.ByteBuffer;
import java.nio.channels.FileChannel;

/**
 * 类说明：DirectBufferTest
 * 由c函数 malloc 分配内存
 *
 * 实现了 零拷贝
 *
 * @author zhucj
 * @since 20200423
 */
public class DirectBufferTest {

    public static void main(String[] args) throws IOException {
        testTwo();
    }

    private static void testTwo() throws IOException {
        FileInputStream inputStream = new FileInputStream("/Users/janita/code/studyCode/sp/netty/src/main/java/com/shengsiyuan/netty/nio/direct/files/input.txt");
        FileOutputStream outputStream = new FileOutputStream("/Users/janita/code/studyCode/sp/netty/src/main/java/com/shengsiyuan/netty/nio/direct/files/output.txt");

        FileChannel inputChannel = inputStream.getChannel();
        FileChannel outputChannel = outputStream.getChannel();
        //direct
        ByteBuffer byteBuffer = ByteBuffer.allocateDirect(512);
        while (true) {
            byteBuffer.clear();

            int read = inputChannel.read(byteBuffer);

            System.out.println("读到的字节数= " + read);
            if (-1 == read) {
                break;
            }

            byteBuffer.flip();

            outputChannel.write(byteBuffer);
        }

        inputChannel.close();
        outputChannel.close();
    }
}
