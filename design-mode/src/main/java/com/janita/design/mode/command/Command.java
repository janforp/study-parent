package com.janita.design.mode.command;

/**
 * 类说明：
 *
 * @author zhucj
 * @since 20200423
 */
public interface Command {

    void execute();

    void undo();
}
