package com.janita.design.mode.visit;

/**
 * 类说明：MainTest
 *
 * @author zhucj
 * @since 20200423
 */
public class MainTest {

    public static void main(String[] args) {

        EmployeeGroup employeeGroup = new EmployeeGroup();
        employeeGroup.attach(new Employee("Tom", 4500, 8, 1));
        employeeGroup.attach(new Employee("Jerry", 6500, 10, 2));
        employeeGroup.attach(new Employee("Jack", 9600, 12, 3));

        //发放补偿金啦
        employeeGroup.accept(new CompensationVisitor());

        System.out.println("====================");

        //开会
        employeeGroup.accept(new NoticeMeetingVisitor());
    }

}
