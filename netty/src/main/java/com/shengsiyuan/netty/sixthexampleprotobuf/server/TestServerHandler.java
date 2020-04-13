package com.shengsiyuan.netty.sixthexampleprotobuf.server;

import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.SimpleChannelInboundHandler;
import io.netty.channel.socket.SocketChannel;

/**
 * 类说明：
 *
 * @author zhucj
 * @since 20200423
 */
public class TestServerHandler extends SimpleChannelInboundHandler<SocketChannel> {

    @Override
    protected void channelRead0(ChannelHandlerContext ctx, SocketChannel msg) throws Exception {

    }
}
