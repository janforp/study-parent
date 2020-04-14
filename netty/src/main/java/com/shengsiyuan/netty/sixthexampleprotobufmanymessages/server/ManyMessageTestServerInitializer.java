package com.shengsiyuan.netty.sixthexampleprotobufmanymessages.server;

import com.shengsiyuan.netty.sixthexampleprotobufmanymessages.MmMyDataInfo;
import io.netty.channel.ChannelInitializer;
import io.netty.channel.ChannelPipeline;
import io.netty.channel.socket.SocketChannel;
import io.netty.handler.codec.protobuf.ProtobufDecoder;
import io.netty.handler.codec.protobuf.ProtobufEncoder;
import io.netty.handler.codec.protobuf.ProtobufVarint32FrameDecoder;
import io.netty.handler.codec.protobuf.ProtobufVarint32LengthFieldPrepender;

/**
 * 类说明：
 *
 * @author zhucj
 * @since 20200423
 */
public class ManyMessageTestServerInitializer extends ChannelInitializer<SocketChannel> {

    @Override
    protected void initChannel(SocketChannel socketChannel) {
        ChannelPipeline pipeline = socketChannel.pipeline();
        pipeline.addLast(new ProtobufVarint32FrameDecoder());
        //关键,被处理的类型
        MmMyDataInfo.Person defaultInstance = MmMyDataInfo.Person.getDefaultInstance();
        pipeline.addLast(new ProtobufDecoder(defaultInstance));
        pipeline.addLast(new ProtobufVarint32LengthFieldPrepender());
        pipeline.addLast(new ProtobufEncoder());
        pipeline.addLast(new ManyMessagesTestServerHandler());
    }
}