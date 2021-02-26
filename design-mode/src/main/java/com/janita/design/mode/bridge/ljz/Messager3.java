package com.janita.design.mode.bridge.ljz;

/**
 * 继承转组合
 *
 * @author zhucj
 * @since 20210225
 */

class Test3 {

    public static void main(String[] args) {
        MobileMessagerPerfect messagerPerfect = new MobileMessagerPerfect();
    }
}

abstract class Messager3 {

    /**
     * 此处在运行时可以是pc或者mobile版本
     */
    protected Messager3Imp messager3Imp;

    abstract void login(String username, String password);

    abstract void sendMessage(String message);

    abstract void sendPicture(Image image);

}

interface Messager3Imp {

    void playSound();

    void drawShape();

    void writeText();

    void connect();
}

abstract class PCMessager3 implements Messager3Imp {

    @Override
    public void playSound() {
        System.out.println("PC 版本 唱歌");
        //******
    }

    @Override
    public void drawShape() {
        System.out.println("PC 版本 画图");
        //******
    }

    @Override
    public void writeText() {
        System.out.println("PC 版本 写字");
        //******
    }

    @Override
    public void connect() {
        System.out.println("PC 版本 连接网络");
        //******
    }
}

abstract class MobileMessager3 implements Messager3Imp {

    @Override
    public void playSound() {
        System.out.println("MOBILE 版本 唱歌");
        //******
    }

    @Override
    public void drawShape() {
        System.out.println("MOBILE 版本 画图");
        //******
    }

    @Override
    public void writeText() {
        System.out.println("MOBILE 版本 写字");
        //******
    }

    @Override
    public void connect() {
        System.out.println("MOBILE 版本 连接网络");
        //******
    }
}

//精简版本
class Messager3Lite extends Messager3 {

    @Override
    public void login(String username, String password) {
        messager3Imp.connect();
        //......
    }

    @Override
    public void sendMessage(String message) {
        messager3Imp.writeText();
        //......
    }

    @Override
    public void sendPicture(Image image) {
        messager3Imp.drawShape();
        //......
    }
}

//完美版本
class Messager3Perfect extends Messager3 {

    @Override
    public void login(String username, String password) {
        messager3Imp.playSound();
        messager3Imp.connect();
        //......
    }

    @Override
    public void sendMessage(String message) {
        messager3Imp.playSound();
        messager3Imp.writeText();
        //......
    }

    @Override
    public void sendPicture(Image image) {
        messager3Imp.playSound();
        messager3Imp.drawShape();
        //......
    }
}