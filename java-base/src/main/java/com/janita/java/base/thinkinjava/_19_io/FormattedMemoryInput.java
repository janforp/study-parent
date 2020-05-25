package com.janita.java.base.thinkinjava._19_io;

import java.io.BufferedInputStream;
import java.io.ByteArrayInputStream;
import java.io.DataInputStream;
import java.io.EOFException;
import java.io.FileInputStream;
import java.io.IOException;

import static com.janita.java.base.thinkinjava._19_io.BufferedInputFile.FILE_NAME;

/**
 * 类说明：FormattedMemoryInput
 *
 * @author zhucj
 * @since 20200528
 */
public class FormattedMemoryInput {

    public static void main(String[] args) throws IOException {
        try {
            String readStr = BufferedInputFile.read(FILE_NAME);
            byte[] readStrBytes = readStr.getBytes();
            ByteArrayInputStream byteArrayInputStream = new ByteArrayInputStream(readStrBytes);
            DataInputStream in = new DataInputStream(byteArrayInputStream);




            int available = in.available();
            System.out.println(available);
            while (true) {
                System.out.print((char) in.readByte());
            }
        } catch (EOFException e) {
            System.err.println("End of stream");
        }
    }
}

class TestEOF {

    public static void main(String[] args) throws IOException {
        DataInputStream in = new DataInputStream(
                new BufferedInputStream(
                        new FileInputStream(FILE_NAME)));


        while (in.available() != 0) {
            System.out.print((char) in.readByte());
        }
    }
}
