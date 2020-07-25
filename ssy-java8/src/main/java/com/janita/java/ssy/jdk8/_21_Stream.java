package com.janita.java.ssy.jdk8;

import java.util.Arrays;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

/**
 * _20_Stream
 *
 * @author zhucj
 * @since 20200723
 */
public class _21_Stream {

    public static void main(String[] args) {
        List<String> greetList = Arrays.asList("Hi", "Hello", "你好");
        List<String> nameList = Arrays.asList("zhangsan", "lisi", "wangwu", "zhaoliu");

        test2(greetList, nameList);
    }

    private static void test2(List<String> greetList, List<String> nameList) {
        List<String> collect = greetList.stream().flatMap(greet -> nameList.stream().map(name -> greet + ":" + name)).collect(Collectors.toList());
        System.out.println(collect);

    }
}

class StreamTest13 {

    public static void main(String[] args) {

        Student3 zhangsan = new Student3("zhangsan", 100, 20);
        Student3 lisi = new Student3("lisi", 90, 20);
        Student3 wangwu = new Student3("wangwu", 120, 30);
        Student3 wangwu2 = new Student3("wangwu", 80, 40);
        List<Student3> student3List = Arrays.asList(zhangsan, lisi, wangwu, wangwu2);
        Map<String, List<Student3>> collect = student3List.stream().collect(Collectors.groupingBy(Student3::getName));
        System.out.println(collect);

        //统计每个名字的个数
        Map<String, Long> collect1 = student3List.stream().collect(Collectors.groupingBy(Student3::getName, Collectors.counting()));
        System.out.println(collect1);

        //统计每个名字的分数的平均值
        Map<String, Double> collect2 = student3List.stream().collect(Collectors.groupingBy(Student3::getName, Collectors.averagingDouble(Student3::getScore)));
        System.out.println(collect2);

        //分区
        Map<Boolean, List<Student3>> collect3 = student3List.stream().collect(Collectors.partitioningBy(student3 -> student3.getScore() >= 90));
        System.out.println(collect3);
    }
}
