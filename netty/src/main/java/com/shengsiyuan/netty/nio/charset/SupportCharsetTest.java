package com.shengsiyuan.netty.nio.charset;

import java.nio.charset.Charset;
import java.util.SortedMap;

/**
 * 类说明：SupportCharsetTest
 *
 * @author zhucj
 * @since 20200423
 */
public class SupportCharsetTest {

    public static void main(String[] args) {
        SortedMap<String, Charset> map = Charset.availableCharsets();
        System.out.println(map.size());
        map.forEach((key, value) -> System.out.println(key + ", " + value));
    }
}
