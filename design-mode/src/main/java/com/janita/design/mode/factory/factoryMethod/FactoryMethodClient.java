package com.janita.design.mode.factory.factoryMethod;

/**
 * 类说明：FactoryMethodClient
 *
 * @author zhucj
 * @since 20200423
 */
public class FactoryMethodClient {

    public static void main(String[] args) {
        //奔驰车司机
        Driver driver = new BenzDriver();
        //今天想做奥迪车
        Car car = driver.getCar();
        //开车
        car.drive();
    }
}
