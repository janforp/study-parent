package com.janita.java.ssy.jdk8;

import java.util.Comparator;
import java.util.function.BinaryOperator;
import java.util.function.Supplier;

/**
 * _11_Supplier
 *
 * @author zhucj
 * @since 20200623
 */
public class _11_Supplier {

    public static void main(String[] args) {
        Supplier<String> supplier = () -> "hello world";

    }
}

class StudentTest {

    public static void main(String[] args) {
        Supplier<Student> studentSupplier = () -> new Student();
        System.out.println(studentSupplier.get());
        System.out.println("--------");
        Supplier<Student> studentSupplier2 = Student::new;
        System.out.println(studentSupplier2.get().getAge());
        System.out.println("--------");
    }
}

class BinaryOperatorTest {

    public static void main(String[] args) {
        BinaryOperatorTest test = new BinaryOperatorTest();
        System.out.println(test.compute(1, 2, Integer::sum));
        System.out.println(test.compute(2, 3, (v1, v2) -> v1 * v2));
        System.out.println("--------");
        BinaryOperator<String> operator = BinaryOperator.minBy(String::compareTo);
        String apply = operator.apply("a", "b");
        System.out.println(apply);
        System.out.println("--------");
        //返回长度较短的字符串
        String aShort = test.getShort("abc", "ac", (a, b) -> a.length() - b.length());
        System.out.println(aShort);

        System.out.println("--------");
        //返回首字母的顺序小的
        aShort = test.getShort("a", "b", (a, b) -> a.charAt(0) - b.charAt(0));
        System.out.println(aShort);
    }

    private int compute(int a, int b, BinaryOperator<Integer> operator) {
        return operator.apply(a, b);
    }

    private String getShort(String a, String b, Comparator<String> comparator) {
        BinaryOperator<String> minBy = BinaryOperator.minBy(comparator);
        return minBy.apply(a, b);
    }
}