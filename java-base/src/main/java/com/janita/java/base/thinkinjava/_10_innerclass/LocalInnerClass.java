package com.janita.java.base.thinkinjava._10_innerclass;

/**
 * 类说明：LocalInnerClass
 *
 * @author zhucj
 * @since 20200423
 */
public class LocalInnerClass {

    private int count = 0;

    public static void main(String[] args) {
        LocalInnerClass lic = new LocalInnerClass();
        Counter c1 = lic.getCounter("Local inner");
        Counter c2 = lic.getCounter2("Anonymous inner");
        for (int i = 0; i < 5; i++) {
            System.out.println(c1.next());
        }
        for (int i = 0; i < 5; i++) {
            System.out.println(c2.next());
        }
    }

    Counter getCounter(final  String name) {
        class LocalCounter implements Counter {

            @Override
            public int next() {
                System.out.println(name);
                return count ++;
            }
        }
        return new LocalCounter();
    }

    Counter getCounter2(final String name) {
        return new Counter() {

            {
                System.out.println("Counter()");
            }

            @Override
            public int next() {
                System.out.println(name);
                return count++;
            }
        };
    }
}

interface Counter {

    int next();
}
