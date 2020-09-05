package com.janita.java.ssy.jdk8;

import java.util.Comparator;
import java.util.IntSummaryStatistics;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.stream.Collectors;

/**
 * _25_StreamTest1
 *
 * @author zhucj
 * @since 20200723
 */
public class _25_StreamTest1 {

    public static void main(String[] args) {
        List<_22_Student> studentList = _22_Student.getList();
        //最小值
        studentList.stream().min(Comparator.comparingInt(_22_Student::getScore))
                .ifPresent(System.out::println);

        //最大
        studentList.stream()
                .collect(Collectors.maxBy(Comparator.comparingInt(_22_Student::getScore)))
                .ifPresent(System.out::println);
        //平均值
        Double collect = studentList.stream().collect(Collectors.averagingInt(_22_Student::getScore));
        System.out.println(collect);

        //总和
        int sum = studentList.stream().collect(Collectors.summingInt(_22_Student::getScore));
        System.out.println(sum);
        sum = studentList.stream().mapToInt(_22_Student::getScore).sum();
        System.out.println(sum);

        //摘要
        IntSummaryStatistics statistics = studentList.stream()
                .collect(Collectors.summarizingInt(_22_Student::getScore));
        System.out.println(statistics);

        //名字拼接
        String collect1 = studentList.stream()
                .map(_22_Student::getName)
                .collect(Collectors.joining(","));
        System.out.println(collect1);

        collect1 = studentList.stream()
                .map(_22_Student::getName)
                .collect(Collectors.joining(",", "begin       ", "       end"));
        System.out.println(collect1);
    }
}

class GroupingByTest {

    public static void main(String[] args) {

        List<_22_Student> studentList = _22_Student.getList();
        Map<Integer, Map<String, List<_22_Student>>> mapMap = studentList
                .stream()
                //先根据分，再根据名称分组
                .collect(Collectors.groupingBy(_22_Student::getScore, Collectors.groupingBy(_22_Student::getName)));
        System.out.println(mapMap);

        //统计男同学跟女同学的总分
        Map<Integer, Integer> genderStore = studentList.stream()
                .collect(Collectors.groupingBy(_22_Student::getGender, Collectors.summingInt(_22_Student::getScore)));
        System.out.println(genderStore);
    }
}

class PartitionByTest {

    public static void main(String[] args) {

        List<_22_Student> studentList = _22_Student.getList();
        Map<Boolean, List<_22_Student>> collect = studentList.stream()
                .collect(Collectors.partitioningBy(student -> student.getScore() > 80));

        Map<Boolean, Map<Boolean, List<_22_Student>>> collect1 = studentList.stream()
                .collect(Collectors.partitioningBy(student -> student.getScore() > 80,
                        Collectors.partitioningBy(student -> student.getScore() > 90)));
        System.out.println(collect1);

        //分数大于80的人数，以及小于80的人数
        Map<Boolean, Long> map = studentList.stream()
                .collect(Collectors.partitioningBy(student -> student.getScore() > 80, Collectors.counting()));
        System.out.println(map);

        Map<String, _22_Student> studentMap = studentList.stream()
                .collect(Collectors.groupingBy(_22_Student::getName,
                        Collectors.collectingAndThen(Collectors.minBy(Comparator.comparing(_22_Student::getScore)),
                                Optional::get)));
        System.out.println(studentMap);
    }
}
