package com.janita.java.base.thinkinjava._15_genericity;

/**
 * 类说明：Fibonacci
 *
 * @author zhucj
 * @since 20200528
 */
public class Fibonacci implements Generator<Integer> {

    private int count = 0;

    public Integer next() {
        return fib(count++);
    }

    private int fib(int n) {
        if (n < 2) {
            return 1;
        }
        return fib(n - 2) + fib(n - 1);
    }

    public static void main(String[] args) {
        Fibonacci gen = new Fibonacci();
        for (int i = 0; i < 18; i++) {
            System.out.print(gen.next() + " ");
        }
    }
}
