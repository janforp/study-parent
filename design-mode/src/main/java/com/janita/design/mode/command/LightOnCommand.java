package com.janita.design.mode.command;

import com.janita.design.mode.command.device.Light;

/**
 * 类说明：
 *
 * @author zhucj
 * @since 20200423
 */
public class LightOnCommand implements Command {

    private Light light;

    public LightOnCommand(Light light) {
        this.light = light;
    }

    @Override
    public void execute() {
        light.on();
    }

    @Override
    public void undo() {
        light.off();
    }
}
