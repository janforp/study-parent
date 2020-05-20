package com.janita.java.base.thinkinjava._15_genericity;

import com.janita.java.base.thinkinjava._15_genericity.coffee.Coffee;

import java.util.ArrayList;
import java.util.Collection;

/**
 * 类说明：Generators
 *
 * @author zhucj
 * @since 20200528
 */
public class Generators {

    public static <T> Collection<T> fill(Collection<T> collToFill, Generator<T> gen, int n) {
        for (int i = 0; i < n; i++) {
            collToFill.add(gen.next());
        }
        return collToFill;
    }

    public static void main(String[] args) {
        Collection<Coffee> coffee = fill(new ArrayList<>(), new CoffeeGenerator(), 4);
        for (Coffee c : coffee) {
            System.out.println(c);
        }
        Collection<Integer> fnumbers = fill(
                new ArrayList<>(), new Fibonacci(), 12);
        for (int i : fnumbers) {
            System.out.print(i + ", ");
        }
    }
}
