package com.example.netty.handler.message;

import com.example.netty.code.PacketCodeC;
import com.example.netty.packet.message.MessageRequestPacket;
import com.example.netty.packet.message.MessageResponsePacket;
import io.netty.buffer.ByteBuf;
import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.SimpleChannelInboundHandler;
import lombok.extern.slf4j.Slf4j;

import java.util.Date;

/**
 * @author 周泽
 * @date Create in 11:40 2019/6/15
 * @Description 客户端消息处理器
 */
@Slf4j
public class MessageRequestHandler extends SimpleChannelInboundHandler<MessageRequestPacket> {

    @Override
    protected void channelRead0(ChannelHandlerContext channelHandlerContext, MessageRequestPacket messageRequestPacket) throws Exception {
        log.info("{}:收到客户端发过来的消息:{}", new Date(), messageRequestPacket.getMessage());

        // 然后服务端发消息给客户端
        MessageResponsePacket messageResponsePacket = new MessageResponsePacket();
        messageResponsePacket.setMessage("服务端收到了[" + messageRequestPacket.getMessage() + "]这条消息");

        channelHandlerContext.channel().writeAndFlush(messageResponsePacket);
    }

}
