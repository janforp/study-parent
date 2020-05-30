package com.janita.java.base.thinkinjava._19_enum.multicouplers;

/**
 * RoShamBo4
 *
 * @author zhucj
 * @since 20200528
 */
public enum RoShamBo4 implements Competitor<RoShamBo4> {
    ROCK {
        public Outcome compete(RoShamBo4 opponent) {
            return compete(SCISSORS, opponent);
        }
    },
    SCISSORS {
        public Outcome compete(RoShamBo4 opponent) {
            return compete(PAPER, opponent);
        }
    },
    PAPER {
        public Outcome compete(RoShamBo4 opponent) {
            return compete(ROCK, opponent);
        }
    };

    /**
     * 比较
     *
     * @param loser 当前调用该方法的对象能赢的对象
     * @param opponent 当前真正竞争的对象
     * @return 结果
     */
    Outcome compete(RoShamBo4 loser, RoShamBo4 opponent) {
        return
                opponent == this ?
                        Outcome.DRAW :
                        ((opponent == loser) ?
                                Outcome.WIN :
                                Outcome.LOSE);
    }

    public static void main(String[] args) {
        RoShamBo.play(RoShamBo4.class, 20);
    }
}
