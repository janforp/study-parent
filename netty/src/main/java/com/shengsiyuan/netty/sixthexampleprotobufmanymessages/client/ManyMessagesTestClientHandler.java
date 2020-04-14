package com.shengsiyuan.netty.sixthexampleprotobufmanymessages.client;

import com.shengsiyuan.netty.sixthexampleprotobufmanymessages.MmMyDataInfo;
import io.netty.channel.Channel;
import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.SimpleChannelInboundHandler;

import java.util.Random;

/**
 * 类说明：
 *
 * @author zhucj
 * @since 20200423
 */
public class ManyMessagesTestClientHandler extends SimpleChannelInboundHandler<MmMyDataInfo.MyMessage> {

    @Override
    protected void channelRead0(ChannelHandlerContext ctx, MmMyDataInfo.MyMessage msg) {

    }

    @Override
    public void channelActive(ChannelHandlerContext ctx) {
        //模拟传递不同类型消息
        int nextInt = new Random().nextInt(3);
        MmMyDataInfo.MyMessage myMessage;
        if (nextInt == 0) {
            myMessage = MmMyDataInfo
                    .MyMessage
                    .newBuilder()
                    .setDataType(MmMyDataInfo.MyMessage.DataType.PersonType)
                    .setPerson(MmMyDataInfo.Person.newBuilder().setName("张三").setAge(20).setAddress("北京").build())
                    .build();
        } else if (nextInt == 1) {
            myMessage = MmMyDataInfo
                    .MyMessage
                    .newBuilder()
                    .setDataType(MmMyDataInfo.MyMessage.DataType.DogType)
                    .setDog(MmMyDataInfo.Dog.newBuilder().setName("一只狗").setAge(2).build())
                    .build();
        } else {
            myMessage = MmMyDataInfo
                    .MyMessage
                    .newBuilder()
                    .setDataType(MmMyDataInfo.MyMessage.DataType.CatType)
                    .setCat(MmMyDataInfo.Cat.newBuilder().setName("一只猫").setCity("上海").build())
                    .build();
        }
        Channel channel = ctx.channel();
        //发送
        System.out.println("ManyMessagesTestClientHandler-客户端向服务端发送一个 MyMessage 对象 ： " + myMessage);
        channel.writeAndFlush(myMessage);
    }
}
