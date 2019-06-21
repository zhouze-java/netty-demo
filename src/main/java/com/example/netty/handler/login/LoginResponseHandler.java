package com.example.netty.handler.login;

import com.example.netty.code.PacketCodeC;
import com.example.netty.packet.login.LoginRequestPacket;
import com.example.netty.packet.login.LoginResponsePacket;
import com.example.netty.util.LoginUtil;
import io.netty.buffer.ByteBuf;
import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.SimpleChannelInboundHandler;
import lombok.extern.slf4j.Slf4j;

import java.util.Date;

/**
 * @author 周泽
 * @date Create in 11:46 2019/6/15
 * @Description 客户端登录响应处理
 */
@Slf4j
public class LoginResponseHandler extends SimpleChannelInboundHandler<LoginResponsePacket> {

    @Override
    public void channelActive(ChannelHandlerContext ctx) throws Exception {
        log.info("{},客户端开始登录...", new Date());

        // 创建登录的对象
        LoginRequestPacket loginRequestPacket = new LoginRequestPacket();

        loginRequestPacket.setUsername("zhangsan");
        loginRequestPacket.setPassword("123456");
        loginRequestPacket.setUserId(1);

        // 写到服务端
        ctx.channel().writeAndFlush(loginRequestPacket);
    }

    @Override
    protected void channelRead0(ChannelHandlerContext channelHandlerContext, LoginResponsePacket loginResponsePacket) throws Exception {

        if (loginResponsePacket.getSuccess()) {
            log.info("登录成功....");
        } else {
            log.info("登录失败,原因:{}", loginResponsePacket.getReason());
        }

    }

}
