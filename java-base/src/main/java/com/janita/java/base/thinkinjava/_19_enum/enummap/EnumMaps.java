package com.janita.java.base.thinkinjava._19_enum.enummap;

import com.janita.java.base.thinkinjava._19_enum.enumset.AlarmPoints;

import java.util.EnumMap;
import java.util.Map;

import static com.janita.java.base.thinkinjava._19_enum.enumset.AlarmPoints.BATHROOM;
import static com.janita.java.base.thinkinjava._19_enum.enumset.AlarmPoints.KITCHEN;
import static com.janita.java.base.thinkinjava._19_enum.enumset.AlarmPoints.UTILITY;
import static com.janita.java.base.thinkinjava.util.Print.print;
import static com.janita.java.base.thinkinjava.util.Print.printnb;

/**
 * EnumMaps
 *
 * @author zhucj
 * @since 20200528
 */
interface Command {

    void action();
}

public class EnumMaps {

    public static void main(String[] args) {
        EnumMap<AlarmPoints, Command> em = new EnumMap<AlarmPoints, Command>(AlarmPoints.class);
        em.put(KITCHEN, () -> print("Kitchen fire!"));
        em.put(BATHROOM, () -> print("Bathroom alert!"));

        for (Map.Entry<AlarmPoints, Command> e : em.entrySet()) {
            printnb(e.getKey() + ": ");
            e.getValue().action();
        }
        try { // If there's no value for a particular key:
            em.get(UTILITY).action();
        } catch (Exception e) {
            print(e);
        }
    }
} /* Output:
BATHROOM: Bathroom alert!
KITCHEN: Kitchen fire!
java.lang.NullPointerException
*///:~
