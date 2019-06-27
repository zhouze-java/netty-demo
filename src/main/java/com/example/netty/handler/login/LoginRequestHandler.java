package com.example.netty.handler.login;

import com.example.netty.packet.login.LoginRequestPacket;
import com.example.netty.packet.login.LoginResponsePacket;
import com.example.netty.session.Session;
import com.example.netty.util.LoginUtil;
import com.example.netty.util.SessionUtil;
import io.netty.channel.ChannelHandler;
import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.SimpleChannelInboundHandler;
import lombok.extern.slf4j.Slf4j;

import java.util.Date;
import java.util.UUID;

/**
 * @author 周泽
 * @date Create in 10:08 2019/6/15
 * @Description 客户端登录请求逻辑处理器
 */
@Slf4j
@ChannelHandler.Sharable
public class LoginRequestHandler extends SimpleChannelInboundHandler<LoginRequestPacket> {

    public static final LoginRequestHandler INSTANCE = new LoginRequestHandler();

    protected LoginRequestHandler() {
    }

    @Override
    protected void channelRead0(ChannelHandlerContext channelHandlerContext, LoginRequestPacket loginRequestPacket) throws Exception {
        log.info("{}: 客户端收到登录请求....", new Date());

        LoginResponsePacket loginResponsePacket = new LoginResponsePacket();
        loginResponsePacket.setVersion(loginRequestPacket.getVersion());
        loginResponsePacket.setUserName(loginRequestPacket.getUserName());

        // 校验用户名密码
        if (valid(loginRequestPacket)) {
            log.info("客户端[{}]登录成功....", loginRequestPacket.getUserName());
            // 登录成功
            loginResponsePacket.setSuccess(true);
            // 分配一个随机的userId
            String userId = randomUserId();
            loginResponsePacket.setUserId(userId);
            // 记录登录成功的标识
            SessionUtil.bindSession(new Session(userId, loginRequestPacket.getUserName()), channelHandlerContext.channel());
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

    public static String randomUserId(){
        return UUID.randomUUID().toString().split("-")[0];
    }

    /**
     * 客户端连接被关闭的时候,解绑session
     * @param ctx
     * @throws Exception
     */
    @Override
    public void channelInactive(ChannelHandlerContext ctx) throws Exception {
        SessionUtil.unBindSession(ctx.channel());
    }
}
