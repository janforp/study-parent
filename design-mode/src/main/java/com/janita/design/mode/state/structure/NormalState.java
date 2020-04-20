package com.janita.design.mode.state.structure;

/**
 * 类说明：开机有网络的状态
 *
 * @author zhucj
 * @since 20200423
 */
public class NormalState extends BaseState{

    @Override
    protected void call() {
        System.out.println("正常状态，可以打电话");
    }

    @Override
    protected void sendText() {
        System.out.println("正常状态，可以发短信");
    }

    @Override
    protected void useWeChat() {
        System.out.println("正常状态，可以发微信");
    }
}
