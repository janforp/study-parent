package com.shengsiyuan.netty.zhanbaochaibaoexample.client;

import io.netty.bootstrap.Bootstrap;
import io.netty.channel.ChannelFuture;
import io.netty.channel.nio.NioEventLoopGroup;
import io.netty.channel.socket.nio.NioSocketChannel;

/**
 * 类说明：
 *
 * @author zhucj
 * @since 20200423
 */
public class ZbCbClient {

    public static void main(String[] args) throws InterruptedException {

        NioEventLoopGroup work = new NioEventLoopGroup();
        try {
            Bootstrap bootstrap = new Bootstrap();
            bootstrap.group(work)
                    .channel(NioSocketChannel.class)
                    .handler(new ZbCbClientInitializer());
            ChannelFuture channelFuture = bootstrap.connect("localhost", 8899).sync();
        } finally {
            work.shutdownGracefully();
        }
    }
}
