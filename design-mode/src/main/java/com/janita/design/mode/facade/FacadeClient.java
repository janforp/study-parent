package com.janita.design.mode.facade;

/**
 * 类说明：FacadeCilent
 *
 * @author zhucj
 * @since 20200423
 */
public class FacadeClient {

    public static void main(String[] args) {
        Facade facade = new Facade();
        facade.open();
    }
}
