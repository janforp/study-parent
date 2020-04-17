package com.shengsiyuan.netty.grpc;

import com.shengsiyuan.netty.proto.MyRequest;
import com.shengsiyuan.netty.proto.MyResponse;
import com.shengsiyuan.netty.proto.StreamRequest;
import com.shengsiyuan.netty.proto.StreamResponse;
import com.shengsiyuan.netty.proto.StudentRequest;
import com.shengsiyuan.netty.proto.StudentResponse;
import com.shengsiyuan.netty.proto.StudentResponseList;
import com.shengsiyuan.netty.proto.StudentServiceGrpc;
import io.grpc.stub.StreamObserver;

import java.util.UUID;

/**
 * 类说明：实现 grpc 的抽象类
 *
 * @author zhucj
 * @since 20200423
 */
public class StudentServiceImpl extends StudentServiceGrpc.StudentServiceImplBase {

    @Override
    public void getRealNameByUsername(MyRequest request, StreamObserver<MyResponse> responseObserver) {
        System.out.println("收到请求，userName = " + request.getUsername());
        MyResponse response = MyResponse.newBuilder().setRealname(request.getUsername() + " 的 realName = " + request.getUsername()).build();
        //执行逻辑
        responseObserver.onNext(response);
        //告诉客户端，我处理完毕
        responseObserver.onCompleted();
    }

    @Override
    public void getStudentsByAge(StudentRequest request, StreamObserver<StudentResponse> responseObserver) {
        System.out.println("收到请求，根据 age 查询学生, age = " + request.getAge());
        responseObserver.onNext(StudentResponse.newBuilder().setName("张三").setAge(20).setCity("北京").build());
        responseObserver.onNext(StudentResponse.newBuilder().setName("李四").setAge(20).setCity("天津").build());
        responseObserver.onNext(StudentResponse.newBuilder().setName("王五").setAge(20).setCity("成都").build());
        responseObserver.onNext(StudentResponse.newBuilder().setName("赵六").setAge(20).setCity("深圳").build());
        responseObserver.onCompleted();
    }

    /**
     * rpc GetStudentWrapperByAges(stream StudentRequest) returns (StuentResponseList) {}
     */
    @Override
    public StreamObserver<StudentRequest> getStudentWrapperByAges(StreamObserver<StudentResponseList> responseObserver) {
        return new StreamObserver<StudentRequest>() {
            @Override
            public void onNext(StudentRequest value) {
                System.out.println("oneNex：" + value.getAge());
            }

            @Override
            public void onError(Throwable t) {
                System.out.println(t.getMessage());
            }

            @Override
            public void onCompleted() {
                StudentResponse response1 = StudentResponse.newBuilder().setName("张三").setAge(20).setCity("西安").build();
                StudentResponse response2 = StudentResponse.newBuilder().setName("李四").setAge(30).setCity("广州").build();
                StudentResponseList stuentResponseList = StudentResponseList.newBuilder().addStudentResponse(response1).addStudentResponse(response2).build();
                responseObserver.onNext(stuentResponseList);
                responseObserver.onCompleted();
            }
        };
    }

    @Override
    public StreamObserver<StreamRequest> biTalk(StreamObserver<StreamResponse> responseObserver) {
        return new StreamObserver<StreamRequest>() {

            @Override
            public void onNext(StreamRequest value) {
                System.out.println("biTalk");
                System.out.println(value.getRequestInfo());
                System.out.println("biTalk");
                responseObserver.onNext(StreamResponse.newBuilder().setRespnseInfo(UUID.randomUUID().toString()).build());
            }

            @Override
            public void onError(Throwable t) {
                System.out.println(t.getMessage());
            }

            @Override
            public void onCompleted() {
                responseObserver.onCompleted();
            }
        };
    }
}
