package com.janita.java.ssy.jdk8;

import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import java.util.function.Function;
import java.util.function.Supplier;

/**
 * _13_MethodReferenceDemo
 * <p>
 * 1.类名::方法名称
 * 2.对象名::实例方法名
 * 3.类名::实例方法名
 * 4.构造方法引用（类名::new）
 *
 * @author zhucj
 * @since 20200623
 */
public class _13_14_MethodReferenceDemo {

    public static void main(String[] args) {
        Student2 student1 = new Student2("zhangsan", 10);
        Student2 student2 = new Student2("lisi", 90);
        Student2 student3 = new Student2("wangwu", 50);
        Student2 student4 = new Student2("zhaoliu", 40);
        List<Student2> student2List = Arrays.asList(student1, student2, student3, student4);
        student2List.sort((s1, s2) -> Student2.compareByScore(s1, s2));
        System.out.println(student2List);
        Collections.shuffle(student2List);
        System.out.println(student2List);
        //1.类名::方法名称
        student2List.sort(Student2::compareByScore);
        System.out.println(student2List);
        Collections.shuffle(student2List);
        student2List.sort((s1,s2) -> Student2.compareByName(s1,s2));
        System.out.println(student2List);
        Collections.shuffle(student2List);
        student2List.sort(Student2::compareByName);
        System.out.println(student2List);
        Collections.shuffle(student2List);

        //2.对象名::实例方法名
        StudentComparator studentComparator = new StudentComparator();
        student2List.sort((s1,s2) -> studentComparator.compareStudentByScore(s1,s2));
        System.out.println(student2List);
        Collections.shuffle(student2List);
        student2List.sort(studentComparator::compareStudentByScore);
        System.out.println(student2List);
        Collections.shuffle(student2List);
        student2List.sort(studentComparator::compareStudentByName);
        System.out.println(student2List);
        Collections.shuffle(student2List);

        // 3.类名::实例方法名
        student2List.sort(Student2::compareByScore2);
        System.out.println(student2List);
        Collections.shuffle(student2List);
        student2List.sort(Student2::compareByName2);
        System.out.println(student2List);

        List<String> cityList = Arrays.asList("qingdao", "chongqing","tianjin","beijing");
        Collections.sort(cityList, (c1,c2) -> c1.compareToIgnoreCase(c2));
        System.out.println(cityList);
        Collections.shuffle(cityList);

        // 4.构造方法引用（类名::new）
        _13_14_MethodReferenceDemo demo = new _13_14_MethodReferenceDemo();
        System.out.println(demo.getString(String::new));
        System.out.println(demo.getString2("hello", String::new));
    }

    public String getString(Supplier<String> supplier) {
        return supplier.get() + "test";
    }

    public String getString2(String string, Function<String, String> function) {
        return function.apply(string);
    }
}

class StudentComparator {

    public int compareStudentByScore(Student2 s1, Student2 s2) {
        return s1.getScore() - s2.getScore();
    }

    public int compareStudentByName(Student2 s1, Student2 s2) {
        return s1.getName().compareToIgnoreCase(s2.getName());
    }
}

class MethodOverride {

    private String test(Long para) {
        return "长整型" + para;
    }

    private String test(int para) {
        return "整型" + para;
    }

    private String test(String para) {
        return "字符串" + para;
    }

    public static void main(String[] args) {
        MethodOverride methodOverride = new MethodOverride();
        String test = methodOverride.test((String) null);
        System.out.println(test);
        //NPE
        String test1 = methodOverride.test((Integer) null);
        System.out.println(test1);
    }
}