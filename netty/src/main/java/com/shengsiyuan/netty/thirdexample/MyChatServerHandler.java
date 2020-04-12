package com.shengsiyuan.netty.thirdexample;

import io.netty.channel.Channel;
import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.SimpleChannelInboundHandler;
import io.netty.channel.group.ChannelGroup;
import io.netty.channel.group.DefaultChannelGroup;
import io.netty.util.concurrent.GlobalEventExecutor;

/**
 * 类说明：
 *
 * @author zhucj
 * @since 20200423
 */
public class MyChatServerHandler extends SimpleChannelInboundHandler<String> {

    /**
     * 用于保存连接
     */
    private static ChannelGroup channelGroup = new DefaultChannelGroup(GlobalEventExecutor.INSTANCE);

    @Override
    protected void channelRead0(ChannelHandlerContext ctx, String msg) {
        //发送消息的连接自己
        Channel channel = ctx.channel();
        channelGroup.forEach(ch -> {
            if (ch != channel) {
                //给除了自己之外的连接发送通知
                ch.writeAndFlush(channel.remoteAddress() + " 发送消息 ： " + msg + "\n");
            } else {
                //给自己发送,让自己的console上展示自己发送的消息
                ch.writeAndFlush("【自己】" + msg + "\n");
            }
        });
    }

    /**
     * 任意一个客户端给服务端发送了消息
     */
    @Override
    public void handlerAdded(ChannelHandlerContext ctx) {
        //新来的连接对象
        Channel channel = ctx.channel();

        //发送（广播）消息给所有连接
        channelGroup.writeAndFlush("【服务器】- " + ", " + channel.remoteAddress() + "加入\n");

        //并且服务端需要把所有的连接保存起来
        channelGroup.add(channel);
    }

    /**
     * 有一个连接断开了
     */
    @Override
    public void handlerRemoved(ChannelHandlerContext ctx) {
        Channel channel = ctx.channel();
        //发送（广播）消息给所有连接
        channelGroup.writeAndFlush("【服务器】- " + ", " + channel.remoteAddress() + "离开\n");

        System.out.println("剩余的连接 = " + channelGroup.size());

        //移除该链接,但是该函数会自动调用，可以不主动调用
        channelGroup.remove(channel);
    }

    @Override
    public void channelActive(ChannelHandlerContext ctx) {
        Channel channel = ctx.channel();
        System.out.println(channel.remoteAddress() + " 上线");
    }

    @Override
    public void channelInactive(ChannelHandlerContext ctx) {
        Channel channel = ctx.channel();
        System.out.println(channel.remoteAddress() + " 下线");
    }

    @Override
    public void exceptionCaught(ChannelHandlerContext ctx, Throwable cause) {
        cause.printStackTrace();
        ctx.close();
    }
}
