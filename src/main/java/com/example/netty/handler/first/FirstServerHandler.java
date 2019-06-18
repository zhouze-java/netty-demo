package com.example.netty.handler.first;

import io.netty.buffer.ByteBuf;
import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.ChannelInboundHandlerAdapter;
import lombok.extern.slf4j.Slf4j;

import java.nio.charset.Charset;

/**
 * @author 周泽
 * @date Create in 14:39 2019/6/17
 * @Description 服务端接收消息handler
 */
@Slf4j
public class FirstServerHandler extends ChannelInboundHandlerAdapter {

    @Override
    public void channelRead(ChannelHandlerContext ctx, Object msg) throws Exception {

        ByteBuf byteBuf = (ByteBuf)msg;

        log.info("接收到客户端发过来的消息:{}", byteBuf.toString(Charset.forName("utf-8")));
    }
}
