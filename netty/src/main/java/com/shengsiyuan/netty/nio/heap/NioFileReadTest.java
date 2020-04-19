package com.shengsiyuan.netty.nio.heap;

import java.io.FileInputStream;
import java.io.IOException;
import java.nio.ByteBuffer;
import java.nio.channels.FileChannel;

/**
 * 类说明：NioFileTest
 *
 * @author zhucj
 * @since 20200423
 */
public class NioFileReadTest {

    public static void main(String[] args) throws IOException {
        FileInputStream inputStream = new FileInputStream("/Users/janita/code/studyCode/sp/netty/src/main/java/com/shengsiyuan/netty/nio/files/NioTest2.txt");
        FileChannel channel = inputStream.getChannel();

        //512字节
        ByteBuffer byteBuffer = ByteBuffer.allocate(512);

        //读到buffer中，也就是写入buffer
        channel.read(byteBuffer);

        //操作反转
        byteBuffer.flip();
        while (byteBuffer.remaining() > 0) {
            //从buffer读取
            byte b = byteBuffer.get();
            System.out.println("Character: " + (char)b);
        }
        channel.close();
    }
}
