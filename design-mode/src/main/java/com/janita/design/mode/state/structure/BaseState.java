package com.janita.design.mode.state.structure;

/**
 * 类说明：BaseState
 *
 * @author zhucj
 * @since 20200423
 */
public abstract class BaseState {

    protected void call() {
        throw new UnsupportedOperationException("不支持打电话");
    }

    protected void sendText() {
        throw new UnsupportedOperationException("不支持发短信");
    }

    protected void useWeChat() {
        throw new UnsupportedOperationException("不支持发微信");
    }

    protected void takePhoto() {
        System.out.println("照相");
    }
}
