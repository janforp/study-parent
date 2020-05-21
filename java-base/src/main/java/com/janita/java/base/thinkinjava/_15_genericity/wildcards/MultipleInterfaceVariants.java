package com.janita.java.base.thinkinjava._15_genericity.wildcards;

/**
 * 类说明：MultipleInterfaceVariants
 *
 * @author zhucj
 * @since 20200528
 */
public class MultipleInterfaceVariants {

}

interface Payable<T> {

}

class Employee implements Payable<Employee> {

}

//class Hourly extends Employee implements Payable<Hourly> {}

class Teacher implements Payable {

}

/**
 * 如果都去掉泛型，则可以编译通过
 */
class Profession extends Teacher implements Payable {

}