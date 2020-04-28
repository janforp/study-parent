package com.janita.design.mode.bridge.penexample;

/**
 * 类说明：ImplementorColor
 *
 * 这是一个变化的维度，因为我们可以有很多颜色
 *
 * @author zhucj
 * @since 20200423
 */
public abstract class ImplementorColor {

    /**
     * paint
     * @return 颜色
     */
    public abstract String doPaint();
}
