package com.shengsiyuan.netty.nio.heap;

import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.nio.ByteBuffer;
import java.nio.channels.FileChannel;

/**
 * 类说明：NioFileReadWriteTest
 *
 * @author zhucj
 * @since 20200423
 */
public class NioFileReadWriteTest {

    public static void main(String[] args) throws IOException {
        testOne();

        System.out.println("_____________");

        testTwo();
    }

    private static void testTwo() throws IOException {
        FileInputStream inputStream = new FileInputStream("/Users/janita/code/studyCode/sp/netty/src/main/java/com/shengsiyuan/netty/nio/files/input.txt");
        FileOutputStream outputStream = new FileOutputStream("/Users/janita/code/studyCode/sp/netty/src/main/java/com/shengsiyuan/netty/nio/files/output2.txt");

        FileChannel inputChannel = inputStream.getChannel();
        FileChannel outputChannel = outputStream.getChannel();

        ByteBuffer byteBuffer = ByteBuffer.allocate(5);
        while (true) {

            //如果注视掉该行？就不会重置，每次write的时候就会有问题
            //public Buffer clear() {
            //        position = 0;
            //        limit = capacity;
            //        mark = -1;
            //        return this;
            //    }
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

    private static void testOne() throws IOException {
        FileInputStream inputStream = new FileInputStream("/Users/janita/code/studyCode/sp/netty/src/main/java/com/shengsiyuan/netty/nio/files/input.txt");
        FileOutputStream outputStream = new FileOutputStream("/Users/janita/code/studyCode/sp/netty/src/main/java/com/shengsiyuan/netty/nio/files/output.txt");

        FileChannel inputChannel = inputStream.getChannel();
        FileChannel outputChannel = outputStream.getChannel();

        ByteBuffer byteBuffer = ByteBuffer.allocate(1024);
        while (true) {
            byteBuffer.clear();

            int read = inputChannel.read(byteBuffer);
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
