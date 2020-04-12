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
        System.out.println(httpObject.getClass());
        System.out.println(channelHandlerContext.channel().remoteAddress());
        //zhuchenjiandeMacBook-Pro:study-parent janita$ lsof -i:8899
        //COMMAND   PID   USER   FD   TYPE             DEVICE SIZE/OFF NODE NAME
        //java    72847 janita   99u  IPv6 0x324a294a3b231069      0t0  TCP *:8899 (LISTEN)
        //java    72847 janita  103u  IPv6 0x324a294a358603a9      0t0  TCP localhost:8899->localhost:64084 (ESTABLISHED)
        //curl    72935 janita    3u  IPv4 0x324a294a3d226811      0t0  TCP localhost:64084->localhost:8899 (ESTABLISHED)
        Thread.sleep(8000);

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

        //主动关闭的话，浏览器的 keep-alive 就无效了
        channelHandlerContext.channel().close();
    }

    @Override
    public void handlerAdded(ChannelHandlerContext ctx) throws Exception {
        System.out.println("1：handler added");
        super.handlerAdded(ctx);
    }

    @Override
    public void channelRegistered(ChannelHandlerContext ctx) throws Exception {
        System.out.println("2：channel registered");
        super.channelRegistered(ctx);
    }

    @Override
    public void channelActive(ChannelHandlerContext ctx) throws Exception {
        System.out.println("3：channel active");
        super.channelActive(ctx);
    }

    //active 之后就去处理请求了

    @Override
    public void channelInactive(ChannelHandlerContext ctx) throws Exception {
        System.out.println("4：channel inactive");
        super.channelInactive(ctx);
    }

    @Override
    public void channelUnregistered(ChannelHandlerContext ctx) throws Exception {
        System.out.println("5：channel unregistered");
        super.channelUnregistered(ctx);
    }
}
