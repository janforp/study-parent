package com.janita.design.mode.eventdrive;

import java.util.EventListener;

/**
 * 类说明：
 *
 * @author zhucj
 * @since 20200423
 */
public interface HomeworkListener extends EventListener {

    void update(HomeworkEventObject o, Object arg);
}
