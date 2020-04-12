package com.shengsiyuan.netty.fifthexample;

import io.netty.channel.ChannelInitializer;
import io.netty.channel.ChannelPipeline;
import io.netty.channel.socket.SocketChannel;
import io.netty.handler.codec.http.HttpObjectAggregator;
import io.netty.handler.codec.http.HttpServerCodec;
import io.netty.handler.codec.http.websocketx.WebSocketServerProtocolHandler;
import io.netty.handler.stream.ChunkedWriteHandler;

/**
 * 类说明：该类的实例会在客户端连接到服务端的时候会被创建，并且会执行 initChannel 函数
 *
 * @author zhucj
 * @since 20200423
 */
public class WebSocketChannelInitializer extends ChannelInitializer<SocketChannel> {

    @Override
    protected void initChannel(SocketChannel socketChannel) {
        ChannelPipeline pipeline = socketChannel.pipeline();
        pipeline.addLast("httpServerCodec", new HttpServerCodec());
        //以块的方式写的处理器
        pipeline.addLast(new ChunkedWriteHandler());

        //这个处理器是基于 netty http 编程是用得很多的
        //netty 会把请求分块，每一块聚合到一起才是一个完整的请求或者响应
        pipeline.addLast(new HttpObjectAggregator(8192));
        //ws://localhost:9999/ws
        pipeline.addLast(new WebSocketServerProtocolHandler("/ws"));

        //添加自己的处理器
        pipeline.addLast(new TextWebSocketFrameHandler());
    }
}
