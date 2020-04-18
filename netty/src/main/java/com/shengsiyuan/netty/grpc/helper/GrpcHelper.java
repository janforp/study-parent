package com.shengsiyuan.netty.grpc.helper;

import com.shengsiyuan.netty.proto.StudentServiceGrpc;
import com.shengsiyuan.netty.proto.StudentServiceGrpc.StudentServiceBlockingStub;
import com.shengsiyuan.netty.proto.StudentServiceGrpc.StudentServiceStub;
import io.grpc.ManagedChannel;
import io.grpc.ManagedChannelBuilder;

/**
 * 类说明：
 *
 * @author zhucj
 * @since 20200423
 */
public class GrpcHelper {

    public static StudentServiceBlockingStub getBlockingStub() {
        final ManagedChannel managedChannel = ManagedChannelBuilder.forAddress("localhost", 8899).usePlaintext().build();
        return StudentServiceGrpc.newBlockingStub(managedChannel);
    }

    public static StudentServiceStub getAsyncStub() {
        final ManagedChannel managedChannel = ManagedChannelBuilder.forAddress("localhost", 8899).usePlaintext().build();
        return StudentServiceGrpc.newStub(managedChannel);
    }
}
