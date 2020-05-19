package com.janita.java.base.thinkinjava._14_type_param;

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;
import java.lang.reflect.Proxy;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;

/**
 * 类说明：SnowRemovalRobot
 *
 * @author zhucj
 * @since 20200528
 */
public class SnowRemovalRobot implements Robot {

    private String name;

    public SnowRemovalRobot(String name) {
        this.name = name;
    }

    @Override
    public String name() {
        return name;
    }

    @Override
    public String model() {
        return "SnowBot Series 11";
    }

    @Override
    public List<Operation> operations() {
        return Arrays.asList(
                new Operation() {
                    @Override
                    public String description() {
                        return name + " can shovel snow";
                    }

                    @Override
                    public void command() {
                        System.out.println(name + " shoveling snow");
                    }
                },
                new Operation() {
                    @Override
                    public String description() {
                        return name + " can chip ice";
                    }

                    @Override
                    public void command() {
                        System.out.println(name + " chipping ice");
                    }
                },
                new Operation() {
                    @Override
                    public String description() {
                        return name + " can clear the roof";
                    }

                    @Override
                    public void command() {
                        System.out.println(name + " clearing roof");
                    }
                }
        );
    }

    public static void main(String[] args) {
        Robot.Test.test(new SnowRemovalRobot("Slusher"));
    }
}

interface Robot {

    String name();

    String model();

    List<Operation> operations();

    class Test {

        public static void test(Robot r) {
            if (r instanceof Null) {
                System.out.println("[Null Robot]");
            }
            System.out.println("Robot name: " + r.name());
            System.out.println("Robot model: " + r.model());
            for (Operation operation : r.operations()) {
                System.out.println(operation.description());
                operation.command();
            }
        }
    }
}

interface Operation {

    String description();

    void command();
}

class NullRobot {

    public static Robot newNullRobot(Class<? extends Robot> type) {
        return (Robot) Proxy.newProxyInstance(
                NullRobot.class.getClassLoader(),
                new Class[] { Null.class, Robot.class },
                new NullRobotProxyHandler(type));
    }

    public static void main(String[] args) {

        Robot[] robots = {
                new SnowRemovalRobot("SnowBee"),
                newNullRobot(SnowRemovalRobot.class)
        };
        //TODO
        for (Robot robot : robots) {
            System.out.println("开始测试下一个机器人");
            Robot.Test.test(robot);
        }
    }
}

class NullRobotProxyHandler implements InvocationHandler {

    private String nullName;

    private Robot proxied = new NRobot();

    NullRobotProxyHandler(Class<? extends Robot> type) {
        nullName = type.getSimpleName() + " NullRobot";
    }

    private class NRobot implements Null, Robot {

        @Override
        public String name() {
            return nullName;
        }

        @Override
        public String model() {
            return nullName;
        }

        @Override
        public List<Operation> operations() {
            return Collections.emptyList();
        }
    }

    @Override
    public Object invoke(Object proxy, Method method, Object[] args) throws Throwable {
        return method.invoke(proxied, args);
    }
}