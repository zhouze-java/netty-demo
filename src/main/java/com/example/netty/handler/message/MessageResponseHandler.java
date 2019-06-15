package com.example.netty.handler.message;

import com.example.netty.packet.message.MessageResponsePacket;
import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.SimpleChannelInboundHandler;
import lombok.extern.slf4j.Slf4j;

/**
 * @author 周泽
 * @date Create in 11:57 2019/6/15
 * @Description 客户端
 */
@Slf4j
public class MessageResponseHandler extends SimpleChannelInboundHandler<MessageResponsePacket> {

    @Override
    protected void channelRead0(ChannelHandlerContext channelHandlerContext, MessageResponsePacket messageResponsePacket) throws Exception {
        log.info("收到了服务端发来的消息:{}", messageResponsePacket.getMessage());
    }

}
