package com.shengsiyuan.netty.protobuf;

import com.google.protobuf.InvalidProtocolBufferException;

/**
 * 类说明：
 *
 * @author zhucj
 * @since 20200423
 */
public class ProtoBufTest {

    public static void main(String[] args) throws InvalidProtocolBufferException {
        //构建对象
        DataInfo.Student student = DataInfo.Student.newBuilder().setName("张三").setAge(20).setAddress("北京").build();
        //转字节数组
        byte[] bytes = student.toByteArray();
        //反序列化
        DataInfo.Student parseStudent = DataInfo.Student.parseFrom(bytes);
        System.out.println(parseStudent);
        System.out.println(parseStudent.getName());
        System.out.println(parseStudent.getAge());
        System.out.println(parseStudent.getAddress());
    }
}
