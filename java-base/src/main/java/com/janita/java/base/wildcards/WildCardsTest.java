package com.janita.java.base.wildcards;

import java.util.ArrayList;
import java.util.List;

/**
 * 类说明：Fruit
 *
 * @author zhucj
 * @since 20200423
 */
public class WildCardsTest {

    public static void main(String[] args) {
        //        testUpperWildcards();
        testNoBoundWildcards();
    }

    private static void testUpperWildcards() {
        Fruit[] fruits = new Apple[10];
        //Fruit[] fruits
        int weight = WildCardsUtils.sumWeight(fruits);

        List<Fruit> fruitList = new ArrayList<>();
        List<Apple> appleList = new ArrayList<>();
        List<Orange> orangeList = new ArrayList<>();
        //List<? extends Fruit> fruits
        weight = WildCardsUtils.sumWeightInputSomethingExtendsFruitList(fruitList);
        weight = WildCardsUtils.sumWeightInputSomethingExtendsFruitList(appleList);
        weight = WildCardsUtils.sumWeightInputSomethingExtendsFruitList(orangeList);

        //List<Fruit> fruits
        fruitList.add(new Apple());
        weight = WildCardsUtils.sumWeightInputFruitList(fruitList);
    }

    private static void testNoBoundWildcards() {
        System.out.println(" List<String> stringList ");
        List<String> stringList = new ArrayList<>();
        stringList.add("1*****");
        stringList.add("2++++++");
        WildCardsUtils.printListWithAnything(stringList);

        System.out.println(" List<Integer> integerList ");
        List<Integer> integerList = new ArrayList<>();
        integerList.add(1000);
        integerList.add(20000);
        WildCardsUtils.printListWithAnything(integerList);

        System.out.println(" List<?> anyList ");
        List<?> anyList = new ArrayList<>();
        anyList.add(null);
        anyList.add(null);
        //        anyList.add(1);
        //        anyList.add("2");
        WildCardsUtils.printListWithAnything(anyList);

        System.out.println(" List list ");
        List list = new ArrayList();
        list.add(1);
        list.add(3.0F);
        list.add("123123123");
        list.add(new Fruit());
        WildCardsUtils.printListWithAnything(list);
    }

    private static void testUpperBoundedWildcards() {

        List<Apple> appleList = new ArrayList<>();
        List<Fruit> fruitList = new ArrayList<>();
        //testUpperBoundedWildcards(List<? super T> dst, List<T> src)
        WildCardsUtils.testUpperBoundedWildcards(fruitList, appleList);
        //        WildCardsUtils.testUpperBoundedWildcards(appleList, fruitList);
    }
}

class WildCardsUtils {

    /**
     * 下边界限定通配符 <? super E>
     * super 表示这个泛型中的参数必须是值E或者是E的父类，甚至可以是Object
     *
     * 可以看到：List<? super T> dst 添加元素是可以的，因为子类是可以指向父类的，
     * 添加的时候并不会出现像 List<? extends E> 会出现安全性问题
     * 因为取出来的元素都会使用父类型接受，向上转型是安全的，向下专项的不安全的
     */
    static <T> void testUpperBoundedWildcards(List<? super T> dst, List<T> src) {
        dst.addAll(src);
    }

    /**
     * 上边界限定通配符
     * 入参数：List<Fruit> fruitList 或者 List<Apple> appleList 或者 List<Orange> orangeList
     * 只要list的元素是Fruit或者Fruit的子类都可以
     *
     * 注意：通过消费，也就是只能从其中get元素，无法往其中set/add元素
     *
     * 理解：如果一个 List<? extends Fruit> fruits 可以 add(new Fruit()), 也可以 add(new Orange())
     * 如果这个 List<? extends Fruit> 真正传入的时候是一个 List<Fruit> 上面的2个add是可以的
     * 但是如果传进来的是 List<Apple> 那么add 一个橘子是不合理的，（明细向下专项是不安全的）
     * 于是为避免这样的不确定性，直接禁止了向 List<? extends Fruit> 中添加任何元素
     */
    static int sumWeightInputSomethingExtendsFruitList(List<? extends Fruit> fruits) {
        int weight = 0;
        for (Fruit fruit : fruits) {
            weight = weight + fruit.getWeight();
        }
        return weight;
    }

    static int sumWeight(Fruit[] fruits) {
        int weight = 0;
        for (Fruit fruit : fruits) {
            weight = weight + fruit.getWeight();
        }
        return weight;
    }

    /**
     * 入参数：List<Fruit> fruitList
     */
    static int sumWeightInputFruitList(List<Fruit> fruits) {
        int weight = 0;
        for (Fruit fruit : fruits) {
            weight = weight + fruit.getWeight();
        }
        return weight;
    }

    static void printListWithAnything(List<?> list) {
        for (Object o : list) {
            System.out.println(o);
        }
    }
}

class Fruit {

    public int getWeight() {
        return 0;
    }
}

class Apple extends Fruit {

    @Override
    public int getWeight() {
        return 5;
    }
}

class Orange extends Fruit {

    @Override
    public int getWeight() {
        return 2;
    }
}


