package com.example.netty.handler.login;

import com.example.netty.code.PacketCodeC;
import com.example.netty.packet.login.LoginRequestPacket;
import com.example.netty.packet.login.LoginResponsePacket;
import com.example.netty.packet.base.Packet;
import com.example.netty.packet.message.MessageRequestPacket;
import com.example.netty.packet.message.MessageResponsePacket;
import io.netty.buffer.ByteBuf;
import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.ChannelInboundHandlerAdapter;
import lombok.extern.slf4j.Slf4j;
import sun.rmi.runtime.Log;

import java.util.Date;

/**
 * @author 周泽
 * @date Create in 14:46 2019/6/10
 * @Description 服务端逻辑处理器
 */
@Slf4j
public class ServerHandler extends ChannelInboundHandlerAdapter {

    @Override
    public void channelRead(ChannelHandlerContext ctx, Object msg) throws Exception {

        // 收到消息后解码
        ByteBuf byteBuf = (ByteBuf) msg;

        Packet packet = PacketCodeC.INSTANCE.decode(byteBuf);

        // 判断是不是登录请求数据包
        if (packet instanceof LoginRequestPacket) {
            LoginRequestPacket loginRequestPacket = (LoginRequestPacket) packet;

            LoginResponsePacket loginResponsePacket = new LoginResponsePacket();
            loginRequestPacket.setVersion(loginRequestPacket.getVersion());

            // 校验用户名密码
            if (valid(loginRequestPacket)) {
                // 登录成功
                loginResponsePacket.setSuccess(true);
            } else {
                // 登录失败
                loginResponsePacket.setSuccess(false);
                loginResponsePacket.setReason("账号密码错误");
            }

            // 编码返回给客户端
            ByteBuf responseBuffer = PacketCodeC.INSTANCE.encode(ctx.alloc(), loginResponsePacket);
            ctx.channel().writeAndFlush(responseBuffer);

        } else if (packet instanceof MessageRequestPacket){
            // 处理客户端发过来的消息
            MessageRequestPacket messageRequestPacket = (MessageRequestPacket) packet;
            log.info("{}:收到客户端发过来的消息:{}", new Date(), messageRequestPacket.getMessage());

            // 然后服务端发消息给客户端
            MessageResponsePacket messageResponsePacket = new MessageResponsePacket();
            messageResponsePacket.setMessage("服务端收到了[" + messageRequestPacket.getMessage() + "]这条消息");
            ByteBuf responseBuffer = PacketCodeC.INSTANCE.encode(ctx.alloc(), messageResponsePacket);
            ctx.channel().writeAndFlush(responseBuffer);

        }
    }

    private boolean valid(LoginRequestPacket loginRequestPacket) {
        return true;
    }
}
