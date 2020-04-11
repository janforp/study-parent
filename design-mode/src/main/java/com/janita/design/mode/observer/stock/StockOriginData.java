package com.janita.design.mode.observer.stock;

import com.janita.design.mode.observer.Observer;
import com.janita.design.mode.observer.Subject;

import java.util.ArrayList;
import java.util.List;

/**
 * 类说明：
 *
 * @author zhucj
 * @since 20200423
 */
public class StockOriginData implements Subject {

    private float stock;

    private List<Observer> observerList;

    public StockOriginData() {
        observerList = new ArrayList<>();
    }

    @Override
    public void register(Observer observer) {
        observerList.add(observer);
    }

    @Override
    public void remove(Observer observer) {
        observerList.remove(observer);
    }

    @Override
    public void remind() {
        for (Observer observer : observerList) {
            observer.update(stock);
        }
    }

    public void fetchDataFromRemote(float stock) {
        this.stock = stock;
        remind();
    }
}
