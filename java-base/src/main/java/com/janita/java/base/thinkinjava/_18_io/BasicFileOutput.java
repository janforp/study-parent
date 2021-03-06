package com.janita.java.base.thinkinjava._18_io;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.io.StringReader;

/**
 * 类说明：BasicFileOutput
 *
 * @author zhucj
 * @since 20200528
 */
public class BasicFileOutput {

    static String file = "/Users/janita/code/studyCode/sp/java-base/src/main/java/com/janita/java/base/thinkinjava/_19_io/BasicFileOutput.java";

    public static void main(String[] args) throws IOException {
        BufferedReader in
                = new BufferedReader(new StringReader(BufferedInputFile.read(file)));
        PrintWriter out = new PrintWriter(new BufferedWriter(new FileWriter(file)));
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
