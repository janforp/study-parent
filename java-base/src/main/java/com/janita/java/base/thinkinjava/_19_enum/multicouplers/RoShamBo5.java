package com.janita.java.base.thinkinjava._19_enum.multicouplers;

import java.util.EnumMap;

import static com.janita.java.base.thinkinjava._19_enum.multicouplers.Outcome.DRAW;
import static com.janita.java.base.thinkinjava._19_enum.multicouplers.Outcome.LOSE;
import static com.janita.java.base.thinkinjava._19_enum.multicouplers.Outcome.WIN;

/**
 * RoShamBo5
 *
 * TODO 这样写我感觉不好理解
 *
 * @author zhucj
 * @since 20200528
 */
enum RoShamBo5 implements Competitor<RoShamBo5> {
    PAPER,
    SCISSORS,
    ROCK;

    static EnumMap<RoShamBo5, EnumMap<RoShamBo5, Outcome>> table = new EnumMap<RoShamBo5, EnumMap<RoShamBo5, Outcome>>(RoShamBo5.class);

    static {
        RoShamBo5[] roShamBo5s = RoShamBo5.values();
        for (RoShamBo5 it : roShamBo5s) {
            EnumMap<RoShamBo5, Outcome> outcomeEnumMap = new EnumMap<>(RoShamBo5.class);
            table.put(it, outcomeEnumMap);
        }
        initRow(PAPER, DRAW, LOSE, WIN);
        initRow(SCISSORS, WIN, DRAW, LOSE);
        initRow(ROCK, LOSE, WIN, DRAW);
    }

    static void initRow(RoShamBo5 it, Outcome vPAPER, Outcome vSCISSORS, Outcome vROCK) {
        EnumMap<RoShamBo5, Outcome> row = table.get(it);
        row.put(RoShamBo5.PAPER, vPAPER);
        row.put(RoShamBo5.SCISSORS, vSCISSORS);
        row.put(RoShamBo5.ROCK, vROCK);
    }

    public Outcome compete(RoShamBo5 it) {
        EnumMap<RoShamBo5, Outcome> enumMap = table.get(this);
        Outcome outcome = enumMap.get(it);
        return outcome;
    }

    public static void main(String[] args) {
        RoShamBo.play(RoShamBo5.class, 20);
    }
}
