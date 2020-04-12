package com.shengsiyuan.netty.firstexample;

import io.netty.channel.ChannelInitializer;
import io.netty.channel.ChannelPipeline;
import io.netty.channel.socket.SocketChannel;
import io.netty.handler.codec.http.HttpServerCodec;

/**
 * 类说明：请求到服务端的时候，由该处理器处理
 *
 * @author zhucj
 * @since 20200423
 */
public class TestServerInitializer extends ChannelInitializer<SocketChannel> {

    /**
     * 连接建立之后就会回调该方法
     *
     * @param socketChannel 链接
     * @throws Exception 异常
     */
    @Override
    protected void initChannel(SocketChannel socketChannel) throws Exception {
        //管道中有很多类似拦截器的东西，责任链模式？
        ChannelPipeline pipeline = socketChannel.pipeline();
        //codec:编码解码器，对请求和响应编解码
        //HttpRequestDecoder, HttpResponseEncoder 合二为一的处理器，把http请求解码，把http响应编码
        pipeline.addLast("httpServerCodec", new HttpServerCodec());
        //自定义处理器
        pipeline.addLast("testHttpServerHandler", new TestHttpServerHandler());
    }
}
