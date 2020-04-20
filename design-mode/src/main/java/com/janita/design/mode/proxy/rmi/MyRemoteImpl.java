package com.janita.design.mode.proxy.rmi;

import java.rmi.RemoteException;
import java.rmi.server.UnicastRemoteObject;

/**
 * 类说明：MyRemoteImpl
 *
 * 接口提供方
 *
 * @author zhucj
 * @since 20200423
 */
public class MyRemoteImpl extends UnicastRemoteObject implements MyRemote {

    protected MyRemoteImpl() throws RemoteException {
        super();
    }

    @Override
    public String sayHello(String name) {
        return name + "： hello world";
    }
}