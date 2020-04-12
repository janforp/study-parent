package com.shengsiyuan.netty.forthexample;

import io.netty.channel.ChannelInitializer;
import io.netty.channel.ChannelPipeline;
import io.netty.channel.socket.SocketChannel;
import io.netty.handler.timeout.IdleStateHandler;

import java.util.concurrent.TimeUnit;

/**
 * 类说明：该类的实例会在客户端连接到服务端的时候会被创建，并且会执行 initChannel 函数
 *
 * @author zhucj
 * @since 20200423
 */
public class MyServerInitializer extends ChannelInitializer<SocketChannel> {

    @Override
    protected void initChannel(SocketChannel socketChannel) {
        ChannelPipeline pipeline = socketChannel.pipeline();
        //空闲状态检测处理器
        //int readerIdleTimeSeconds,读空闲，如果服务端在5秒内没有接收到客户端的消息，则表示该客户端是空闲的
        //int writerIdleTimeSeconds,写空闲，如果服务端在7秒内没有向客户端发送消息，则表示该客户端是空闲的
        //int allIdleTimeSeconds，读写空闲，如果服务端在10描述内没有读写到该客户端的任何消息，则表示空闲
        pipeline.addLast(new IdleStateHandler(5, 7, 10, TimeUnit.SECONDS));
        pipeline.addLast("myServerHandler", new MyServerHandler());
    }
}
