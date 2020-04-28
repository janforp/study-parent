package com.janita.design.mode.bridge.carexample;

import com.janita.design.mode.bridge.carexample.product.*;
import com.janita.design.mode.bridge.carexample.car.*;


/**
 * 类说明：TestBridgePattern
 *
 * @author zhucj
 * @since 20200423
 */
public class TestBridgePattern {

    /**
     * 不同的汽车可以跟不同的 product 组装
     */
    public static void main(String[] args) {
        Product centralLocking = new CentralLocking("Central Locking System");
        Product gearLocking = new GearLocking("Gear Locking System");
        Car car = new BigWheel(centralLocking, "BigWheel xz model");
        car.produceProduct();
        car.assemble();
        car.printDetails();

        System.out.println("-----------------------------------------------------");

        car = new BigWheel(gearLocking, "BigWheel xz model");
        car.produceProduct();
        car.assemble();
        car.printDetails();

        System.out.println("-----------------------------------------------------");

        car = new Motoren(centralLocking, "Motoren lm model");
        car.produceProduct();
        car.assemble();
        car.printDetails();

        System.out.println();

        car = new Motoren(gearLocking, "Motoren lm model");
        car.produceProduct();
        car.assemble();
        car.printDetails();
    }
}
