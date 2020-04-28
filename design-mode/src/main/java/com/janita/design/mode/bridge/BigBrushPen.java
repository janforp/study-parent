package com.janita.design.mode.bridge;

import com.janita.design.mode.bridge.penexample.BrushPenAbstraction;

/**
 * 类说明：BigBrushPenRefinedAbstraction
 * 毛笔的具体实现，这是一支能画粗线条的毛笔
 *
 * 粗线条的毛笔能够画出很多不同的颜色，具体的颜色由它持有的颜色引用的实现决定
 *
 * @author zhucj
 * @since 20200423
 */
public class BigBrushPen extends BrushPenAbstraction {

    @Override
    public void operationDraw() {
        System.out.println("一个能画出" + color.doPaint() + "的粗毛笔");
    }
}
