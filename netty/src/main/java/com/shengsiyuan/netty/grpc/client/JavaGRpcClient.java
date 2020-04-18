package com.shengsiyuan.netty.grpc.client;

import com.shengsiyuan.netty.grpc.helper.GrpcHelper;
import com.shengsiyuan.netty.proto.MyRequest;
import com.shengsiyuan.netty.proto.MyResponse;
import com.shengsiyuan.netty.proto.StreamRequest;
import com.shengsiyuan.netty.proto.StreamResponse;
import com.shengsiyuan.netty.proto.StudentRequest;
import com.shengsiyuan.netty.proto.StudentResponse;
import com.shengsiyuan.netty.proto.StudentResponseList;
import com.shengsiyuan.netty.proto.StudentServiceGrpc;
import io.grpc.ManagedChannel;
import io.grpc.ManagedChannelBuilder;
import io.grpc.stub.StreamObserver;

import java.util.Iterator;
import java.util.List;
import java.util.UUID;
import java.util.HashMap;

/**
 * 类说明：
 *
 * @author zhucj
 * @since 20200423
 */
public class JavaGRpcClient {

    public static void main(final String[] args) throws InterruptedException {
        final int caseInt = Integer.parseInt(args[0]);
        if (1 == caseInt) {
            getRealNameByUsername();
        }
        if (2 == caseInt) {
            getStudentsByAge();
        }
        if (3 == caseInt) {
            getStudentWrapperByAges();
        }
        if (4 == caseInt) {
            biTalk();
        }
    }

    /**
     * rpc GetRealNameByUsername(MyRequest) returns (MyResponse) {}
     */
    private static void getRealNameByUsername() {
        final StudentServiceGrpc.StudentServiceBlockingStub blockingStub = GrpcHelper.getBlockingStub();
        MyResponse myResponse = blockingStub.getRealNameByUsername(MyRequest.newBuilder().setUsername("张三").build());
        System.out.println(myResponse.getRealName());

        myResponse = blockingStub.getRealNameByUsername(MyRequest.newBuilder().setUsername("李四").build());
        System.out.println(myResponse.getRealName());

        myResponse = blockingStub.getRealNameByUsername(MyRequest.newBuilder().setUsername("王五").build());
        System.out.println(myResponse.getRealName());
    }

    /**
     * rpc GetStudentsByAge(StudentRequest) returns (stream StudentResponse) {}
     */
    private static void getStudentsByAge() {
        final StudentServiceGrpc.StudentServiceBlockingStub blockingStub = GrpcHelper.getBlockingStub();
        //发生请求
        final Iterator<StudentResponse> iterator = blockingStub.getStudentsByAge(StudentRequest.newBuilder().setAge(20).build());

        //处理响应
        iterator.forEachRemaining(studentResponse
                -> System.out.println(studentResponse.getName() + "，" + studentResponse.getAge() + "，" + studentResponse.getCity()));
    }

    /**
     * rpc GetStudentWrapperByAges(stream StudentRequest) returns (StudentResponseList) {}
     */
    private static void getStudentWrapperByAges() throws InterruptedException {
        StudentServiceGrpc.StudentServiceStub stub = GrpcHelper.getAsyncStub();

        StreamObserver<StudentRequest> studentRequestStreamObserver
                = stub.getStudentWrapperByAges(new StreamObserver<StudentResponseList>() {

            @Override
            public void onNext(StudentResponseList value) {
                //处理返回
                List<StudentResponse> responseList = value.getStudentResponseList();
                responseList.forEach(studentResponse ->
                        System.out.println(studentResponse.getName()
                                + "，" + studentResponse.getAge()
                                + "，" + studentResponse.getCity()));
            }

            @Override
            public void onError(Throwable t) {
                System.out.println(t.getMessage());
            }

            @Override
            public void onCompleted() {
                System.out.println("completed");
            }
        });

        //发生请求
        studentRequestStreamObserver.onNext(StudentRequest.newBuilder().setAge(20).build());
        studentRequestStreamObserver.onNext(StudentRequest.newBuilder().setAge(30).build());
        studentRequestStreamObserver.onNext(StudentRequest.newBuilder().setAge(40).build());
        studentRequestStreamObserver.onNext(StudentRequest.newBuilder().setAge(50).build());
        studentRequestStreamObserver.onCompleted();
        //由于是异步的，结果不会马上返回，如果不 sleep 客户端就会马上关闭，导致无法获取结果
        Thread.sleep(3000);
    }

    /**
     * rpc BiTalk(stream StreamRequest) returns (stream StreamResponse) {}
     */
    private static void biTalk() throws InterruptedException {
        StudentServiceGrpc.StudentServiceStub stub = GrpcHelper.getAsyncStub();
        StreamObserver<StreamRequest> requestStreamObserver = stub.biTalk(new StreamObserver<StreamResponse>() {

            @Override
            public void onNext(StreamResponse value) {
                //处理返回值
                System.out.println(value.getResponseInfo());
            }

            @Override
            public void onError(Throwable t) {
                System.out.println(t.getMessage());
            }

            @Override
            public void onCompleted() {
                System.out.println("comlpeted");
            }
        });
        for (int i = 0; i < 10; i++) {
            //发请求：请求 com.shengsiyuan.netty.grpc.StudentServiceImpl.biTalk
            requestStreamObserver.onNext(StreamRequest.newBuilder().setRequestInfo(UUID.randomUUID().toString()).build());
            Thread.sleep(1000);
        }
        Thread.sleep(3000);
    }
}
