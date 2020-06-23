package com.janita.java.ssy.jdk8;

import java.util.Arrays;
import java.util.Date;
import java.util.List;
import java.util.function.Predicate;
import java.util.stream.Collectors;

/**
 * _9_Predicte
 *
 * @author zhucj
 * @since 20200623
 */
public class _9_Predicate {

    public static void main(String[] args) {
        Predicate<String> predicate = p -> p.length() > 5;
        System.out.println(predicate.test("hello"));
        System.out.println(predicate.test("hell12313o"));
    }
}

class _10_PredicateTest2 {

    public static void main(String[] args) {
        List<Integer> list = Arrays.asList(1, 2, 3, 4, 5, 6, 7, 8, 9, 10);
        _10_PredicateTest2 test = new _10_PredicateTest2();
        test.conditionFilter2(list, item -> item % 2 == 0);//传递行为，提供了更高层次的抽象
        System.out.println("----------------");
        test.conditionFilter2(list, item -> item % 2 != 0);
        System.out.println("----------------");
        test.conditionFilter2(list, item -> item > 5);
        System.out.println("----------------");
        test.conditionFilter2(list, item -> item < 3);
        System.out.println("----------------");
        test.conditionFilter2(list, item -> true);
        System.out.println("----------------");
        test.conditionFilter2(list, item -> false);
        System.out.println("----------------");
        test.and(list, value -> value > 5, value -> value % 2 == 0);
        System.out.println("----------------");
        test.or(list, value -> value > 5, value -> value % 2 == 0);
        System.out.println("----------------");
        Predicate<String> equal = test.isEqual("test");
        System.out.println(equal.test("test"));
        System.out.println("----------------");
        Predicate<Date> equalDate = test.isDateEqual(new Date());
        System.out.println(equalDate.test(new Date()));
    }

    private List<Integer> filter(List<Integer> list, Predicate<Integer> predicate) {
        return list.stream().filter(predicate).collect(Collectors.toList());
    }

    /**
     * @param list
     * @param predicate 传递行为，提供了更高层次的抽象
     */
    private void conditionFilter2(List<Integer> list, Predicate<Integer> predicate) {
        for (Integer integer : list) {
            if (predicate.test(integer)) {
                System.out.println(integer);
            }
        }
    }

    private void and(List<Integer> list, Predicate<Integer> predicate1, Predicate<Integer> predicate2) {
        Predicate<Integer> and = predicate1.and(predicate2);
        for (Integer integer : list) {
            if (and.test(integer)) {
                System.out.println(integer);
            }
        }
    }

    private void or(List<Integer> list, Predicate<Integer> predicate1, Predicate<Integer> predicate2) {
        Predicate<Integer> or = predicate1.or(predicate2);
        for (Integer integer : list) {
            if (or.test(integer)) {
                System.out.println(integer);
            }
        }
    }

    private Predicate<String> isEqual(Object o) {
        return Predicate.isEqual(o);
    }

    private Predicate<Date> isDateEqual(Object o) {
        return Predicate.isEqual(o);
    }
}
