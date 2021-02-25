package com.janita.design.mode.bridge.ljz;

/**
 * 继承转组合
 *
 * @author zhucj
 * @since 20210225
 */

class Test2 {

}

interface Messager2 {

    void login(String username, String password);

    void sendMessage(String message);

    void sendPicture(Image image);

    abstract void playSound();

    abstract void drawShape();

    abstract void writeText();

    abstract void connect();
}

abstract class PCMessager2Base implements Messager2 {

    public void playSound() {
        //******
    }

    public void drawShape() {
        //******
    }

    public void writeText() {
        //******
    }

    public void connect() {
        //******
    }
}

abstract class MobileMessager2Base implements Messager2 {

    public void playSound() {
        //******
    }

    public void drawShape() {
        //******
    }

    public void writeText() {
        //******
    }

    public void connect() {
        //******
    }
}

//精简版本
class MessagerLite {

    /**
     * 此处在运行时可以是pc或者mobile版本
     */
    private Messager2 messager2;

    void login(String username, String password) {
        messager2.connect();
        //......
    }

    void sendMessage(String message) {
        messager2.writeText();
        //......
    }

    void sendPicture(Image image) {
        messager2.drawShape();
        //......
    }
}

//完美版本
class Messager2Perfect {

    /**
     * 此处在运行时可以是pc或者mobile版本
     */
    private Messager2 messager2;

    void login(String username, String password) {
        messager2.playSound();
        messager2.connect();
        //......
    }

    void sendMessage(String message) {
        messager2.playSound();
        messager2.writeText();
        //......
    }

    void sendPicture(Image image) {
        messager2.playSound();
        messager2.drawShape();
        //......
    }
}