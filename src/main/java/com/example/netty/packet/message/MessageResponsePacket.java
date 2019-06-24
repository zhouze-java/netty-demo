package com.example.netty.packet.message;

import com.example.netty.packet.base.Packet;
import com.example.netty.packet.command.Command;
import lombok.Data;

/**
 * @author 周泽
 * @date Create in 10:35 2019/6/11
 * @Description 服务端响应消息数据包
 */
@Data
public class MessageResponsePacket extends Packet {

    private String fromUserId;

    private String fromUserName;

    private String message;

    @Override
    public Byte getCommand() {
        return Command.MESSAGE_RESPONSE;
    }

}
