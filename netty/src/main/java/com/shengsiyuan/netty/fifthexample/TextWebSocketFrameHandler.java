package com.shengsiyuan.netty.fifthexample;

import io.netty.channel.Channel;
import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.ChannelId;
import io.netty.channel.SimpleChannelInboundHandler;
import io.netty.handler.codec.http.websocketx.TextWebSocketFrame;

import java.time.LocalDateTime;

/**
 * 类说明：
 *
 * @author zhucj
 * @since 20200423
 */
public class TextWebSocketFrameHandler extends SimpleChannelInboundHandler<TextWebSocketFrame> {

    /**
     * 收到websocket客户端发来的消息
     */
    @Override
    protected void channelRead0(ChannelHandlerContext ctx, TextWebSocketFrame msg) {
        System.out.println("服务端收到消息：" + msg.text());
        Channel channel = ctx.channel();
        channel.writeAndFlush(new TextWebSocketFrame("服务端时间：" + LocalDateTime.now()));
    }

    @Override
    public void handlerAdded(ChannelHandlerContext ctx) {
        Channel channel = ctx.channel();
        ChannelId channelId = channel.id();
        String longId = channelId.asLongText();
        System.out.println("handlerAdded：" + longId);
    }

    @Override
    public void handlerRemoved(ChannelHandlerContext ctx) throws Exception {
        Channel channel = ctx.channel();
        ChannelId channelId = channel.id();
        String longId = channelId.asLongText();
        System.out.println("handlerAdded：" + longId);
    }

    @Override
    public void exceptionCaught(ChannelHandlerContext ctx, Throwable cause) throws Exception {
        System.out.println("发生异常");
        ctx.close();
    }
}
