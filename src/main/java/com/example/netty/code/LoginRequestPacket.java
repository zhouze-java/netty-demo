package com.example.netty.code;

import lombok.Data;

/**
 * @author 周泽
 * @date Create in 15:08 2019/6/5
 * @Description 登录请求数据包
 */
@Data
public class LoginRequestPacket extends Packet{

    private Integer userId;

    private String username;

    private String password;

    @Override
    public Byte getCommand() {
        return Command.LOGIN_REQUEST;
    }
}
