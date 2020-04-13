package com.shengsiyuan.netty.sixthexampleprotobuf.client;

import com.shengsiyuan.netty.sixthexampleprotobuf.MyDataInfo;
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
}
