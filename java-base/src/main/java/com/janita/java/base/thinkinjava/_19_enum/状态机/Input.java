package com.janita.java.base.thinkinjava._19_enum.状态机;

import java.util.Random;

/**
 * Input
 *
 * @author zhucj
 * @since 20200528
 */
public enum Input {
    NICKEL(5),
    DIME(10),
    QUARTER(25),
    DOLLAR(100),
    TOOTHPASTE(200),
    CHIPS(75),
    SODA(100),
    SOAP(50),
    ABORT_TRANSACTION {
        @Override
        public int amount() { // Disallow
            throw new RuntimeException("ABORT.amount()");
        }
    },
    STOP { // This must be the last instance.

        @Override
        public int amount() { // Disallow
            throw new RuntimeException("SHUT_DOWN.amount()");
        }
    };

    int value; // In cents

    Input(int value) {
        this.value = value;
    }

    Input() {
    }

    int amount() {
        return value;
    }

    ; // In cents

    static Random rand = new Random(47);

    public static Input randomSelection() {
        // Don't include STOP:
        Input[] values = values();
        return values[rand.nextInt(values.length - 1)];
    }
} ///:~
