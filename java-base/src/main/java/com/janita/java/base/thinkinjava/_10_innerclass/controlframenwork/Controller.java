package com.janita.java.base.thinkinjava._10_innerclass.controlframenwork;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

/**
 * 类说明：Controller
 *
 * @author zhucj
 * @since 20200423
 */
public class Controller {

    private List<Event> eventList = new ArrayList<>();

    public void addEvent(Event c) {
        eventList.add(c);
    }

    public void run() {
        while (eventList.size() > 0) {
            for (Event event : new ArrayList<>(eventList)) {
                if (event.ready()) {
                    System.out.println(event);
                    event.action();
                    eventList.remove(event);
                }
            }
        }
    }
}
