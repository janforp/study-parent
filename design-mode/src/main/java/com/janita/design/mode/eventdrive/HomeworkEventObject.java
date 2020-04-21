package com.janita.design.mode.eventdrive;

import java.util.EventObject;

/**
 * 类说明：HomeworkEventObject
 *
 * @author zhucj
 * @since 20200423
 */
public class HomeworkEventObject extends EventObject {

    public HomeworkEventObject(Teacher teacher) {
        super(teacher);
    }

    public Teacher getTeacher() {
        return (Teacher) super.getSource();
    }
}
