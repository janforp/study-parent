package com.shengsiyuan.netty.nio.direct;

import java.io.IOException;
import java.io.RandomAccessFile;
import java.nio.channels.FileLock;

/**
 * 类说明：FileLockTest
 *
 * @author zhucj
 * @since 20200423
 */
public class FileLockTest {

    public static void main(String[] args) throws IOException {
        testOne();
    }

    private static void testOne() throws IOException {
        RandomAccessFile randomAccessFile = new RandomAccessFile("/Users/janita/code/studyCode/sp/netty/src/main/java/com/shengsiyuan/netty/nio/direct/files/fileLock.txt", "rw");
        FileLock fileLock = randomAccessFile
                .getChannel().lock(3, 6, true);

        System.out.println("valid：" + fileLock.isValid());
        System.out.println("lock type：" + fileLock.isShared());

        fileLock.release();
        randomAccessFile.close();
    }
}
