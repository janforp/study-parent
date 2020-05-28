package com.janita.java.base.thinkinjava._19_enum;

import static com.janita.java.base.thinkinjava.util.Print.print;

/**
 * TrafficLight
 *
 * @author zhucj
 * @since 20200528
 */
enum Signal {
    GREEN,
    YELLOW,
    RED,
}

public class TrafficLight {

    Signal color = Signal.RED;

    public void change() {
        switch (color) {
            // Note that you don't have to say Signal.RED
            // in the case statement:
            case RED:
                color = Signal.GREEN;
                break;
            case GREEN:
                color = Signal.YELLOW;
                break;
            case YELLOW:
                color = Signal.RED;
                break;
        }
    }

    public String toString() {
        return "The traffic light is " + color;
    }

    public static void main(String[] args) {
        TrafficLight t = new TrafficLight();
        for (int i = 0; i < 7; i++) {
            print(t);
            t.change();
        }
    }
} /* Output:
The traffic light is RED
The traffic light is GREEN
The traffic light is YELLOW
The traffic light is RED
The traffic light is GREEN
The traffic light is YELLOW
The traffic light is RED
*///:~
