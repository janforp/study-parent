package com.janita.java.base.thinkinjava._19_enum.multicouplers;

import static com.janita.java.base.thinkinjava._19_enum.multicouplers.Outcome.DRAW;
import static com.janita.java.base.thinkinjava._19_enum.multicouplers.Outcome.LOSE;
import static com.janita.java.base.thinkinjava._19_enum.multicouplers.Outcome.WIN;

/**
 * RoShamBo2
 *
 * @author zhucj
 * @since 20200528
 */
public enum RoShamBo2 implements Competitor<RoShamBo2> {
    PAPER(DRAW, LOSE, WIN),
    SCISSORS(WIN, DRAW, LOSE),
    ROCK(LOSE, WIN, DRAW);

    private Outcome vPAPER;

    private Outcome vSCISSORS;

    private Outcome vROCK;

    RoShamBo2(Outcome paper, Outcome scissors, Outcome rock) {
        this.vPAPER = paper;
        this.vSCISSORS = scissors;
        this.vROCK = rock;
    }

    public Outcome compete(RoShamBo2 it) {
        switch (it) {
            default:
            case PAPER:
                return vPAPER;
            case SCISSORS:
                return vSCISSORS;
            case ROCK:
                return vROCK;
        }
    }

    public static void main(String[] args) {
        RoShamBo.play(RoShamBo2.class, 20);
        System.out.println(PAPER.compete(PAPER));
        System.out.println(PAPER.compete(ROCK));
        System.out.println(PAPER.compete(SCISSORS));
        System.out.println(ROCK.compete(ROCK));
        System.out.println(ROCK.compete(PAPER));
        System.out.println(ROCK.compete(SCISSORS));
        System.out.println(SCISSORS.compete(PAPER));
        System.out.println(SCISSORS.compete(ROCK));
        System.out.println(SCISSORS.compete(SCISSORS));

    }
}
