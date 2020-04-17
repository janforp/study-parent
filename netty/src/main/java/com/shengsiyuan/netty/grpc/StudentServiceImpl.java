package com.shengsiyuan.netty.grpc;

import com.shengsiyuan.netty.proto.MyRequest;
import com.shengsiyuan.netty.proto.MyResponse;
import com.shengsiyuan.netty.proto.StudentServiceGrpc;
import io.grpc.stub.StreamObserver;

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
        MyResponse response = MyResponse.newBuilder().setRealname("张三").build();

        //执行逻辑
        responseObserver.onNext(response);

        //告诉客户端，我处理完毕
        responseObserver.onCompleted();
    }
}
