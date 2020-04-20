package com.janita.design.mode.proxy.rmi;

import lombok.SneakyThrows;

import java.rmi.Naming;
import java.util.UUID;

/**
 * 类说明：MyRemoteClient
 *
 * 接口使用方
 *
 * @author zhucj
 * @since 20200423
 */
public class RmiClient {

    public static void main(String[] args) {
        new RmiClient().go();
    }

    @SneakyThrows
    private void go() {
        //远端对象本地化
        MyRemote service = (MyRemote) Naming.lookup("rmi://localhost:6600/RemoteHello");
        String s = service.sayHello(UUID.randomUUID().toString());
        System.out.println(s);
    }
}
