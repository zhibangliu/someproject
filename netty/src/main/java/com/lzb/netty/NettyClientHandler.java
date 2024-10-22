package com.lzb.netty;

import io.netty.buffer.ByteBuf;
import io.netty.buffer.Unpooled;
import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.ChannelInboundHandlerAdapter;
import io.netty.util.CharsetUtil;

/**
 * @author zhibang
 * @description:
 * @date 2024/10/22 22:26
 */
public class NettyClientHandler extends ChannelInboundHandlerAdapter {

    @Override
    public void channelActive(ChannelHandlerContext ctx) throws Exception {
        // 当连接到服务端时触发
        System.out.println("Connected to server: " + ctx.channel().remoteAddress());
        ctx.writeAndFlush("Welcome\r\n"); // 发送欢迎消息
    }

    @Override
    public void channelRead(ChannelHandlerContext ctx, Object msg) throws Exception {
        // 当从服务端读取到数据时触发
        System.out.println("Server response: " + msg);
    }

    @Override
    public void exceptionCaught(ChannelHandlerContext ctx, Throwable cause) throws Exception {
        // 当发生异常时触发
        cause.printStackTrace();
        ctx.close();
    }
}
