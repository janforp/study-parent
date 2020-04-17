package com.janita.design.mode.command.device;

/**
 * 类说明：
 *
 * @author zhucj
 * @since 20200423
 */
public class Light {

    String loc = "";

    public Light(String loc) {
        this.loc = loc;
    }

    public void on() {

        System.out.println(loc + " On");
    }

    public void off() {

        System.out.println(loc + " Off");
    }
}
