package com.janita.java.base.thinkinjava._18_io.file;

import com.janita.java.base.thinkinjava.util.PPrint;

import java.io.File;

import static com.janita.java.base.thinkinjava.util.Print.print;

/**
 * 类说明：DirectoryDemo
 *
 * @author zhucj
 * @since 20200528
 */

public class DirectoryDemo {

    public static void main(String[] args) {
        // All directories:
        PPrint.pprint(Directory.walk(".").dirs);
        // All files beginning with 'T'
        for (File file : Directory.local(".", "T.*")) {
            print(file);
        }
        print("----------------------");
        // All Java files beginning with 'T':
        for (File file : Directory.walk(".", "T.*\\.java")) {
            print(file);
        }
        print("======================");
        // Class files containing "Z" or "z":
        for (File file : Directory.walk(".", ".*[Zz].*\\.class")) {
            print(file);
        }
    }
} /* Output: (Sample)
[.\xfiles]
.\TestEOF.class
.\TestEOF.java
.\TransferTo.class
.\TransferTo.java
----------------------
.\TestEOF.java
.\TransferTo.java
.\xfiles\ThawAlien.java
======================
.\FreezeAlien.class
.\GZIPcompress.class
.\ZipCompress.class
*///:~

