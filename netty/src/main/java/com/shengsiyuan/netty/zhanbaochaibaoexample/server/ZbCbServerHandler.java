package com.shengsiyuan.netty.zhanbaochaibaoexample.server;

import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.SimpleChannelInboundHandler;

/**
 * 类说明：
 *
 * @author zhucj
 * @since 20200423
 */
public class ZbCbServerHandler extends SimpleChannelInboundHandler<String> {

    @Override
    protected void channelRead0(ChannelHandlerContext ctx, String msg) throws Exception {
        //客户端的地址+msg
        System.out.println("服务端读到数据 -> " + msg);
    }

    @Override
    public void exceptionCaught(ChannelHandlerContext ctx, Throwable cause) throws Exception {
        cause.printStackTrace();
        ctx.close();
    }
}
