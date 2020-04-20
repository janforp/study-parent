package com.janita.design.mode.agency.rmi;

import java.rmi.Remote;
import java.rmi.RemoteException;

/**
 * 类说明：MyRemote
 *
 * @author zhucj
 * @since 20200423
 */
public interface MyRemote extends Remote {

    /**
     * xxx
     *
     * @param name xx
     * @return xx
     * @throws RemoteException xx
     */
    String sayHello(String name) throws RemoteException;
}