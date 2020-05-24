package com.janita.java.base.thinkinjava._17_container;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import static com.janita.java.base.thinkinjava.util.Print.print;

/**
 * 类说明：CountedString
 *
 * @author zhucj
 * @since 20200528
 */
public class CountedString {

    private static List<String> created = new ArrayList<String>();

    private String string;

    /**
     * 该 string 在 created 集合中的个数
     */
    private int id = 0;

    public CountedString(String str) {
        string = str;
        created.add(string);
        // id is the total number of instances
        // of this string in use by CountedString:
        for (String s2 : created) {
            if (s2.equals(string)) {
                id++;
            }
        }
    }

    public String toString() {
        return "String: " + string + " id: " + id + " hashCode(): " + hashCode();
    }

    public int hashCode() {
        // The very simple approach:
        // return s.hashCode() * id;
        // Using Joshua Bloch's recipe:
        int result = 17;
        result = 37 * result + string.hashCode();
        result = 37 * result + id;
        return result;
    }

    public boolean equals(Object o) {
        return o instanceof CountedString
                && string.equals(((CountedString) o).string)
                && id == ((CountedString) o).id;
    }
}

class CountedStringTest {

    public static void main(String[] args) {
        Map<CountedString, Integer> map = new HashMap<CountedString, Integer>();
        CountedString[] countedStrings = new CountedString[5];

        for (int i = 0; i < countedStrings.length; i++) {
            //循环5次
            countedStrings[i] = new CountedString("hi");

            //map的key都是一个新的 new CountedString("hi")
            map.put(countedStrings[i], i); // Autobox int -> Integer
        }

        print(map);

        for (CountedString countedString : countedStrings) {
            print("Looking up " + countedString);
            print(map.get(countedString));
        }
    }

}