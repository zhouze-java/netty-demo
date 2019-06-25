package com.example.netty.handler.logout;

import com.example.netty.packet.logout.LogoutResponsePacket;
import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.SimpleChannelInboundHandler;
import lombok.extern.slf4j.Slf4j;

/**
 * @author 周泽
 * @date Create in 14:57 2019/6/25
 * @Description 注销响应处理
 */
@Slf4j
public class LogoutResponseHandler extends SimpleChannelInboundHandler<LogoutResponsePacket> {

    @Override
    protected void channelRead0(ChannelHandlerContext channelHandlerContext, LogoutResponsePacket logoutResponsePacket) throws Exception {
        log.info("收到服务端注销响应,已注销.....");
    }

}
