package com.janita.design.mode.state.structure;

/**
 * 类说明：Phone
 *
 * @author zhucj
 * @since 20200423
 */
public class Phone {

    private BaseState baseState;

    public Phone() {
        this.baseState = new NormalState();
    }

    public void setToAirplaneMode() {
        this.baseState = new AirplaneModeState();
    }

    public void setToNormalMode() {
        this.baseState = new NormalState();
    }

    protected void call() {
        baseState.call();
    }

    protected void sendText() {
        baseState.sendText();
    }

    protected void useWeChat() {
        baseState.useWeChat();
    }

    protected void takePhoto() {
        baseState.takePhoto();
    }
}
