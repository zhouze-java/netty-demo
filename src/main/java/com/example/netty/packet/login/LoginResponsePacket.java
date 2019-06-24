package com.example.netty.packet.login;

import com.example.netty.packet.base.Packet;
import com.example.netty.packet.command.Command;
import lombok.Data;

/**
 * @author 周泽
 * @date Create in 10:40 2019/6/11
 * @Description 服务端登录响应数据包
 */
@Data
public class LoginResponsePacket extends Packet {

    private String userId;

    private String userName;

    private Boolean success;

    private String reason;

    @Override
    public Byte getCommand() {
        return Command.LOGIN_RESPONSE;
    }
}
