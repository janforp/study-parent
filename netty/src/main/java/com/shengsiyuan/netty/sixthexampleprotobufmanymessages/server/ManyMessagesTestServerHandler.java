package com.shengsiyuan.netty.sixthexampleprotobufmanymessages.server;

import com.shengsiyuan.netty.sixthexampleprotobufmanymessages.MmMyDataInfo;
import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.SimpleChannelInboundHandler;

/**
 * 类说明：
 *
 * @author zhucj
 * @since 20200423
 */
public class ManyMessagesTestServerHandler extends SimpleChannelInboundHandler<MmMyDataInfo.MyMessage> {

    @Override
    protected void channelRead0(ChannelHandlerContext ctx, MmMyDataInfo.MyMessage msg) {
        //客户端跟服务端的连接
        System.out.println("ManyMessagesTestServerHandler 服务端接收到一个 MyMessage 对象 ：" + msg);

        MmMyDataInfo.MyMessage.DataType dataType = msg.getDataType();
        if (dataType == MmMyDataInfo.MyMessage.DataType.PersonType) {
            MmMyDataInfo.Person person = msg.getPerson();
            System.out.println(person.getName());
            System.out.println(person.getAge());
            System.out.println(person.getAddress());
        }

        if (dataType == MmMyDataInfo.MyMessage.DataType.DogType) {
            MmMyDataInfo.Dog dog = msg.getDog();
            System.out.println(dog.getAge());
            System.out.println(dog.getName());
        }

        if (dataType == MmMyDataInfo.MyMessage.DataType.CatType) {
            MmMyDataInfo.Cat cat = msg.getCat();
            System.out.println(cat.getName());
            System.out.println(cat.getCity());
        }
    }
}
