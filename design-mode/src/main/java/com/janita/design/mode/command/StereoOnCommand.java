package com.janita.design.mode.command;

import com.janita.design.mode.command.device.Stereo;

/**
 * 类说明：
 *
 * @author zhucj
 * @since 20200423
 */
public class StereoOnCommand implements Command {

    private Stereo stereo;

    public StereoOnCommand(Stereo stereo) {
        this.stereo = stereo;
    }

    @Override
    public void execute() {
        stereo.On();
        stereo.SetCd();
    }

    @Override
    public void undo() {
        stereo.Off();
    }
}
