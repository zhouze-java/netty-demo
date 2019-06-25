package com.example.netty.handler.first;

import io.netty.buffer.ByteBuf;
import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.ChannelInboundHandlerAdapter;
import lombok.extern.slf4j.Slf4j;

import java.nio.charset.Charset;
import java.util.Date;

/**
 * @author 周泽
 * @date Create in 14:29 2019/6/17
 * @Description 客户端发送消息
 */
@Slf4j
public class FirstClientHandler extends ChannelInboundHandlerAdapter {

    @Override
    public void channelActive(ChannelHandlerContext ctx) throws Exception {
        log.info(new Date() + "客户端写出数据");
        for (int i = 0; i < 1000; i++) {
            // 1. 获取数据
            ByteBuf buffer = getByteBuf(ctx);

            // 2. 写数据
            ctx.channel().writeAndFlush(buffer);
        }
    }


    private ByteBuf getByteBuf(ChannelHandlerContext ctx) {
        // 获取二进制抽象
        ByteBuf buffer = ctx.alloc().buffer();

        // 准备数据,并指定编码格式
        byte[] bytes = "hello,world".getBytes(Charset.forName("utf-8"));

        // 填充数据到ByteBuf
        buffer.writeBytes(bytes);

        return buffer;
    }

}