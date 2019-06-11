package com.example.netty.handler.test;

import io.netty.buffer.ByteBuf;
import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.ChannelInboundHandlerAdapter;
import lombok.extern.slf4j.Slf4j;

import java.nio.charset.Charset;
import java.util.Date;

/**
 * @author 周泽
 * @date Create in 17:13 2019/5/31
 * @Description 服务端逻辑处理器(读取数据)
 */
@Slf4j
public class FirstServerHandler extends ChannelInboundHandlerAdapter {

    @Override
    public void channelRead(ChannelHandlerContext ctx, Object msg) throws Exception {
        ByteBuf byteBuf = (ByteBuf) msg;

        log.info(new Date() + "服务端读取数据:{}", byteBuf.toString(Charset.forName("utf-8")));

        // 给客户端返回消息
        log.info(new Date() + "服务端发送数据.....");

        ByteBuf outBuf = getByteBuf(ctx);
        ctx.channel().writeAndFlush(outBuf);
    }


    private ByteBuf getByteBuf(ChannelHandlerContext ctx) {
        // 获取二进制抽象
        ByteBuf buffer = ctx.alloc().buffer();

        // 准备数据,并指定编码格式
        byte[] bytes = "hello,world from server".getBytes(Charset.forName("utf-8"));

        // 填充数据到ByteBuf
        buffer.writeBytes(bytes);

        return buffer;
    }

}
