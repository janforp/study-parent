package com.janita.design.mode.bridge.product;

import com.janita.design.mode.bridge.Product;

/**
 * 类说明：GearLocking
 *
 * @author zhucj
 * @since 20200423
 */
public class GearLocking implements Product {

    private final String productName;

    public GearLocking(String productName) {
        this.productName = productName;
    }

    @Override
    public String productName() {
        return productName;
    }

    @Override
    public void produce() {
        System.out.println("Producing Gear Locking System");
    }
}