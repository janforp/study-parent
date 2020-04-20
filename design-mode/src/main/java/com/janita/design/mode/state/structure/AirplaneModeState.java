package com.janita.design.mode.state.structure;

/**
 * 类说明：AirplaneModeState
 *
 * @author zhucj
 * @since 20200423
 */
public class AirplaneModeState extends BaseState{

    @Override
    protected void call() {
        System.err.println("飞行模式无法打电话");
    }

    @Override
    protected void sendText() {
        System.err.println("飞行模式无法发短信");
    }

    @Override
    protected void useWeChat() {
        System.err.println("飞行模式无法发微信");
    }
}
