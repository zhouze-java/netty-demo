package com.example.netty.packet;

/**
 * @author 周泽
 * @date Create in 15:08 2019/6/5
 * @Description 命令常量
 */
public interface Command {

    /**
     * 登录命令
     */
    Byte LOGIN_REQUEST = 1;

    /**
     * 客户端发送消息命令
     */
    Byte MESSAGE_REQUEST = 3 ;

    /**
     * 服务端响应发送消息命令
     */
    Byte MESSAGE_RESPONSE = 4;
}
