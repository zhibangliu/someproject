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
public class NettyServerHandler extends ChannelInboundHandlerAdapter {
    @Override
    public void channelActive(ChannelHandlerContext ctx) throws Exception {
        // 当客户端连接时触发
        System.out.println("Client connected: " + ctx.channel().remoteAddress());
        ctx.writeAndFlush("Welcome to the server!\r\n");
    }

    @Override
    public void channelRead(ChannelHandlerContext ctx, Object msg) throws Exception {
        System.out.println("服务端读取线程: " + Thread.currentThread().getName());
        ByteBuf buffer = (ByteBuf) msg;
        // 当从客户端读取到数据时触发
        System.out.println("Message received: " + buffer.toString(CharsetUtil.UTF_8));
    }

    @Override
    public void channelReadComplete(ChannelHandlerContext ctx) throws Exception {
        ByteBuf buf = Unpooled.copiedBuffer("Hello Client", CharsetUtil.UTF_8);
        ctx.writeAndFlush(buf);
    }

    @Override
    public void exceptionCaught(ChannelHandlerContext ctx, Throwable cause) throws Exception {
        // 当发生异常时触发
        cause.printStackTrace();
        ctx.close();
    }
}
