package com.janita.java.base.entity;

/**
 * 类说明：Details
 *
 * @author zhucj
 * @since 20200423
 */
public class Details {

    public volatile int numberTimesInvoked;

    public int getNumberTimesInvoked() {

        return numberTimesInvoked;
    }

    public void setNumberTimesInvoked(int numberTimesInvoked) {

        this.numberTimesInvoked = numberTimesInvoked;
    }
}
