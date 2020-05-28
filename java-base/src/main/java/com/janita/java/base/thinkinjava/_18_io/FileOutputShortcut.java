package com.janita.java.base.thinkinjava._18_io;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.PrintWriter;
import java.io.StringReader;

/**
 * 类说明：FileOutputShortcut
 *
 * @author zhucj
 * @since 20200528
 */
public class FileOutputShortcut {

    static String file = "FileOutputShortcut.out";

    public static void main(String[] args)
            throws IOException {
        BufferedReader in = new BufferedReader(
                new StringReader(
                        BufferedInputFile.read("FileOutputShortcut.java")));
        // Here's the shortcut:
        PrintWriter out = new PrintWriter(file);
        int lineCount = 1;
        String s;
        while ((s = in.readLine()) != null) {
            out.println(lineCount++ + ": " + s);
        }
        out.close();
        // Show the stored file:
        System.out.println(BufferedInputFile.read(file));
    }
}