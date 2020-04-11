package com.janita.design.mode.observer;

/**
 * 类说明：
 *
 * @author zhucj
 * @since 20200423
 */
public interface Subject {

    void register(Observer observer);

    void remove(Observer observer);

    void remind();
}
