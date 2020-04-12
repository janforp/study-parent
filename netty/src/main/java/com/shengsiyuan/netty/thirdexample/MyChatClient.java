package com.shengsiyuan.netty.thirdexample;

import io.netty.bootstrap.Bootstrap;
import io.netty.channel.Channel;
import io.netty.channel.ChannelFuture;
import io.netty.channel.EventLoopGroup;
import io.netty.channel.nio.NioEventLoopGroup;
import io.netty.channel.socket.nio.NioSocketChannel;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

/**
 * 类说明：
 *
 * @author zhucj
 * @since 20200423
 */
public class MyChatClient {

    public static void main(String[] args) throws InterruptedException, IOException {
        EventLoopGroup eventLoopGroup = new NioEventLoopGroup();
        try {
            Bootstrap bootstrap = new Bootstrap();
            bootstrap
                    .group(eventLoopGroup)
                    . channel(NioSocketChannel.class)
                    .handler(new MyChatClientInitializer());
            ChannelFuture channelFuture = bootstrap.connect("localhost", 8899).sync();
            //链接对象
            Channel channel = channelFuture.channel();
            BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
            for (;;) {
                channel.writeAndFlush(br.readLine() + "\r\n");
            }
        }finally {
            eventLoopGroup.shutdownGracefully();
        }
    }
}
