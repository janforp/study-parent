package com.shengsiyuan.netty.sixthexampleprotobuf.server;

import com.shengsiyuan.netty.sixthexampleprotobuf.MyDataInfo;
import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.SimpleChannelInboundHandler;

/**
 * 类说明：
 *
 * @author zhucj
 * @since 20200423
 */
public class TestServerHandler extends SimpleChannelInboundHandler<MyDataInfo.Person> {

    @Override
    protected void channelRead0(ChannelHandlerContext ctx, MyDataInfo.Person msg) {
        //客户端跟服务端的连接
        System.out.println("服务端接收到一个 Person 对象");

        System.out.println(msg.getAddress());
        System.out.println(msg.getName());
        System.out.println(msg.getAge());
    }
}
