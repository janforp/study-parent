package com.shengsiyuan.netty.firstexample;

import io.netty.buffer.ByteBuf;
import io.netty.buffer.Unpooled;
import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.SimpleChannelInboundHandler;
import io.netty.handler.codec.http.DefaultFullHttpResponse;
import io.netty.handler.codec.http.FullHttpResponse;
import io.netty.handler.codec.http.HttpHeaderNames;
import io.netty.handler.codec.http.HttpObject;
import io.netty.handler.codec.http.HttpRequest;
import io.netty.handler.codec.http.HttpResponseStatus;
import io.netty.handler.codec.http.HttpVersion;
import io.netty.util.CharsetUtil;

import java.net.URI;

/**
 * 类说明：自定义处理器
 *
 * @author zhucj
 * @since 20200423
 */
public class TestHttpServerHandler extends SimpleChannelInboundHandler<HttpObject> {

    /**
     * 该方法其实就是接收客户端的数据/消息，并且向客户端返回响应
     * 流程执行到该方法的时候，就说明请求已经进来了
     * will be renamed to messageReceived in netty 5, but netty 5 was dropped
     *
     * @param channelHandlerContext 处理器上下文
     * @param httpObject 请求对象
     * @throws Exception ？
     */
    @Override
    protected void channelRead0(ChannelHandlerContext channelHandlerContext, HttpObject httpObject) throws Exception {
        boolean instance = httpObject instanceof HttpRequest;
        if (!instance) {
            return;
        }
        HttpRequest httpRequest = (HttpRequest) httpObject;
        System.out.println("请求方法名：" + httpRequest.method().name());
        URI uri = new URI(httpRequest.uri());
        if ("/favicon.ico".equals(uri.getPath())) {
            System.out.println("请求 favicon.ico");
            return;
        }
        ByteBuf content = Unpooled.copiedBuffer("Hello World \n", CharsetUtil.UTF_8);
        FullHttpResponse response = new DefaultFullHttpResponse(HttpVersion.HTTP_1_1, HttpResponseStatus.OK, content);
        response.headers().set(HttpHeaderNames.CONTENT_TYPE, "test/plain");
        response.headers().set(HttpHeaderNames.CONTENT_LENGTH, content.readableBytes());
        channelHandlerContext.writeAndFlush(response);
    }
}
