package com.janita.design.mode.bridge;

import com.janita.design.mode.bridge.penexample.ImplementorColor;

/**
 * 类说明：OnCreteImplementorRed
 *
 * @author zhucj
 * @since 20200423
 */
public class OnCreteImplementorRed extends ImplementorColor {

    @Override
    public String doPaint() {
        return "红色";
    }
}
