package com.janita.design.mode.proxy.javaproxy;

/**
 * 类说明：
 *
 * @author zhucj
 * @since 20200326
 */
public class Teacher implements People{

    @Override
    public String work() {
        System.out.println("老师教书育人...");
        return "教书";
    }
}