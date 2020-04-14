package com.shengsiyuan.netty.thrift;

import com.shengsiyuan.netty.thrift.gen.Person;
import com.shengsiyuan.netty.thrift.gen.PersonService;
import org.apache.thrift.TException;

/**
 * 类说明：
 *
 * @author zhucj
 * @since 20200423
 */
public class PersonServiceImpl implements PersonService.Iface {

    @Override
    public Person getPersonByUsername(String username) throws TException {
        System.out.println("Got Client Param: " + username);
        Person person = new Person();
        person.setUsername(username);
        person.setAge(20);
        person.setMarried(false);
        return person;
    }

    @Override
    public void savePerson(Person person) throws TException {
        System.out.println("Got Client Param: ");
        System.out.println(person.getUsername());
        System.out.println(person.getAge());
        System.out.println(person.isMarried());
    }
}
