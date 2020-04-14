package com.shengsiyuan.netty.thrift;

import com.shengsiyuan.netty.thrift.gen.PersonService;
import org.apache.thrift.TProcessorFactory;
import org.apache.thrift.protocol.TCompactProtocol;
import org.apache.thrift.server.THsHaServer;
import org.apache.thrift.server.TServer;
import org.apache.thrift.transport.TFramedTransport;
import org.apache.thrift.transport.TNonblockingServerSocket;
import org.apache.thrift.transport.TTransportException;

/**
 * 类说明：
 *
 * @author zhucj
 * @since 20200423
 */
public class ThriftTestServer {

    public static void main(String[] args) throws TTransportException {
        //socket连接
        TNonblockingServerSocket socket = new TNonblockingServerSocket(8899);

        THsHaServer.Args arg = new THsHaServer.Args(socket).minWorkerThreads(2).maxWorkerThreads(4);

        //协议层
        arg.protocolFactory(new TCompactProtocol.Factory());

        //传输层
        arg.transportFactory(new TFramedTransport.Factory());

        //结合 spring 的时候，PersonServiceImpl 可以设置为单例，通过注入获取
        PersonService.Processor<PersonServiceImpl> processor = new PersonService.Processor<>(new PersonServiceImpl());
        arg.processorFactory(new TProcessorFactory(processor));

        //半同步半异步服务
        TServer server = new THsHaServer(arg);

        System.out.println("Thrift Server is Started");

        //死循环，异步非阻塞
        server.serve();
    }
}
