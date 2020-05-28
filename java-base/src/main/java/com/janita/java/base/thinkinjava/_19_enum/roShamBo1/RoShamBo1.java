package com.janita.java.base.thinkinjava._19_enum.roShamBo1;

import java.util.Random;

import static com.janita.java.base.thinkinjava._19_enum.roShamBo1.Outcome.DRAW;
import static com.janita.java.base.thinkinjava._19_enum.roShamBo1.Outcome.LOSE;
import static com.janita.java.base.thinkinjava._19_enum.roShamBo1.Outcome.WIN;

/**
 * RoShamBo1
 *
 * @author zhucj
 * @since 20200528
 */

interface Item {

    Outcome compete(Item it);

    Outcome eval(Paper p);

    Outcome eval(Scissors s);

    Outcome eval(Rock r);
}

class Paper implements Item {

    @Override
    public Outcome compete(Item it) {
        return it.eval(this);
    }

    @Override
    public Outcome eval(Paper p) {
        return DRAW;
    }

    @Override
    public Outcome eval(Scissors s) {
        return WIN;
    }

    @Override
    public Outcome eval(Rock r) {
        return LOSE;
    }

    @Override
    public String toString() {
        return "Paper";
    }
}

class Scissors implements Item {

    @Override
    public Outcome compete(Item it) {
        return it.eval(this);
    }

    @Override
    public Outcome eval(Paper p) {
        return LOSE;
    }

    @Override
    public Outcome eval(Scissors s) {
        return DRAW;
    }

    @Override
    public Outcome eval(Rock r) {
        return WIN;
    }

    @Override
    public String toString() {
        return "Scissors";
    }
}

class Rock implements Item {

    @Override
    public Outcome compete(Item it) {
        return it.eval(this);
    }

    @Override
    public Outcome eval(Paper p) {
        return WIN;
    }

    @Override
    public Outcome eval(Scissors s) {
        return LOSE;
    }

    @Override
    public Outcome eval(Rock r) {
        return DRAW;
    }

    @Override
    public String toString() {
        return "Rock";
    }
}

public class RoShamBo1 {

    static final int SIZE = 20;

    private static Random rand = new Random(47);

    public static Item newItem() {
        switch (rand.nextInt(3)) {
            default:
            case 0:
                return new Scissors();
            case 1:
                return new Paper();
            case 2:
                return new Rock();
        }
    }

    public static void match(Item a, Item b) {
        Outcome competeResult = a.compete(b);
        System.out.println(a + " vs. " + b + ": " + competeResult);
    }

    public static void main(String[] args) {
        for (int i = 0; i < SIZE; i++) {
            Item itemA = newItem();
            Item itemB = newItem();
            match(itemA, itemB);
        }
    }
} /* Output:
Rock vs. Rock: DRAW
Paper vs. Rock: WIN
Paper vs. Rock: WIN
Paper vs. Rock: WIN
Scissors vs. Paper: WIN
Scissors vs. Scissors: DRAW
Scissors vs. Paper: WIN
Rock vs. Paper: LOSE
Paper vs. Paper: DRAW
Rock vs. Paper: LOSE
Paper vs. Scissors: LOSE
Paper vs. Scissors: LOSE
Rock vs. Scissors: WIN
Rock vs. Paper: LOSE
Paper vs. Rock: WIN
Scissors vs. Paper: WIN
Paper vs. Scissors: LOSE
Paper vs. Scissors: LOSE
Paper vs. Scissors: LOSE
Paper vs. Scissors: LOSE
*///:~
