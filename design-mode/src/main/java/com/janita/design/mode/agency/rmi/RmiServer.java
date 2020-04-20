package com.janita.design.mode.agency.rmi;

import lombok.SneakyThrows;

import java.rmi.Naming;
import java.rmi.registry.LocateRegistry;

/**
 * 类说明：RmiServer
 *
 * @author zhucj
 * @since 20200423
 */
public class RmiServer {

    @SneakyThrows
    public static void main(String[] args) {
        MyRemote service = new MyRemoteImpl();
        LocateRegistry.createRegistry(6600);
        Naming.rebind("rmi://localhost:6600/RemoteHello", service);
    }
}
