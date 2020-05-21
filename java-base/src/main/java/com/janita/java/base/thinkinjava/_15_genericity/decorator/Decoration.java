package com.janita.java.base.thinkinjava._15_genericity.decorator;

/**
 * 类说明：Decoration
 *
 * @author zhucj
 * @since 20200528
 */

class Basic {

    private String value;

    public void set(String val) {
        value = val;
    }

    public String get() {
        return value;
    }
}

class Decorator extends Basic {

    protected Basic basic;

    public Decorator(Basic basic) {
        this.basic = basic;
    }

    public void set(String val) {
        basic.set(val);
    }

    public String get() {
        return basic.get();
    }
}

class TimeStamped extends Decorator {

    private final long timeStamp;

    public TimeStamped(Basic basic) {
        super(basic);
        timeStamp = System.currentTimeMillis();
    }

    public long getStamp() {
        return timeStamp;
    }
}

class SerialNumbered extends Decorator {

    private static long counter = 1;

    private final long serialNumber = counter++;

    public SerialNumbered(Basic basic) {
        super(basic);
    }

    public long getSerialNumber() {
        return serialNumber;
    }
}

public class Decoration {

    public static void main(String[] args) {
        TimeStamped t = new TimeStamped(new Basic());
        TimeStamped t2 = new TimeStamped(new SerialNumbered(new Basic()));
        //可以添加多几层，但是最后一层的方法是可视的
        //! t2.getSerialNumber(); // Not available
        SerialNumbered s = new SerialNumbered(new Basic());
        SerialNumbered s2 = new SerialNumbered(new TimeStamped(new Basic()));
        //! s2.getStamp(); // Not available

        long stamp = t2.getStamp();
        String s1 = t2.get();

        long serialNumber = s2.getSerialNumber();
        String s3 = s2.get();
    }
}