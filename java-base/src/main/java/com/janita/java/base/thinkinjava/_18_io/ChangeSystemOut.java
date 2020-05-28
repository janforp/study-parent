package com.janita.java.base.thinkinjava._18_io;

import java.io.PrintStream;
import java.io.PrintWriter;

/**
 * 类说明：ChangeSystemOut
 *
 * @author zhucj
 * @since 20200528
 */
public class ChangeSystemOut {

    public static void main(String[] args) {

        PrintStream printStream = System.out;
        PrintWriter printWriter = new PrintWriter(printStream, true);
        printStream.println("hello world");
    }
}
