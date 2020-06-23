package com.janita.java.ssy.jdk8;

import java.util.List;

/**
 * Company
 *
 * @author zhucj
 * @since 20200623
 */
public class Company {

    private String name;

    private List<Employee> employeeList;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public List<Employee> getEmployeeList() {
        return employeeList;
    }

    public void setEmployeeList(List<Employee> employeeList) {
        this.employeeList = employeeList;
    }
}
