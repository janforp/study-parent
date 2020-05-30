package com.janita.java.base.thinkinjava._19_enum.multicouplers;

import static com.janita.java.base.thinkinjava._19_enum.multicouplers.Outcome.DRAW;
import static com.janita.java.base.thinkinjava._19_enum.multicouplers.Outcome.LOSE;
import static com.janita.java.base.thinkinjava._19_enum.multicouplers.Outcome.WIN;

/**
 * RoShamBo6
 *
 * @author zhucj
 * @since 20200528
 */
enum RoShamBo6 implements Competitor<RoShamBo6> {
    PAPER,
    SCISSORS,
    ROCK;

    private static Outcome[][] table = {
            { DRAW, LOSE, WIN }, // PAPER
            { WIN, DRAW, LOSE }, // SCISSORS
            { LOSE, WIN, DRAW }, // ROCK
    };

    public Outcome compete(RoShamBo6 other) {
        return table[this.ordinal()][other.ordinal()];
    }

    public static void main(String[] args) {
        RoShamBo.play(RoShamBo6.class, 20);
    }
} ///:~