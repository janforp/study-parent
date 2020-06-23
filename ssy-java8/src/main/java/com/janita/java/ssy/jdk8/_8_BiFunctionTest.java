package com.janita.java.ssy.jdk8;

import java.util.Arrays;
import java.util.List;
import java.util.function.BiFunction;
import java.util.stream.Collectors;

/**
 * _8_BiFunctionTest
 *
 * @author zhucj
 * @since 20200623
 */
public class _8_BiFunctionTest {
}

class PersonTest {

    public static void main(String[] args) {
        Person person1 = new Person("zhansan", 20);
        Person person2 = new Person("lisi", 30);
        Person person3 = new Person("wangwu", 40);
        List<Person> personList = Arrays.asList(person1, person2, person3);

        PersonTest test = new PersonTest();
        List<Person> persons = test.getPersonsByUsername("zhansan", personList);
        System.out.println(persons);

        List<Person> persons1 = test.getPersonsByAge(20, personList);
        System.out.println(persons1);

        //下面是传递不同的行为
        List<Person> targetList = test.getPersonsByAge2(20, personList,
                (age, personLists) -> personLists.stream()
                        .filter(person -> person.getAge() > age)
                        .collect(Collectors.toList()));
        System.out.println(targetList);

        targetList = test.getPersonsByAge2(20, personList,
                (age, personLists) -> personLists.stream()
                        .filter(person -> person.getAge() <= age)
                        .collect(Collectors.toList()));
        System.out.println(targetList);
    }

    public List<Person> getPersonsByUsername(String username, List<Person> sourcePersonList) {
        return sourcePersonList.stream()
                .filter(person -> person.getUsername().equals(username))
                .collect(Collectors.toList());
    }

    public List<Person> getPersonsByAge(int age, List<Person> sourcePersonList) {
        BiFunction<Integer, List<Person>, List<Person>> biFunction =
                (ageOfPerson, personList) -> personList.stream()
                        .filter(person -> person.getAge() > ageOfPerson)
                        .collect(Collectors.toList());
        return biFunction.apply(age, sourcePersonList);
    }

    public List<Person> getPersonsByAge2(int age, List<Person> sourcePersonList,
                                         BiFunction<Integer, List<Person>, List<Person>> biFunction) {
        return biFunction.apply(age, sourcePersonList);
    }
}