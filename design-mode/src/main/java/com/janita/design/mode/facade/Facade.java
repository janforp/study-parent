package com.janita.design.mode.facade;

/**
 * 类说明：Facade
 *
 * @author zhucj
 * @since 20200423
 */
public class Facade {

    private Light light1, light2, light3;

    private Heater heater;

    private TV tv;

    public Facade() {
        light1 = new Light();
        light2 = new Light();
        light3 = new Light();
        heater = new Heater();
        tv = new TV();
    }

    public void open() {
        light1.open();
        light2.open();
        light3.open();
        heater.open();
        tv.open();
    }
}
