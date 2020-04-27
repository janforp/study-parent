package com.janita.design.mode.bridge.car;

import com.janita.design.mode.bridge.Car;
import com.janita.design.mode.bridge.Product;

/**
 * 类说明：BigWheel
 *
 * @author zhucj
 * @since 20200423
 */
public class BigWheel extends Car {

    private final Product product;

    private final String carType;

    public BigWheel(Product product, String carType) {
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