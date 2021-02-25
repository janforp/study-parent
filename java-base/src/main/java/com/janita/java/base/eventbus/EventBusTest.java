package com.janita.java.base.eventbus;

import com.google.common.eventbus.EventBus;
import com.google.common.eventbus.Subscribe;
import lombok.Data;

/**
 * EventBusTest
 *
 * @author zhucj
 * @since 20210225
 */
@SuppressWarnings("all")
public class EventBusTest {

    public static void main(String[] args) {
        EventBus eventBus = new EventBus();
        eventBus.register(new Subscriber());
        eventBus.post(new EventOne("测试个锤子"));
        eventBus.post(new EventOne("测试个果子"));
    }

    private static class Subscriber {

        @Subscribe
        public void subscriber1(EventOne eventOne) {
            System.out.println("1st event" + eventOne.getMessage());
        }

        @Subscribe
        public void subscriber2(EventTwo eventTwo) {
            System.out.println("1st event" + eventTwo.getMessage());
        }
    }

    @Data
    private static class EventOne {

        private String message;

        public EventOne(String message) {
            this.message = message;
        }
    }

    @Data
    private static class EventTwo {

        private String message;

        public EventTwo(String message) {
            this.message = message;
        }
    }
}