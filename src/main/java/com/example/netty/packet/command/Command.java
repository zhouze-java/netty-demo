package com.example.netty.packet.command;

/**
 * @author 周泽
 * @date Create in 15:08 2019/6/5
 * @Description 命令常量
 */
public interface Command {

    /**
     * 登录请求命令
     */
    Byte LOGIN_REQUEST = 1;

    /**
     * 登录响应命令
     */
    Byte LOGIN_RESPONSE = 2;

    /**
     * 客户端发送消息命令
     */
    Byte MESSAGE_REQUEST = 3 ;

    /**
     * 服务端响应发送消息命令
     */
    Byte MESSAGE_RESPONSE = 4;

    /**
     * 客户端注销命令
     */
    Byte LOGOUT_REQUEST = 5;

    /**
     * 客户端注销响应
     */
    Byte LOGOUT_RESPONSE = 6;

    /**
     * 创建群聊请求
     */
    Byte CREATE_GROUP_REQUEST = 7;

    /**
     * 创建群聊响应
     */
    Byte CREATE_GROUP_RESPONSE = 8;

    /**
     * 客户端心跳请求
     */
    Byte HEARTBEAT_REQUEST = 9;

    /**
     * 服务端心跳响应
     */
    Byte HEARTBEAT_RESPONSE = 10;

}
