package com.janita.java.base;

import java.util.ArrayList;
import java.util.List;

/**
 * 类说明：Test
 *
 * @author zhucj
 * @since 20200423
 */
public class Test {

    public static void main(String[] args) {
        System.out.println(1>>4);
        weiyi();
    }

    private static void weiyi() {

        List<Integer> sizeTable = new ArrayList<Integer>();
//        for (int i = 16; i < 512; i += 16) {
//            sizeTable.add(i);
//        }

        for (int i = 512; i > 0; i <<= 1) {
            sizeTable.add(i);
        }

        System.out.println(sizeTable);
    }
}
