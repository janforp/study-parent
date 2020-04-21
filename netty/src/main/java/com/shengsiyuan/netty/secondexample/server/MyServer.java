package com.shengsiyuan.netty.secondexample.server;

import io.netty.bootstrap.ServerBootstrap;
import io.netty.channel.ChannelFuture;
import io.netty.channel.EventLoopGroup;
import io.netty.channel.nio.NioEventLoopGroup;
import io.netty.channel.socket.nio.NioServerSocketChannel;

/**
 * 类说明：
 *
 * @author zhucj
 * @since 20200423
 */
public class MyServer {

    public static void main(String[] args) throws InterruptedException {
        //监听客户端连接，转发到 childGroup
        //底层就是一个死循环
        EventLoopGroup parentGroup = new NioEventLoopGroup(1);
        //真正处理业务的
        EventLoopGroup childGroup = new NioEventLoopGroup();
        try {
            ServerBootstrap serverBootstrap = new ServerBootstrap();
            serverBootstrap
                    .group(parentGroup,childGroup)
                    .channel(NioServerSocketChannel.class)
                    //给worker，如果用handler，则给boss
                    .childHandler(new MyServerInitializer());
            //上面的代码都是赋值，也就初始化对象
            new NioServerSocketChannel();

            ChannelFuture channelFuture = serverBootstrap.bind(8899).sync();
            channelFuture.channel().closeFuture().sync();
        }finally {
            parentGroup.shutdownGracefully();
            childGroup.shutdownGracefully();
        }
    }
}
