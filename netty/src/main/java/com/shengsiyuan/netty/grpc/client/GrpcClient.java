package com.shengsiyuan.netty.grpc.client;

import com.shengsiyuan.netty.proto.MyRequest;
import com.shengsiyuan.netty.proto.MyResponse;
import com.shengsiyuan.netty.proto.StudentRequest;
import com.shengsiyuan.netty.proto.StudentResponse;
import com.shengsiyuan.netty.proto.StudentServiceGrpc;
import io.grpc.ManagedChannel;
import io.grpc.ManagedChannelBuilder;

import java.util.Iterator;

/**
 * 类说明：
 *
 * @author zhucj
 * @since 20200423
 */
public class GrpcClient {

    public static void main(String[] args) {
        ManagedChannel managedChannel = ManagedChannelBuilder.forAddress("localhost", 8899).usePlaintext().build();
        StudentServiceGrpc.StudentServiceBlockingStub blockingStub = StudentServiceGrpc.newBlockingStub(managedChannel);
        MyRequest request = MyRequest.newBuilder().setUsername("zs").build();
        MyResponse myResponse = blockingStub.getRealNameByUsername(request);
        System.out.println(myResponse.getRealname());

        myResponse = blockingStub.getRealNameByUsername(request);
        System.out.println(myResponse.getRealname());

        myResponse = blockingStub.getRealNameByUsername(request);
        System.out.println(myResponse.getRealname());

        myResponse = blockingStub.getRealNameByUsername(request);
        System.out.println(myResponse.getRealname());

        myResponse = blockingStub.getRealNameByUsername(request);
        System.out.println(myResponse.getRealname());

        myResponse = blockingStub.getRealNameByUsername(request);
        System.out.println(myResponse.getRealname());

        myResponse = blockingStub.getRealNameByUsername(request);
        System.out.println(myResponse.getRealname());

        myResponse = blockingStub.getRealNameByUsername(request);
        System.out.println(myResponse.getRealname());

        myResponse = blockingStub.getRealNameByUsername(request);
        System.out.println(myResponse.getRealname());

        System.out.println("--------------------------");

        //  rpc GetStudentsByAge(StudentRequest) returns (stream StudentResponse) {}
        Iterator<StudentResponse> iterator = blockingStub.getStudentsByAge(StudentRequest.newBuilder().setAge(20).build());
        iterator.forEachRemaining(studentResponse -> {
            System.out.println(studentResponse.getName() + "，" + studentResponse.getAge() + "，" + studentResponse.getCity());
        });
    }
}
