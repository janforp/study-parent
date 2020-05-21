package com.janita.java.base.thinkinjava._15_genericity.mixin;

/**
 * 类说明：Mixins
 *
 * @author zhucj
 * @since 20200528
 */
interface TimeStamped {

    long getStamp();
}

class TimeStampedImp implements TimeStamped {

    private final long timeStamp;

    public TimeStampedImp() {
        timeStamp = System.currentTimeMillis();
    }

    public long getStamp() {
        return timeStamp;
    }
}

interface SerialNumbered {

    long getSerialNumber();
}

class SerialNumberedImp implements SerialNumbered {

    private static long counter = 1;

    private final long serialNumber = counter++;

    public long getSerialNumber() {
        return serialNumber;
    }
}

interface Basic {

    void set(String val);

    String get();
}

class BasicImp implements Basic {

    private String value;

    public void set(String val) {
        value = val;
    }

    public String get() {
        return value;
    }
}

class Mixin extends BasicImp implements TimeStamped, SerialNumbered {

    private TimeStamped timeStamp = new TimeStampedImp();

    private SerialNumbered serialNumber = new SerialNumberedImp();

    @Override
    public long getStamp() {
        return timeStamp.getStamp();
    }

    @Override
    public long getSerialNumber() {
        return serialNumber.getSerialNumber();
    }
}

public class Mixins {

    public static void main(String[] args) {
        Mixin mixin1 = new Mixin();
        Mixin mixin2 = new Mixin();
        mixin1.set("test string 1");
        mixin2.set("test string 2");
        System.out.println(mixin1.get() + " " + mixin1.getStamp() + " " + mixin1.getSerialNumber());
        System.out.println(mixin2.get() + " " + mixin2.getStamp() + " " + mixin2.getSerialNumber());
    }
}