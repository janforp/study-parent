package com.shengsiyuan.netty._52_;

import io.netty.channel.MultithreadEventLoopGroup;
import io.netty.util.NettyRuntime;
import io.netty.util.internal.SystemPropertyUtil;

/**
 * 类说明：Test
 *
 * @author zhucj
 * @since 20200423
 */
public class Test {

    /**
     * @see MultithreadEventLoopGroup
     */
    public static void main(String[] args) {
        int max = Math.max(1, SystemPropertyUtil.getInt(
                "io.netty.eventLoopThreads", NettyRuntime.availableProcessors() * 2));
        System.out.println(max);
    }
}
