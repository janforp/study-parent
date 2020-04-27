package com.janita.design.mode.facade;

/**
 * 类说明：NoFacadeClient
 *
 * @author zhucj
 * @since 20200423
 */
public class NoFacadeClient {

    /**
     * 客户端需要与很多子系统交互
     */
    public static void main(String[] args) {

        Light light1 = new Light();
        Light light2 = new Light();
        Light light3 = new Light();
        Heater heater = new Heater();
        TV tv = new TV();

        light1.open();
        light2.open();
        light3.open();
        heater.open();
        tv.open();
    }
}
