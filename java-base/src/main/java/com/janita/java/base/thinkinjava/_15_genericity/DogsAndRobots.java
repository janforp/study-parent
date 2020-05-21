package com.janita.java.base.thinkinjava._15_genericity;

import static com.janita.java.base.thinkinjava._15_genericity.util.Print.print;

/**
 * 类说明：DogsAndRobots
 *
 * @author zhucj
 * @since 20200528
 */
class Dog2 {

}

class PerformingDog extends Dog2 implements Performs {

    public void speak() {
        print("Woof!");
    }

    public void sit() {
        print("Sitting");
    }

    public void reproduce() {
    }
}

class Robot implements Performs {

    public void speak() {
        print("Click!");
    }

    public void sit() {
        print("Clank!");
    }

    public void oilChange() {
    }
}

class Communicate {

    public static <T extends Performs> void perform(T performer) {
        performer.speak();
        performer.sit();
    }
}

class CommunicateSimply {

    static void perform(Performs performer) {
        performer.speak();
        performer.sit();
    }
}

public class DogsAndRobots {

    public static void main(String[] args) {
        PerformingDog d = new PerformingDog();
        Robot r = new Robot();
        Communicate.perform(d);
        Communicate.perform(r);
    }
}

class SimpleDogsAndRobots {

    public static void main(String[] args) {
        CommunicateSimply.perform(new PerformingDog());
        CommunicateSimply.perform(new Robot());
    }
}