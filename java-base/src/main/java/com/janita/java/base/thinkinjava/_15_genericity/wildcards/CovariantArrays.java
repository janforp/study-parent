package com.janita.java.base.thinkinjava._15_genericity.wildcards;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * 类说明：CovariantArrays
 *
 * @author zhucj
 * @since 20200528
 */
class Fruit {

}

class Apple extends Fruit {

}

class Jonathan extends Apple {

}

class Orange extends Fruit {

}

public class CovariantArrays {

    public static void main(String[] args) {
        //其实是 Apple 类型的数组，在其中只能放 Apple 或者 Apple 的子类，而不能是 Apple 的父类
        Fruit[] fruit = new Apple[10];
        fruit[0] = new Apple(); // OK
        fruit[1] = new Jonathan(); // OK
        // Runtime type is Apple[], not Fruit[] or Orange[]:
        try {
            // Compiler allows you to add Fruit:
            //其实是 Apple 类型的数组
            fruit[0] = new Fruit(); // ArrayStoreException
        } catch (Exception e) {
            System.out.println(e);
        }
        try {
            // Compiler allows you to add Oranges:
            //其实是 Apple 类型的数组
            fruit[0] = new Orange(); // ArrayStoreException
        } catch (Exception e) {
            System.out.println(e);
        }
    }

    //其他方法

    static class NonCovariantGenerics {

        List<Fruit> fruitList = new ArrayList<>();
        //编译错误：incompatible types:

        //其实这不是向上转型：Apple的List并不是Fruit的List,即便Apple是一种Fruit
        //因为我们是在谈论容器的类型，而不是容器持有的类型
        //List<Fruit> appleList =  new ArrayList<Apple>();
    }

}

class GenericsAndCovariance {

    public static void main(String[] args) {
        // Wildcards allow covariance:
        //TODO List 的向上转型？一旦执行这种类型的向上专项，就将丢失向其中传递任何对象的功能，但是从其中获取数据是安全的
        List<? extends Fruit> fruitList = new ArrayList<Apple>();
        // Compile Error: can't add any type of object:
        //fruitList.add(new Apple());
        //fruitList.add(new Fruit());
        //fruitList.add(new Object());
        //fruitList.add(new Object());
        fruitList.add(null); // Legal but uninteresting
        // We know that it returns at least Fruit:
        Fruit f = fruitList.get(0);
    }
}

class CompilerIntelligence {

    public static void main(String[] args) {
        List<? extends Fruit> fruits = Arrays.asList(new Apple());
        //        fruits.add(new Apple());
        Apple a = (Apple) fruits.get(0); // No warning
        fruits.contains(new Apple()); // Argument is 'Object',因为该方法的入参数是一个object
        fruits.indexOf(new Apple()); // Argument is 'Object'
    }
}
