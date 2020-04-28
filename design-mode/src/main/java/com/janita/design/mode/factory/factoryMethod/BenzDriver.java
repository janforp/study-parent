package com.janita.design.mode.factory.factoryMethod;

/**
 * 类说明：BenzDriver
 *
 * @author zhucj
 * @since 20200423
 */
public class BenzDriver extends Driver {

    @Override
    public Car getCar() {
        return new Benz();
    }
}
