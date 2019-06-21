package com.example.netty.handler.login;

import com.example.netty.packet.login.LoginRequestPacket;
import com.example.netty.packet.login.LoginResponsePacket;
import com.example.netty.util.LoginUtil;
import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.SimpleChannelInboundHandler;
import lombok.extern.slf4j.Slf4j;

import java.util.Date;

/**
 * @author 周泽
 * @date Create in 10:08 2019/6/15
 * @Description 客户端登录请求逻辑处理器
 */
@Slf4j
public class LoginRequestHandler extends SimpleChannelInboundHandler<LoginRequestPacket> {

    @Override
    protected void channelRead0(ChannelHandlerContext channelHandlerContext, LoginRequestPacket loginRequestPacket) throws Exception {
        log.info("{}: 客户端收到登录请求....", new Date());

        LoginResponsePacket loginResponsePacket = new LoginResponsePacket();
        loginRequestPacket.setVersion(loginRequestPacket.getVersion());

        // 校验用户名密码
        if (valid(loginRequestPacket)) {
            log.info("客户端登录成功....");
            // 登录成功
            loginResponsePacket.setSuccess(true);

            // 记录登录成功的标识
            LoginUtil.markAsLogin(channelHandlerContext.channel());
        } else {
            log.info("客户端登录失败");

            // 登录失败
            loginResponsePacket.setSuccess(false);
            loginResponsePacket.setReason("账号密码错误");
        }

        log.info("服务端发送登录响应数据包给客户端....");

        // 编码返回给客户端
        channelHandlerContext.channel().writeAndFlush(loginResponsePacket);
    }

    private boolean valid(LoginRequestPacket loginRequestPacket) {
        return true;
    }

}
