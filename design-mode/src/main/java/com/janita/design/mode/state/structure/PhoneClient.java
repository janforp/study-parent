package com.janita.design.mode.state.structure;

/**
 * 类说明：PhoneClient
 *
 * @author zhucj
 * @since 20200423
 */
public class PhoneClient {

    public static void main(String[] args) {

        Phone phone = new Phone();
        phone.call();
        phone.sendText();
        phone.useWeChat();
        phone.takePhoto();

        //飞行模式
        phone.setToAirplaneMode();

        phone.call();
        phone.sendText();
        phone.useWeChat();
        phone.takePhoto();

        //从飞行模式切换到正常
        phone.setToNormalMode();

        phone.call();
        phone.sendText();
        phone.useWeChat();
        phone.takePhoto();
    }
}
