package com.janita.java.ssy.jdk8;

import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import java.util.Optional;

/**
 * _12_Optional
 *
 * @author zhucj
 * @since 20200623
 */
public class _12_Optional {
}

class OptionalTest {

    public static void main(String[] args) {
        Optional<String> optionalS = Optional.of("hello");
        optionalS.ifPresent(System.out::println);
        System.out.println("-----------");
        System.out.println(optionalS.orElse("world"));
        System.out.println("-----------");
        System.out.println(optionalS.orElseGet(() -> "niHao"));

        System.out.println("++++++++++++++");

        Optional<String> optionalEmpty = Optional.empty();
        optionalEmpty.ifPresent(System.out::println);
        System.out.println("-----------");
        System.out.println(optionalEmpty.orElse("world"));
        System.out.println("-----------");
        System.out.println(optionalEmpty.orElseGet(() -> "niHao"));

        System.out.println("++++++++++++++");
        //因为从db查询到的数据是不知道是否为空的
        Optional<String> optional = Optional.ofNullable("hello");
    }
}

class OptionalTest2 {

    public static void main(String[] args) {
        Company company = getCompany();
        Optional<Company> optionalCompany = Optional.ofNullable(company);
        List<Employee> employeeList = optionalCompany
                .map(theCompany -> theCompany.getEmployeeList())
                .orElse(Collections.emptyList());
        System.out.println(employeeList);


        Company withoutEmployees = getCompanyWithoutEmployees();
        optionalCompany = Optional.ofNullable(withoutEmployees);
        employeeList = optionalCompany
                .map(theCompany -> theCompany.getEmployeeList())
                .orElse(Collections.emptyList());
        System.out.println(employeeList);

    }

    private static Company getCompany() {
        Employee employee = new Employee();
        employee.setName("zhangsan");

        Employee employee2 = new Employee();
        employee2.setName("lisi");

        Company company = new Company();
        company.setName("company1");
        company.setEmployeeList(Arrays.asList(employee, employee2));
        return company;
    }

    private static Company getCompanyWithoutEmployees() {
        Company company = new Company();
        company.setName("company1");
        return company;
    }
}