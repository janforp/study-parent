package com.shengsiyuan.netty.grpc.client;

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

/**
 * 类说明：
 *
 * @author zhucj
 * @since 20200423
 */
public class GrpcClient {

    public static void main(String[] args) throws InterruptedException {
        getRealNameByUsername();
        //        getStudentsByAge();
        //        getStudentWrapperByAges();
        //        biTalk();
    }

    private static void getRealNameByUsername() {
        StudentServiceGrpc.StudentServiceBlockingStub blockingStub = getBlockingStub();
        MyResponse myResponse = blockingStub.getRealNameByUsername(MyRequest.newBuilder().setUsername("张三").build());
        System.out.println(myResponse.getRealname());

        myResponse = blockingStub.getRealNameByUsername(MyRequest.newBuilder().setUsername("李四").build());
        System.out.println(myResponse.getRealname());

        myResponse = blockingStub.getRealNameByUsername(MyRequest.newBuilder().setUsername("王五").build());
        System.out.println(myResponse.getRealname());
    }

    private static void getStudentsByAge() {
        StudentServiceGrpc.StudentServiceBlockingStub blockingStub = getBlockingStub();
        Iterator<StudentResponse> iterator = blockingStub.getStudentsByAge(StudentRequest.newBuilder().setAge(20).build());
        iterator.forEachRemaining(studentResponse
                -> System.out.println(studentResponse.getName() + "，" + studentResponse.getAge() + "，" + studentResponse.getCity()));
    }

    private static void getStudentWrapperByAges() throws InterruptedException {
        StudentServiceGrpc.StudentServiceStub stub = getAsyncStub();
        StreamObserver<StudentRequest> studentRequestStreamObserver
                = stub.getStudentWrapperByAges(new StreamObserver<StudentResponseList>() {

            @Override
            public void onNext(StudentResponseList value) {
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
        studentRequestStreamObserver.onNext(StudentRequest.newBuilder().setAge(20).build());
        studentRequestStreamObserver.onNext(StudentRequest.newBuilder().setAge(30).build());
        studentRequestStreamObserver.onNext(StudentRequest.newBuilder().setAge(40).build());
        studentRequestStreamObserver.onNext(StudentRequest.newBuilder().setAge(50).build());
        studentRequestStreamObserver.onCompleted();
        //由于是异步的，结果不会马上返回，如果不 sleep 客户端就会马上关闭，导致无法获取结果
        Thread.sleep(3000);
    }

    private static StudentServiceGrpc.StudentServiceBlockingStub getBlockingStub() {
        ManagedChannel managedChannel = ManagedChannelBuilder.forAddress("localhost", 8899).usePlaintext().build();
        StudentServiceGrpc.StudentServiceBlockingStub blockingStub = StudentServiceGrpc.newBlockingStub(managedChannel);
        return blockingStub;
    }

    private static void biTalk() throws InterruptedException {
        StudentServiceGrpc.StudentServiceStub stub = getAsyncStub();
        StreamObserver<StreamRequest> requestStreamObserver = stub.biTalk(new StreamObserver<StreamResponse>() {

            @Override
            public void onNext(StreamResponse value) {
                System.out.println(value.getRespnseInfo());
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
            requestStreamObserver.onNext(StreamRequest.newBuilder().setRequestInfo(UUID.randomUUID().toString()).build());
            Thread.sleep(1000);
        }
        Thread.sleep(3000);
    }

    private static StudentServiceGrpc.StudentServiceStub getAsyncStub() {
        ManagedChannel managedChannel = ManagedChannelBuilder.forAddress("localhost", 8899).usePlaintext().build();
        StudentServiceGrpc.StudentServiceStub stub = StudentServiceGrpc.newStub(managedChannel);
        return stub;
    }
}
