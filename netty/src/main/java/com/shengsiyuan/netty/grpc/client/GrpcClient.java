package com.shengsiyuan.netty.grpc.client;

import com.shengsiyuan.netty.proto.MyRequest;
import com.shengsiyuan.netty.proto.MyResponse;
import com.shengsiyuan.netty.proto.StudentRequest;
import com.shengsiyuan.netty.proto.StudentResponse;
import com.shengsiyuan.netty.proto.StudentServiceGrpc;
import com.shengsiyuan.netty.proto.StuentResponseList;
import io.grpc.ManagedChannel;
import io.grpc.ManagedChannelBuilder;
import io.grpc.stub.StreamObserver;

import java.util.Iterator;
import java.util.List;

/**
 * 类说明：
 *
 * @author zhucj
 * @since 20200423
 */
public class GrpcClient {

    public static void main(String[] args) throws InterruptedException {
        ManagedChannel managedChannel = ManagedChannelBuilder.forAddress("localhost", 8899).usePlaintext().build();
        StudentServiceGrpc.StudentServiceBlockingStub blockingStub = StudentServiceGrpc.newBlockingStub(managedChannel);
        StudentServiceGrpc.StudentServiceStub stub = StudentServiceGrpc.newStub(managedChannel);
        MyRequest request = MyRequest.newBuilder().setUsername("zs").build();
        MyResponse myResponse = blockingStub.getRealNameByUsername(request);
        System.out.println(myResponse.getRealname());

        myResponse = blockingStub.getRealNameByUsername(request);
        System.out.println(myResponse.getRealname());

        myResponse = blockingStub.getRealNameByUsername(request);
        System.out.println(myResponse.getRealname());

        System.out.println("--------------------------");

        //rpc GetStudentsByAge(StudentRequest) returns (stream StudentResponse) {}
        Iterator<StudentResponse> iterator = blockingStub.getStudentsByAge(StudentRequest.newBuilder().setAge(20).build());
        iterator.forEachRemaining(studentResponse -> {
            System.out.println(studentResponse.getName() + "，" + studentResponse.getAge() + "，" + studentResponse.getCity());
        });

        System.out.println("+++++++++++++++++++++++++++++++");

        //rpc GetStudentWrapperByAges(stream StudentRequest) returns (StuentResponseList) {}
        StreamObserver<StuentResponseList> stuentResponseListStreamObserver = new StreamObserver<StuentResponseList>() {
            @Override
            public void onNext(StuentResponseList value) {
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
        };

        StreamObserver<StudentRequest> studentRequestStreamObserver = stub.getStudentWrapperByAges(stuentResponseListStreamObserver);
        studentRequestStreamObserver.onNext(StudentRequest.newBuilder().setAge(20).build());
        studentRequestStreamObserver.onNext(StudentRequest.newBuilder().setAge(30).build());
        studentRequestStreamObserver.onNext(StudentRequest.newBuilder().setAge(40).build());
        studentRequestStreamObserver.onNext(StudentRequest.newBuilder().setAge(50).build());
        studentRequestStreamObserver.onCompleted();
        //由于是异步的，结果不会马上返回，如果不 sleep 客户端就会马上关闭，导致无法获取结果
        Thread.sleep(3000);
    }
}
