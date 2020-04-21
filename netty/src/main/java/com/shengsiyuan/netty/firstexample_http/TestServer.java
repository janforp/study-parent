package com.shengsiyuan.netty.firstexample_http;

import io.netty.bootstrap.ServerBootstrap;
import io.netty.channel.ChannelFuture;
import io.netty.channel.EventLoopGroup;
import io.netty.channel.nio.NioEventLoopGroup;
import io.netty.channel.socket.nio.NioServerSocketChannel;

/**
 * 类说明：steps:
 * firstly:start app
 * secondly: curl http://127.0.0.1:8899
 * then you can see the response on console
 *
 * @author zhucj
 * @since 20200423
 */
public class TestServer {

    public static void main(String[] args) throws InterruptedException {
        //2个线程组
        //获取连接
        EventLoopGroup bossGroup = new NioEventLoopGroup();
        //处理任务
        NioEventLoopGroup workGroup = new NioEventLoopGroup();
        try {
            //帮助服务端启动的类
            ServerBootstrap serverBootstrap = new ServerBootstrap();
            serverBootstrap
                    .group(bossGroup, workGroup)
                    //反射
                    .channel(NioServerSocketChannel.class)
                    //请求到服务端的时候，由该处理器处理，包括提供的以及自定义的处理器
                    .childHandler(new TestServerInitializer());

            //绑定到端口8899，同步的方式
            ChannelFuture channelFuture = serverBootstrap.bind(8899).sync();
            channelFuture.channel().closeFuture().sync();
        } finally {
            bossGroup.shutdownGracefully();
            workGroup.shutdownGracefully();
        }
    }
}
