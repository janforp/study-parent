package com.janita.design.mode.visit;

import java.util.HashMap;
import java.util.Map;

/**
 * 类说明：Employees
 *
 * @author zhucj
 * @since 20200423
 */
public class EmployeeGroup {

    private final Map<String, Employee> employeeMap;

    public EmployeeGroup() {
        employeeMap = new HashMap<>();
    }

    public void attach(Employee employee) {
        employeeMap.put(employee.getName(), employee);
    }

    public void detach(Employee employee) {
        employeeMap.remove(employee.getName());
    }

    public Employee getEmployee(String name) {
        return employeeMap.get(name);
    }

    public void accept(Visitor visitor) {
        for (Employee employee : employeeMap.values()) {
            employee.accept(visitor);
        }
    }
}
