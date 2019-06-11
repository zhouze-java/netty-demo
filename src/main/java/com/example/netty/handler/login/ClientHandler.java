package com.example.netty.handler.login;

import com.example.netty.code.PacketCodeC;
import com.example.netty.packet.login.LoginRequestPacket;
import com.example.netty.packet.login.LoginResponsePacket;
import com.example.netty.packet.base.Packet;
import com.example.netty.packet.message.MessageResponsePacket;
import com.example.netty.util.LoginUtil;
import io.netty.buffer.ByteBuf;
import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.ChannelInboundHandlerAdapter;
import lombok.extern.slf4j.Slf4j;

import java.util.Date;

/**
 * @author 周泽
 * @date Create in 14:44 2019/6/10
 * @Description 登录客户端逻辑处理器
 */
@Slf4j
public class ClientHandler extends ChannelInboundHandlerAdapter {

    @Override
    public void channelActive(ChannelHandlerContext ctx) throws Exception {

        log.info("{},客户端开始登录...", new Date());

        // 创建登录的对象
        LoginRequestPacket loginRequestPacket = new LoginRequestPacket();

        loginRequestPacket.setUsername("zhangsan");
        loginRequestPacket.setPassword("123456");
        loginRequestPacket.setUserId(1);

        // 编码
        ByteBuf byteBuf = PacketCodeC.INSTANCE.encode(ctx.alloc(), loginRequestPacket);

        // 写到服务端
        ctx.channel().writeAndFlush(byteBuf);

    }

    @Override
    public void channelRead(ChannelHandlerContext ctx, Object msg) throws Exception {
        ByteBuf byteBuf = (ByteBuf) msg;

        // 收到服务端发来的消息后,解码
        Packet packet = PacketCodeC.INSTANCE.decode(byteBuf);

        // 判断是不是登录请求数据包
        if (packet instanceof LoginResponsePacket) {
            LoginResponsePacket loginResponsePacket = (LoginResponsePacket) packet;

            if (loginResponsePacket.getSuccess()) {
                log.info("登录成功....");

                // 记录登录成功的标识
                LoginUtil.markAsLogin(ctx.channel());
            } else {
                log.info("登录失败,原因:{}", loginResponsePacket.getReason());
            }

        } else if (packet instanceof MessageResponsePacket) {
            MessageResponsePacket messageResponsePacket = (MessageResponsePacket) packet;
            log.info("收到了服务端发来的消息:{}", messageResponsePacket.getMessage());
        }
    }
}
