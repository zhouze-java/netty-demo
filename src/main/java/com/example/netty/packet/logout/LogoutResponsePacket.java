package com.example.netty.packet.logout;

import com.example.netty.packet.base.Packet;
import com.example.netty.packet.command.Command;
import lombok.Data;

/**
 * @author 周泽
 * @date Create in 14:51 2019/6/25
 * @Description 注销响应数据包
 */
@Data
public class LogoutResponsePacket extends Packet {

    private boolean success;

    private String reason;

    @Override
    public Byte getCommand() {
        return Command.LOGOUT_RESPONSE;
    }
}
