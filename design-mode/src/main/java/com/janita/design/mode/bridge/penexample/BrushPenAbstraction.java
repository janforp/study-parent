package com.janita.design.mode.bridge.penexample;

/**
 * 类说明：BrushPenAbstraction
 * 另外一个变化的维度
 *
 * @author zhucj
 * @since 20200423
 */
public abstract class BrushPenAbstraction {

    protected ImplementorColor color;

    public void setColor(ImplementorColor color) {
        this.color = color;
    }

    /**
     * 具体怎么画，交给实现去决定
     */
    public abstract void operationDraw();
}
