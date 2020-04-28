package com.janita.design.mode.bridge.carexample.car;

import com.janita.design.mode.bridge.carexample.Car;
import com.janita.design.mode.bridge.carexample.Product;

/**
 * 类说明：Motoren
 *
 * @author zhucj
 * @since 20200423
 */
public class Motoren extends Car {

    private final Product product;

    private final String carType;

    public Motoren(Product product, String carType) {
        super(product, carType);
        this.product = product;
        this.carType = carType;
    }

    @Override
    public void assemble() {
        System.out.println("Assembling " + product.productName() + " for " + carType);
    }

    @Override
    public void produceProduct() {
        product.produce();
        System.out.println("Modifing product " + product.productName() + " according to " + carType);
    }

}