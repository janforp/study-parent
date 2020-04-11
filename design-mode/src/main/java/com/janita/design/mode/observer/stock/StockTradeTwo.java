package com.janita.design.mode.observer.stock;

import com.janita.design.mode.observer.Observer;

/**
 * 类说明：
 *
 * @author zhucj
 * @since 20200423
 */
public class StockTradeTwo implements Observer {

    @Override
    public void update(float stock) {
        handlerStock(stock);
    }

    private void handlerStock(float stock) {
        System.out.println("我们的价格是 " + (stock + 100));
    }
}
