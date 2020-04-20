package com.janita.design.mode.proxy.cglib;

/**
 * 类说明：HelloService
 *
 * @author zhucj
 * @since 20200423
 */
public class HelloService {

    public void sayHello(String name) {
        String result = name + ": hello";
        System.out.println(result);
    }
}
