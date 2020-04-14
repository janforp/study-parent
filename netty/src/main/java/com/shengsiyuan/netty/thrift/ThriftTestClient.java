package com.shengsiyuan.netty.thrift;

import com.shengsiyuan.netty.thrift.gen.Person;
import com.shengsiyuan.netty.thrift.gen.PersonService;
import org.apache.thrift.protocol.TCompactProtocol;
import org.apache.thrift.protocol.TProtocol;
import org.apache.thrift.transport.TFramedTransport;
import org.apache.thrift.transport.TSocket;
import org.apache.thrift.transport.TTransport;

/**
 * 类说明：
 *
 * @author zhucj
 * @since 20200423
 */
public class ThriftTestClient {

    public static void main(String[] args) {

        //与服务器保持一直
        TTransport transport = new TFramedTransport(new TSocket("localhost", 8899), 600);

        //与服务器保持一直
        TProtocol protocol = new TCompactProtocol(transport);

        PersonService.Client client = new PersonService.Client(protocol);

        try {
            //打开socket
            transport.open();

            //rpc调用
            Person person = client.getPersonByUsername("张三");

            System.out.println(person.getUsername());
            System.out.println(person.getAge());
            System.out.println(person.isMarried());

            System.out.println("------------");

            person = new Person();
            person.setUsername("李四");
            person.setAge(30);
            person.setMarried(true);

            //rpc调用
            client.savePerson(person);

        } catch (Exception ex) {
            throw new RuntimeException(ex.getMessage(), ex);
        } finally {
            transport.close();
        }
    }
}
