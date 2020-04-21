package com.janita.design.mode.eventdrive;

import java.util.HashSet;
import java.util.Set;

/**
 * 类说明：Teacher
 *
 * @author zhucj
 * @since 20200423
 */
public class Teacher {

    private final String name;

    /**
     * 教师类要维护一个自己监听器（学生）的列表，为什么？
     * 在观察者模式中，教师是被观察者，继承自java.util.Observable，Observable中含了这个列表
     * 现在我们没有这个列表了，所以要自己创建一个
     */
    private final Set<HomeworkListener> homeworkListenerList;

    public String getName() {
        return this.name;
    }

    public Teacher(String name) {
        this.name = name;
        this.homeworkListenerList = new HashSet<>();
    }

    public void setHomework(String homework) {
        System.out.printf("%s老师布置了作业%s \n", this.name, homework);
        HomeworkEventObject event = new HomeworkEventObject(this);
        /*
         * 在观察者模式中，我们直接调用Observable的notifyObservers来通知被观察者
         * 现在我们只能自己通知了~~
         */
        for (HomeworkListener listener : homeworkListenerList) {
            listener.update(event, homework);
        }
    }

    public void addObserver(HomeworkListener homeworkListener) {
        homeworkListenerList.add(homeworkListener);
    }
}