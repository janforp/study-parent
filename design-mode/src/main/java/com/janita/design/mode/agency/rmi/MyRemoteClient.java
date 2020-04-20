package com.janita.design.mode.agency.rmi;

import lombok.SneakyThrows;

import java.rmi.Naming;
import java.util.UUID;

/**
 * 类说明：MyRemoteClient
 *
 * @author zhucj
 * @since 20200423
 */
public class MyRemoteClient {

    public static void main(String[] args) {
        new MyRemoteClient().go();
    }

    @SneakyThrows
    private void go() {
        //远端对象本地化
        MyRemote service = (MyRemote) Naming.lookup("rmi://localhost:6600/RemoteHello");
        String s = service.sayHello(UUID.randomUUID().toString());
        System.out.println(s);
    }
}
