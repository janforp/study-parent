package com.janita.java.ssy.jdk8;

import java.util.Arrays;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;

/**
 * _26_Compartor
 *
 * @author zhucj
 * @since 20200917
 */
public class _26_27_Comparator {

    public static void main(String[] args) {
        List<String> list = Arrays.asList("nihao", "hello", "world", "welcome");
        //升序
        Collections.sort(list);
        System.out.println(list);

        //按字符串长度升序排序
        list.sort(Comparator.comparingInt(String::length));
        System.out.println(list);

        //按字符串长度降序排序
        Collections.sort(list, (o1, o2) -> o2.length() - o1.length());
        System.out.println(list);

        //按字符串长度降序排序
        list.sort(Comparator.comparingInt(String::length).reversed());
        System.out.println(list);

        //编译器无法推断类型？
        Collections.sort(list, Comparator.comparingInt(item -> item.length()));
        //Collections.sort(list,Comparator.comparingInt(item -> item.length()).reversed());
        Collections.sort(list, Comparator.comparingInt((String item) -> item.length()).reversed());
        System.out.println(list);

        System.out.println(compare());
        System.out.println(compare2());
        System.out.println(compare3());
        System.out.println(compare4());
        System.out.println(compare5());
        System.out.println(compare6());
    }

    private static List<String> compare() {
        List<String> list = Arrays.asList("nihao", "hello", "world", "welcome");
        //先按长度，再按asc码排序
        Collections.sort(list,
                Comparator.comparingInt(String::length)
                        .thenComparing(String.CASE_INSENSITIVE_ORDER));
        return list;
    }

    private static List<String> compare2() {
        List<String> list = Arrays.asList("nihao", "hello", "world", "welcome");
        //先按长度，再按asc码排序
        Collections.sort(list,
                Comparator.comparingInt(String::length)
                        .thenComparing((item1, item2) -> item1.toLowerCase().compareTo(item2.toLowerCase())));
        return list;
    }

    private static List<String> compare3() {
        List<String> list = Arrays.asList("nihao", "hello", "world", "welcome");
        //先按长度，再按asc码排序
        Collections.sort(list,
                Comparator.comparingInt(String::length)
                        .thenComparing(Comparator.comparing(String::toLowerCase)));
        return list;
    }

    private static List<String> compare4() {
        List<String> list = Arrays.asList("nihao", "hello", "world", "welcome");
        //先按长度，再按asc码降序
        Collections.sort(list,
                Comparator.comparingInt(String::length)
                        .thenComparing(Comparator.comparing(String::toLowerCase, Comparator.reverseOrder())));
        return list;
    }

    private static List<String> compare5() {
        List<String> list = Arrays.asList("nihao", "hello", "world", "welcome");
        Collections.sort(list,
                Comparator.comparingInt(String::length)
                        .reversed().thenComparing(Comparator.comparing(String::toLowerCase,
                        Comparator.reverseOrder())));
        return list;
    }

    private static List<String> compare6() {
        List<String> list = Arrays.asList("nihao", "hello", "world", "welcome");
        Collections.sort(list,
                Comparator.comparingInt(String::length)
                        .reversed().thenComparing(Comparator.comparing(String::toLowerCase,
                        Comparator.reverseOrder()))
                        //这个比较器没发挥作用，因为前面的比较器的结果没有=0的比较结果
                        .thenComparing(Comparator.reverseOrder()));
        return list;
    }
}
