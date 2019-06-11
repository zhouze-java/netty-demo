package com.example.netty.code;

import lombok.Data;

/**
 * @author 周泽
 * @date Create in 10:40 2019/6/11
 * @Description 服务端登录响应数据包
 */
@Data
public class LoginResponsePacket extends Packet {

    private Boolean success;

    private String reason;

    @Override
    public Byte getCommand() {
        return Command.LOGIN_RESPONSE;
    }
}
