package com.janita.java.base.thinkinjava._19_enum;

import com.janita.java.base.thinkinjava._15_genericity.Generator;

import java.util.Random;

/**
 * EnumImplementation
 *
 * @author zhucj
 * @since 20200528
 */
enum CartoonCharacter implements Generator<CartoonCharacter> {
    SLAPPY,
    SPANKY,
    PUNCHY,
    SILLY,
    BOUNCY,
    NUTTY,
    BOB;

    private Random rand = new Random(47);

    public CartoonCharacter next() {
        CartoonCharacter[] values = values();
        int length = values.length;
        int nextInt = rand.nextInt(length);
        return values[nextInt];
    }
}

public class EnumImplementation {

    public static <T> void printNext(Generator<T> rg) {
        System.out.print(rg.next() + ", ");
    }

    public static void main(String[] args) {
        // Choose any instance:
        CartoonCharacter cc = CartoonCharacter.BOB;
        for (int i = 0; i < 10; i++) {
            printNext(cc);
        }
    }
} /* Output:
BOB, PUNCHY, BOB, SPANKY, NUTTY, PUNCHY, SLAPPY, NUTTY, NUTTY, SLAPPY,
*///:~
