package com.janita.java.base.thinkinjava._10_innerclass.controlframenwork;

/**
 * 类说明：GreenHouseControllers
 *
 * @author zhucj
 * @since 20200423
 */
public class GreenHouseControllers extends Controller {

    private boolean light = false;

    public class LightOn extends Event {

        public LightOn(long delayTime) {
            super(delayTime);
        }

        @Override
        public void action() {
            light = true;
        }

        @Override
        public String toString() {
            return "打开 灯";
        }
    }

    public class LightOff extends Event {

        public LightOff(long delayTime) {
            super(delayTime);
        }

        @Override
        public void action() {
            light = false;
        }

        @Override
        public String toString() {
            return "关闭 灯";
        }
    }

    private boolean water = false;

    public class WaterOn extends Event {

        public WaterOn(long delayTime) {
            super(delayTime);
        }

        @Override
        public void action() {
            water = true;
        }

        @Override
        public String toString() {
            return "打开热水器";
        }
    }

    public class WaterOff extends Event {

        public WaterOff(long delayTime) {
            super(delayTime);
        }

        @Override
        public void action() {
            water = false;
        }

        @Override
        public String toString() {
            return "关闭 热水器";
        }
    }

    public class Bell extends Event {

        public Bell(long delayTime) {
            super(delayTime);
        }

        @Override
        public void action() {
            addEvent(new Bell(delayTime));
        }

        @Override
        public String toString() {
            return "玲响了";
        }
    }

    public class Restart extends Event {

        private Event[] events;

        public Restart(long delayTime, Event[] events) {
            super(delayTime);
            this.events = events;
            for (Event event : events) {
                addEvent(event);
            }
        }

        @Override
        public void action() {
            for (Event event : events) {
                event.start();
                addEvent(event);
            }
            start();
            addEvent(this);
        }

        @Override
        public String toString() {
            return "重启";
        }
    }

    public static class Terminate extends Event {

        public Terminate(long delayTime) {
            super(delayTime);
        }

        @Override
        public void action() {
            System.exit(0);
        }

        @Override
        public String toString() {
            return "关闭系统";
        }
    }
}

class GreenhouseControllerTest {

    public static void main(String[] args) {
        GreenHouseControllers controllers = new GreenHouseControllers();
        controllers.addEvent(controllers.new Bell(900));
        Event[] events = {
                controllers.new LightOn(200),
                controllers.new LightOff(400),
                controllers.new WaterOn(600),
                controllers.new WaterOff(800),

        };
        controllers.addEvent(controllers.new Restart(2000, events));
        controllers.run();
    }
}