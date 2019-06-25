package com.example.netty.handler.logout;

import com.example.netty.packet.logout.LogoutRequestPacket;
import com.example.netty.packet.logout.LogoutResponsePacket;
import com.example.netty.util.SessionUtil;
import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.SimpleChannelInboundHandler;

/**
 * @author 周泽
 * @date Create in 14:50 2019/6/25
 * @Description 注销handler
 */
public class LogoutRequestHandler extends SimpleChannelInboundHandler<LogoutRequestPacket> {

    @Override
    protected void channelRead0(ChannelHandlerContext channelHandlerContext, LogoutRequestPacket logoutRequestPacket) throws Exception {
        SessionUtil.unBindSession(channelHandlerContext.channel());

        LogoutResponsePacket logoutResponsePacket = new LogoutResponsePacket();
        logoutResponsePacket.setSuccess(true);
        channelHandlerContext.channel().writeAndFlush(logoutResponsePacket);

    }

}
