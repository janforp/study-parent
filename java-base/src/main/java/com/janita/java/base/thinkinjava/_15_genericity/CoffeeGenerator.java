package com.janita.java.base.thinkinjava._15_genericity;

import com.janita.java.base.thinkinjava._15_genericity.coffee.Americano;
import com.janita.java.base.thinkinjava._15_genericity.coffee.Breve;
import com.janita.java.base.thinkinjava._15_genericity.coffee.Cappuccino;
import com.janita.java.base.thinkinjava._15_genericity.coffee.Coffee;
import com.janita.java.base.thinkinjava._15_genericity.coffee.Latte;
import com.janita.java.base.thinkinjava._15_genericity.coffee.Mocha;

import java.util.Iterator;
import java.util.Random;

/**
 * 类说明：CoffeeGenerator
 *
 * @author zhucj
 * @since 20200528
 */
public class CoffeeGenerator implements Generator<Coffee>, Iterable<Coffee> {

    private Class<?>[] types = { Latte.class, Mocha.class, Cappuccino.class, Americano.class, Breve.class, };

    private static Random rand = new Random(47);

    public CoffeeGenerator() {
    }

    // For iteration:
    private int size = 0;

    public CoffeeGenerator(int sz) {
        size = sz;
    }

    public Coffee next() {
        try {
            return (Coffee) types[rand.nextInt(types.length)].newInstance();
            // Report programmer errors at run time:
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    class CoffeeIterator implements Iterator<Coffee> {

        int count = size;

        public boolean hasNext() {
            return count > 0;
        }

        public Coffee next() {
            count--;
            return CoffeeGenerator.this.next();
        }

        @Override
        public void remove() { // Not implemented
            throw new UnsupportedOperationException();
        }
    }

    @Override
    public Iterator<Coffee> iterator() {
        return new CoffeeIterator();
    }

    public static void main(String[] args) {
        CoffeeGenerator gen = new CoffeeGenerator();
        for (int i = 0; i < 5; i++) {
            System.out.println(gen.next());
        }
        for (Coffee c : new CoffeeGenerator(5)) {
            System.out.println(c);
        }

        CoffeeGenerator coffees = new CoffeeGenerator(5);
        Iterator<Coffee> iterator = coffees.iterator();
        while (iterator.hasNext()) {
            System.out.println(iterator.next());
        }
    }
}