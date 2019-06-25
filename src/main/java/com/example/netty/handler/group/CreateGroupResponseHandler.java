package com.example.netty.handler.group;

import com.example.netty.packet.group.CreateGroupResponsePacket;
import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.SimpleChannelInboundHandler;
import lombok.extern.slf4j.Slf4j;

/**
 * @author 周泽
 * @date Create in 14:38 2019/6/25
 * @Description 服务端接收创建群的响应
 */
@Slf4j
public class CreateGroupResponseHandler extends SimpleChannelInboundHandler<CreateGroupResponsePacket> {

    @Override
    protected void channelRead0(ChannelHandlerContext channelHandlerContext, CreateGroupResponsePacket createGroupResponsePacket) throws Exception {
        log.info("服务端创建群聊成功,客户端已经收到响应,id是[{}]", createGroupResponsePacket.getGroupId());
    }

}
