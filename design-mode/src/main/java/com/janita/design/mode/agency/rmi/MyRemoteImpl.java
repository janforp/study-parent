package com.janita.design.mode.agency.rmi;

import lombok.SneakyThrows;

import java.rmi.Naming;
import java.rmi.RemoteException;
import java.rmi.registry.LocateRegistry;
import java.rmi.server.UnicastRemoteObject;

/**
 * 类说明：MyRemoteImpl
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

    @SneakyThrows
    public static void main(String[] args) {
        MyRemote service = new MyRemoteImpl();
        LocateRegistry.createRegistry(6600);
        Naming.rebind("rmi://localhost:6600/RemoteHello", service);
    }
}