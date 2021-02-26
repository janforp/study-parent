package com.janita.design.mode.bridge.ljz;

/**
 * Messager
 *
 * @author zhucj
 * @since 20210225
 */

class Test {

    public static void main(String[] args) {
        Messager messager = new MobileMessagerPerfect();
        messager.playSound();
        messager.drawShape();
        messager.writeText();
        messager.connect();
    }
}

interface Messager {

    void login(String username, String password);

    void sendMessage(String message);

    void sendPicture(Image image);

    abstract void playSound();

    abstract void drawShape();

    abstract void writeText();

    abstract void connect();
}

abstract class PCMessagerBase implements Messager {

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

abstract class MobileMessagerBase implements Messager {

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

//PC精简版本
class PCMessagerLite extends PCMessagerBase {

    @Override
    public void login(String username, String password) {
        connect();
        //......
    }

    @Override
    public void sendMessage(String message) {
        writeText();
        //......
    }

    @Override
    public void sendPicture(Image image) {
        drawShape();
        //......
    }
}

//PC完美版本
class PCMessagerPerfect extends PCMessagerBase {

    @Override
    public void login(String username, String password) {
        playSound();
        connect();
        //......
    }

    @Override
    public void sendMessage(String message) {
        playSound();
        writeText();
        //......
    }

    @Override
    public void sendPicture(Image image) {
        playSound();
        drawShape();
        //......
    }
}

//MB精简版本
class MobileMessagerLite extends MobileMessagerBase {

    @Override
    public void login(String username, String password) {
        connect();
        //......
    }

    @Override
    public void sendMessage(String message) {
        writeText();
        //......
    }

    @Override
    public void sendPicture(Image image) {
        drawShape();
        //......
    }
}

//MB完美版本
class MobileMessagerPerfect extends MobileMessagerBase {

    @Override
    public void login(String username, String password) {
        playSound();
        connect();
        //......
    }

    @Override
    public void sendMessage(String message) {
        playSound();
        writeText();
        //......
    }

    @Override
    public void sendPicture(Image image) {
        playSound();
        drawShape();
        //......
    }
}
