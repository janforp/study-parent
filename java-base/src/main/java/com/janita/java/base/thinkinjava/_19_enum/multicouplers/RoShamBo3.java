package com.janita.java.base.thinkinjava._19_enum.multicouplers;

import static com.janita.java.base.thinkinjava._19_enum.multicouplers.Outcome.DRAW;
import static com.janita.java.base.thinkinjava._19_enum.multicouplers.Outcome.LOSE;
import static com.janita.java.base.thinkinjava._19_enum.multicouplers.Outcome.WIN;

/**
 * RoShamBo3
 *
 * @author zhucj
 * @since 20200528
 */
public enum RoShamBo3 implements Competitor<RoShamBo3> {

    PAPER {
        @Override
        public Outcome compete(RoShamBo3 it) {
            switch (it) {
                case PAPER:
                    return DRAW;
                case SCISSORS:
                    return LOSE;
                case ROCK:
                    return WIN;
                default:
                    return null;
            }
        }
    },
    SCISSORS {
        @Override
        public Outcome compete(RoShamBo3 it) {
            switch (it) {
                case PAPER:
                    return WIN;
                case SCISSORS:
                    return DRAW;
                case ROCK:
                    return LOSE;
                default:
                    return null;
            }
        }
    },
    ROCK {
        @Override
        public Outcome compete(RoShamBo3 it) {
            switch (it) {
                case PAPER:
                    return LOSE;
                case SCISSORS:
                    return WIN;
                case ROCK:
                    return DRAW;
                default:
                    return null;
            }
        }
    };

    public abstract Outcome compete(RoShamBo3 it);

    public static void main(String[] args) {
        RoShamBo.play(RoShamBo3.class, 20);
    }
}
