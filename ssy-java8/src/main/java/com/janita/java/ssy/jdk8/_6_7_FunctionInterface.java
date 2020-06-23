package com.janita.java.ssy.jdk8;

import java.util.Arrays;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;
import java.util.concurrent.Callable;
import java.util.function.BiFunction;
import java.util.function.Function;

/**
 * _6_FunctionInterface
 *
 * @author zhucj
 * @since 20200623
 */
public class _6_7_FunctionInterface {

    public static void main(String[] args) {
    }
}

class StringComparator {

    public static void main(String[] args) {
        List<String> nameList = Arrays.asList("zhangsan", "lisi", "wangwu", "zhaoliu");
        Collections.sort(nameList, new Comparator<String>() {
            @Override
            public int compare(String o1, String o2) {
                //倒序
                return o2.compareTo(o1);
            }
        });
        System.out.println(nameList);

        nameList = Arrays.asList("zhangsan", "lisi", "wangwu", "zhaoliu");
        Collections.sort(nameList, (String o1, String o2) -> {
            return o2.compareTo(o1);/*statement**/
        });
        System.out.println(nameList);

        nameList = Arrays.asList("zhangsan", "lisi", "wangwu", "zhaoliu");
        Collections.sort(nameList, (o1, o2) -> o2.compareTo(o1) /*expression*/);
        System.out.println(nameList);


        nameList = Arrays.asList("zhangsan", "lisi", "wangwu", "zhaoliu");
        Collections.sort(nameList, Comparator.reverseOrder());
        System.out.println(nameList);

        Callable<Integer> integerCallable = () -> 41;
    }
}

/**
 * 高阶函数：如果一个函数结束一个函数作为参数，或者返回一个函数作为返回值，那么该函数就叫做高阶函数
 */
class FunctionTest {

    public static void main(String[] args) {
        FunctionTest test = new FunctionTest();
        //传递不同的行为
        System.out.println(test.compute(1, value -> {
            return 2 * value;
        }));
        System.out.println(test.compute(2, value -> 5 + value));
        System.out.println(test.compute(3, value -> value * value));
        System.out.println(test.convert(5, value -> String.valueOf(value + "helloWorld")));

        //lambda之前我们必须要定义好不同的方法来表达这些不同的行为，现在我们可以直接传递行为，只需要定义一个方法

        Function<Integer, Integer> function = value -> value + 2;
        System.out.println(test.compute(4, function));
    }

    //传递行为：高阶函数
    public int compute(int a, Function<Integer, Integer> function) {
        return function.apply(a);
    }

    //高阶函数
    public String convert(int a, Function<Integer, String> function) {
        return function.apply(a);
    }
}

class FunctionTest2 {

    public static void main(String[] args) {
        FunctionTest2 test2 = new FunctionTest2();
        System.out.println(test2.compose(2, value -> value * 3, value -> value * value));//12
        System.out.println(test2.andThen(2, value -> value * 3, value -> value * value));//36
    }

    public int compose(int a, Function<Integer, Integer> after, Function<Integer, Integer> before) {
        Function<Integer, Integer> compose = after.compose(before);
        return compose.apply(a);
    }

    public int andThen(int a, Function<Integer, Integer> before, Function<Integer, Integer> after) {
        Function<Integer, Integer> andThen = before.andThen(after);
        return andThen.apply(a);
    }
}

class BiFunctionTest {

    public static void main(String[] args) {
        BiFunctionTest test = new BiFunctionTest();

        //compute
        System.out.println(test.compute(1, 2, (v1, v2) -> v1 + v2));
        System.out.println(test.compute(1, 2, (v1, v2) -> v1 - v2));
        System.out.println(test.compute(1, 2, (v1, v2) -> v1 * v2));
        System.out.println(test.compute(1, 2, (v1, v2) -> v1 / v2));

        //andThen
        int andThen = test.andThen(2, 3, (v1, v2) -> v1 + v2, v -> v * v);
        System.out.println(andThen);//25
    }

    public int compute(int a, int b, BiFunction<Integer, Integer, Integer> biFunction) {
        return biFunction.apply(a, b);
    }

    public int andThen(int a, int b, BiFunction<Integer, Integer, Integer> before,
                       Function<Integer, Integer> after) {

        BiFunction<Integer, Integer, Integer> andThen = before.andThen(after);
        return andThen.apply(a, b);
    }
}