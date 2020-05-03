package com.janita.java.base.thinkinjava._10_innerclass;

/**
 * 类说明：BigEgg
 *
 * @author zhucj
 * @since 20200423
 */
public class BigEgg extends Egg {

    public class Yolk {

        public Yolk() {
            System.out.println("BigEgg.Yolk()");
        }
    }

    public static void main(String[] args) {
        new BigEgg();
    }
}

class Egg {

    private Yolk y;

    public Egg() {
        System.out.println("new Egg()");
        y = new Yolk();
    }

    protected class Yolk {

        public Yolk() {
            System.out.println("Egg.Yolk()");
        }
    }
}
