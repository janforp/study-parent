package com.janita.java.base.thinkinjava._17_container;

import java.util.LinkedHashMap;

import static com.janita.java.base.thinkinjava.util.Print.print;

/**
 * 类说明：LinkedHashMapDemo
 *
 * @author zhucj
 * @since 20200528
 */
public class LinkedHashMapDemo {

    public static void main(String[] args) {
        CountingMapData countingMapData = new CountingMapData(9);

        LinkedHashMap<Integer, String> linkedMap = new LinkedHashMap<Integer, String>(countingMapData);
        print(linkedMap);

        // Least-recently-used order:
        //第三个参数传 true
        linkedMap = new LinkedHashMap<Integer, String>(16, 0.75f, true);
        linkedMap.putAll(countingMapData);
        print(linkedMap);
        for (int i = 0; i < 6; i++) // Cause accesses:
        {
            linkedMap.get(i);
        }
        print(linkedMap);
        linkedMap.get(0);
        print(linkedMap);
    }
} /* Output:
{0=A0, 1=B0, 2=C0, 3=D0, 4=E0, 5=F0, 6=G0, 7=H0, 8=I0}
{0=A0, 1=B0, 2=C0, 3=D0, 4=E0, 5=F0, 6=G0, 7=H0, 8=I0}
{6=G0, 7=H0, 8=I0, 0=A0, 1=B0, 2=C0, 3=D0, 4=E0, 5=F0}
{6=G0, 7=H0, 8=I0, 1=B0, 2=C0, 3=D0, 4=E0, 5=F0, 0=A0}
*///:~