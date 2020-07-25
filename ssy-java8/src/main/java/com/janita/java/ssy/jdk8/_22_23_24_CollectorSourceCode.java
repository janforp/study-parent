package com.janita.java.ssy.jdk8;

import java.util.List;
import java.util.stream.Collector;
import java.util.stream.Collectors;

/**
 * _22_CollectorsSourceCode
 *
 * R container = collector.supplier().get();
 * for (T t : data) {
 * collector.accumulator().accept(container, t);
 * }
 * return collector.finisher().apply(container);
 *
 * @author zhucj
 * @see Collector 这几节都是讲解该接口的源码
 * @since 20200723
 */
public class _22_23_24_CollectorSourceCode {

    public static void main(String[] args) {
        List<_22_Student> studentList = _22_Student.getList();
        List<_22_Student> studentList1 = studentList.stream().collect(Collectors.toList());
        studentList1.forEach(System.out::println);
        System.out.println("----------");

        Long collect = studentList.stream().collect(Collectors.counting());
        System.out.println(collect);
        System.out.println("----------");

        System.out.println(studentList.stream().count());
    }
}
