package com.janita.java.base.thinkinjava._17_container;

import java.lang.reflect.Constructor;
import java.util.HashMap;
import java.util.Map;
import java.util.Random;

import static com.janita.java.base.thinkinjava.util.Print.print;

/**
 * 类说明：SpringDetector
 *
 * @author zhucj
 * @since 20200528
 */
class Groundhog {

    protected int number;

    public Groundhog(int n) {
        number = n;
    }

    public String toString() {
        return "Groundhog #" + number;
    }
}

class Prediction {

    private static Random rand = new Random(47);

    private boolean shadow = rand.nextDouble() > 0.5;

    public String toString() {
        if (shadow) {
            return "Six more weeks of Winter!";
        } else {
            return "Early Spring!";
        }
    }
}

public class SpringDetector {

    /**
     * Uses a Groundhog or class derived from Groundhog:
     * 传入一个 Class ，要求该类有一个 int 入参数的构造器
     *
     * @param type
     * @param <T>
     * @throws Exception
     */
    public static <T extends Groundhog> void detectSpring(Class<T> type) throws Exception {
        Map<Groundhog, Prediction> map = new HashMap<Groundhog, Prediction>();

        Constructor<T> groundhogConst = type.getConstructor(int.class);
        for (int i = 0; i < 10; i++) {
            //往map里面放10个映射
            map.put(groundhogConst.newInstance(i), new Prediction());
        }

        print("map = " + map);

        //作者的意图是从map找出该3号土拨鼠对应的天气预报
        Groundhog gh = groundhogConst.newInstance(3);
        print("Looking up prediction for " + gh);

        if (map.containsKey(gh)) {
            print(map.get(gh));
        } else {
            print("Key not found: " + gh);
        }
    }

    public static void main(String[] args) throws Exception {
        detectSpring(Groundhog.class);
    }
}

class Groundhog2 extends Groundhog {

    public Groundhog2(int n) {
        super(n);
    }

    @Override
    public int hashCode() {
        return number;
    }

    @Override
    public boolean equals(Object o) {
        return o instanceof Groundhog2 && (number == ((Groundhog2) o).number);
    }
}

class SpringDetector2 {

    public static void main(String[] args) throws Exception {
        SpringDetector.detectSpring(Groundhog2.class);
    }
}