package com.janita.java.base.thinkinjava._19_enum.状态机;

import com.janita.java.base.thinkinjava._15_genericity.Generator;
import com.janita.java.base.thinkinjava._18_io.TextFile;

import java.util.EnumMap;
import java.util.Iterator;

import static com.janita.java.base.thinkinjava._19_enum.状态机.Input.ABORT_TRANSACTION;
import static com.janita.java.base.thinkinjava._19_enum.状态机.Input.CHIPS;
import static com.janita.java.base.thinkinjava._19_enum.状态机.Input.DIME;
import static com.janita.java.base.thinkinjava._19_enum.状态机.Input.DOLLAR;
import static com.janita.java.base.thinkinjava._19_enum.状态机.Input.NICKEL;
import static com.janita.java.base.thinkinjava._19_enum.状态机.Input.QUARTER;
import static com.janita.java.base.thinkinjava._19_enum.状态机.Input.SOAP;
import static com.janita.java.base.thinkinjava._19_enum.状态机.Input.SODA;
import static com.janita.java.base.thinkinjava._19_enum.状态机.Input.STOP;
import static com.janita.java.base.thinkinjava._19_enum.状态机.Input.TOOTHPASTE;
import static com.janita.java.base.thinkinjava.util.Print.print;

/**
 * VendingMachine
 *
 * @author zhucj
 * @since 20200528
 */

enum Category {
    MONEY(NICKEL, DIME, QUARTER, DOLLAR),
    ITEM_SELECTION(TOOTHPASTE, CHIPS, SODA, SOAP),
    QUIT_TRANSACTION(ABORT_TRANSACTION),
    SHUT_DOWN(STOP);

    private Input[] values;

    Category(Input... types) {
        values = types;
    }

    private static EnumMap<Input, Category> categories = new EnumMap<Input, Category>(Input.class);

    static {
        Category[] enumConstants = Category.class.getEnumConstants();
        for (Category c : enumConstants) {
            Input[] inputs = c.values;
            for (Input type : inputs) {
                categories.put(type, c);
            }
        }
    }

    public static Category categorize(Input input) {
        return categories.get(input);
    }
}

public class VendingMachine {

    private static State state = State.RESTING;

    private static int amount = 0;

    private static Input selection = null;

    enum StateDuration {
        TRANSIENT
    } // Tagging enum

    enum State {
        RESTING {
            void next(Input input) {
                switch (Category.categorize(input)) {
                    case MONEY:
                        amount += input.amount();
                        state = ADDING_MONEY;
                        break;
                    case SHUT_DOWN:
                        state = TERMINAL;
                    default:
                }
            }
        },
        ADDING_MONEY {
            void next(Input input) {
                switch (Category.categorize(input)) {
                    case MONEY:
                        amount += input.amount();
                        break;
                    case ITEM_SELECTION:
                        selection = input;
                        if (amount < selection.amount()) {
                            print("Insufficient money for " + selection);
                        } else {
                            state = DISPENSING;
                        }
                        break;
                    case QUIT_TRANSACTION:
                        state = GIVING_CHANGE;
                        break;
                    case SHUT_DOWN:
                        state = TERMINAL;
                    default:
                }
            }
        },
        DISPENSING(StateDuration.TRANSIENT) {
            void next() {
                print("here is your " + selection);
                amount -= selection.amount();
                state = GIVING_CHANGE;
            }
        },
        GIVING_CHANGE(StateDuration.TRANSIENT) {
            void next() {
                if (amount > 0) {
                    print("Your change: " + amount);
                    amount = 0;
                }
                state = RESTING;
            }
        },
        TERMINAL {
            void output() {
                print("Halted");
            }
        };

        private boolean isTransient = false;

        State() {
        }

        State(StateDuration trans) {
            isTransient = true;
        }

        void next(Input input) {
            throw new RuntimeException("Only call " +
                    "next(Input input) for non-transient states");
        }

        void next() {
            throw new RuntimeException("Only call next() for " +
                    "StateDuration.TRANSIENT states");
        }

        void output() {
            print(amount);
        }
    }

    static void run(Generator<Input> gen) {
        while (state != State.TERMINAL) {
            state.next(gen.next());
            while (state.isTransient) {
                state.next();
            }
            state.output();
        }
    }

    public static void main(String[] args) {
        Generator<Input> gen = new RandomInputGenerator();
        if (args.length == 1) {
            gen = new FileInputGenerator(args[0]);
        }
        run(gen);
    }
}

// For a basic sanity check:
class RandomInputGenerator implements Generator<Input> {

    public Input next() {
        return Input.randomSelection();
    }
}

// Create Inputs from a file of ';'-separated strings:
class FileInputGenerator implements Generator<Input> {

    private Iterator<String> input;

    public FileInputGenerator(String fileName) {
        input = new TextFile(fileName, ";").iterator();
    }

    public Input next() {
        if (!input.hasNext()) {
            return null;
        }
        return Enum.valueOf(Input.class, input.next().trim());
    }
} /* Output:
25
50
75
here is your CHIPS
0
100
200
here is your TOOTHPASTE
0
25
35
Your change: 35
0
25
35
Insufficient money for SODA
35
60
70
75
Insufficient money for SODA
75
Your change: 75
0
Halted
*///:~
