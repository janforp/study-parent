package com.janita.design.mode.proxy.cglib;

/**
 * 类说明：CgLibTest
 *
 * @author zhucj
 * @since 20200423
 */
public class CgLibTest {

    public static void main(String[] args) {
        HelloService helloService = CgLibProxy.getInstance().getProxy(HelloService.class);
        helloService.sayHello("我");
    }
}
