package com.example.netty.handler.login;

import com.example.netty.code.PacketCodeC;
import com.example.netty.packet.login.LoginRequestPacket;
import com.example.netty.packet.login.LoginResponsePacket;
import com.example.netty.packet.base.Packet;
import io.netty.buffer.ByteBuf;
import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.ChannelInboundHandlerAdapter;

/**
 * @author 周泽
 * @date Create in 14:46 2019/6/10
 * @Description 服务端逻辑处理器
 */
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

        }
    }

    private boolean valid(LoginRequestPacket loginRequestPacket) {
        return true;
    }
}
