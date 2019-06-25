package com.example.netty.packet.logout;

import com.example.netty.packet.base.Packet;
import com.example.netty.packet.command.Command;

/**
 * @author 周泽
 * @date Create in 11:10 2019/6/25
 * @Description 客户端注销数据包
 */
public class LogoutRequestPacket extends Packet {

    @Override
    public Byte getCommand() {
        return Command.LOGOUT_REQUEST;
    }

}
