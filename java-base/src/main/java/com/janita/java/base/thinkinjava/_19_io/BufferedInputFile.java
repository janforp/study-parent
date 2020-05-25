package com.janita.java.base.thinkinjava._19_io;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.io.StringReader;

import static com.janita.java.base.thinkinjava._19_io.BufferedInputFile.FILE_NAME;

/**
 * 类说明：BufferedInputFile
 *
 * @author zhucj
 * @since 20200528
 */
public class BufferedInputFile {

    public static final String FILE_NAME = "/Users/janita/code/studyCode/sp/java-base/src/main/java/com/janita/java/base/thinkinjava/_19_io/BufferedInputFile.java";

    // Throw exceptions to console:
    public static String read(String filename) throws IOException {
        // Reading input by lines:
        FileReader fileReader = new FileReader(filename);
        BufferedReader in = new BufferedReader(fileReader);
        String s;
        StringBuilder sb = new StringBuilder();
        while ((s = in.readLine()) != null) {
            sb.append(s).append("\n");
        }
        in.close();
        return sb.toString();
    }

    public static void main(String[] args) throws IOException {
        System.out.print(read(FILE_NAME));
    }
}

class MemoryInput {

    public static void main(String[] args) throws IOException {
        StringReader in = new StringReader(BufferedInputFile.read(FILE_NAME));
        int c;
        while ((c = in.read()) != -1) {
            System.out.print((char) c);
        }
    }
}

class MyTest {

    public static void main(String[] args) throws IOException {
        StringReader reader = new StringReader("我来了");
        int c;
        while ((c = reader.read()) != -1) {
            System.out.print((char) c);
        }
    }
}