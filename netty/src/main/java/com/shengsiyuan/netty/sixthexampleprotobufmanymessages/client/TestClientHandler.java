package com.shengsiyuan.netty.sixthexampleprotobufmanymessages.client;

import com.shengsiyuan.netty.sixthexampleprotobufmanymessages.MyDataInfo;
import io.netty.channel.Channel;
import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.SimpleChannelInboundHandler;

/**
 * 类说明：
 *
 * @author zhucj
 * @since 20200423
 */
public class TestClientHandler extends SimpleChannelInboundHandler<MyDataInfo.Person> {

    @Override
    protected void channelRead0(ChannelHandlerContext ctx, MyDataInfo.Person msg) throws Exception {

    }

    @Override
    public void channelActive(ChannelHandlerContext ctx) throws Exception {
        //客户端向服务端发送数据/请求
        MyDataInfo.Person person = MyDataInfo.Person.newBuilder().setName("张三").setAge(20).setAddress("北京").build();

        Channel channel = ctx.channel();

        //发送
        System.out.println("客户端向服务端发送一个 Person 对象");
        channel.writeAndFlush(person);
    }
}
