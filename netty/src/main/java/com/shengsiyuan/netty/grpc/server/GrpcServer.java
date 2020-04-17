package com.shengsiyuan.netty.grpc.server;

import com.shengsiyuan.netty.grpc.StudentServiceImpl;
import io.grpc.Server;
import io.grpc.ServerBuilder;

import java.io.IOException;

/**
 * 类说明：
 *
 * @author zhucj
 * @since 20200423
 */
public class GrpcServer {

    private Server server;

    public static void main(String[] args) throws IOException, InterruptedException {
        GrpcServer server = new GrpcServer();
        server.start();
        //不加这段就会直接结束
        server.awaitTermination();
    }

    private void start() throws IOException {
        //可以把实现丢进spring
        this.server = ServerBuilder.forPort(8899).addService(new StudentServiceImpl()).build();
        this.server.start();
        System.out.println("server started！");
    }

    private void stop() {
        if (this.server != null) {
            this.server.shutdown();
        }
    }

    private void awaitTermination() throws InterruptedException {
        if (this.server != null) {
            this.server.awaitTermination();
        }
    }
}
