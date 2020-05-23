package com.janita.java.base.thinkinjava._15_genericity.适配器;

import com.janita.java.base.thinkinjava._15_genericity.Generator;
import com.janita.java.base.thinkinjava._15_genericity.SimpleQueue;
import com.janita.java.base.thinkinjava._15_genericity.coffee.Coffee;
import com.janita.java.base.thinkinjava._15_genericity.coffee.Latte;
import com.janita.java.base.thinkinjava._15_genericity.coffee.Mocha;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

import static com.janita.java.base.thinkinjava.util.Print.print;

/**
 * 类说明：Fill2
 * 专业名次：潜在类型机制
 * @author zhucj
 * @since 20200528
 */
interface Addable<T> {

    void add(T t);
}

public class Fill2 {

    // ClassToken version:
    public static <T> void fill(Addable<T> addable, Class<? extends T> classToken, int size) {
        for (int i = 0; i < size; i++) {
            try {
                addable.add(classToken.newInstance());
            } catch (Exception e) {
                throw new RuntimeException(e);
            }
        }
    }

    // Generator version:
    public static <T> void fill(Addable<T> addable, Generator<T> generator, int size) {
        for (int i = 0; i < size; i++) {
            addable.add(generator.next());
        }
    }
}

/**
 * 对方需要的接口是一个 Addable 接口
 *
 * @param <T>
 */
class AddableCollectionAdapter<T> implements Addable<T> {

    /**
     * 但是目前只有 Collection 接口
     */
    private Collection<T> c;

    public AddableCollectionAdapter(Collection<T> c) {
        this.c = c;
    }

    public void add(T item) {
        c.add(item);
    }
}

// A Helper to capture the type automatically:
class Adapter {

    /**
     * 产生一个 由 Collection 适配而得到的 Addable 接口的实现
     * 使用方通过该方法得到一个 Addable 接口的实现，使用方是不知道底层的实现的
     *
     * @param c
     * @param <T>
     * @return
     */
    public static <T> Addable<T> collectionAdapter(Collection<T> c) {
        return new AddableCollectionAdapter<T>(c);
    }
}

// To adapt a specific type, you can use inheritance.
// Make a SimpleQueue Addable using inheritance:
/**
 * 产生一个 由 SimpleQueue 适配而得到的 Addable 接口的实现
 * 使用方通过该方法得到一个 Addable 接口的实现，使用方是不知道底层的实现的
 *
 * @param <T>
 * @return
 */
class AddableSimpleQueue<T> extends SimpleQueue<T> implements Addable<T> {

    public void add(T item) {
        super.add(item);
    }
}

class Fill2Test {

    public static void main(String[] args) {
        // Adapt a Collection:
        List<Coffee> carrier = new ArrayList<Coffee>();
        Fill2.fill(new AddableCollectionAdapter<Coffee>(carrier), Coffee.class, 3);
        // Helper method captures the type:
        Fill2.fill(Adapter.collectionAdapter(carrier), Latte.class, 2);
        for (Coffee c : carrier) {
            print(c);
        }
        print("----------------------");
        // Use an adapted class:
        AddableSimpleQueue<Coffee> coffeeQueue = new AddableSimpleQueue<Coffee>();
        Fill2.fill(coffeeQueue, Mocha.class, 4);
        Fill2.fill(coffeeQueue, Latte.class, 1);
        for (Coffee c : coffeeQueue) {
            print(c);
        }
    }
}