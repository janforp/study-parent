package com.janita.java.base.thinkinjava._18_io;

import java.io.BufferedInputStream;
import java.io.BufferedOutputStream;
import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.PrintStream;

/**
 * 类说明：Redirecting
 *
 * @author zhucj
 * @since 20200528
 */
public class Redirecting {

    public static final String file = "/Users/janita/code/studyCode/sp/java-base/src/main/java/com/janita/java/base/thinkinjava/_19_io/Redirecting.java";

    public static void main(String[] args) throws IOException {

        PrintStream console = System.out;
        FileInputStream fileInputStream = new FileInputStream(file);
        BufferedInputStream in = new BufferedInputStream(fileInputStream);

        FileOutputStream fileOutputStream = new FileOutputStream("test.out");
        BufferedOutputStream bufferedOutputStream = new BufferedOutputStream(fileOutputStream);
        PrintStream out = new PrintStream(bufferedOutputStream);

        System.setIn(in);
        System.setOut(out);
        System.setErr(out);

        InputStream inputStream = System.in;
        InputStreamReader inputStreamReader = new InputStreamReader(inputStream);
        BufferedReader br = new BufferedReader(inputStreamReader);

        String s;
        while ((s = br.readLine()) != null) {
            System.out.println(s);
        }
        out.close();
        System.setOut(console);
    }
}

class Redirecting2 {

    public static void main(String[] args) throws IOException {
        PrintStream console = System.out;
        BufferedInputStream in = new BufferedInputStream(new FileInputStream(Redirecting.file));

        PrintStream out = new PrintStream(new BufferedOutputStream(new FileOutputStream("test.out")));
        System.setIn(in);
        System.setOut(out);
        System.setErr(out);
        BufferedReader br = new BufferedReader(
                new InputStreamReader(System.in));
        String s;
        while ((s = br.readLine()) != null) {
            System.out.println(s);
        }
        out.close(); // Remember this!
        System.setOut(console);
    }
}
