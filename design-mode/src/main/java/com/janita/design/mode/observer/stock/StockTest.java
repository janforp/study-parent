package com.janita.design.mode.observer.stock;

import com.janita.design.mode.observer.Observer;

/**
 * 类说明：
 *
 * @author zhucj
 * @since 20200423
 */
public class StockTest {

    public static void main(String[] args) {

        StockOriginData data = new StockOriginData();
        Observer stockTradeOne = new StockTradeOne();
        Observer sockTradeTwo = new StockTradeTwo();
        data.register(stockTradeOne);
        data.register(sockTradeTwo);

        //收到远程数据
        data.fetchDataFromRemote(9F);

        data.remove(sockTradeTwo);

        data.fetchDataFromRemote(100F);

    }
}
