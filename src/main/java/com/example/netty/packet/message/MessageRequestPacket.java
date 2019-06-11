package com.example.netty.packet.message;

import com.example.netty.packet.base.Packet;
import com.example.netty.packet.command.Command;
import lombok.Data;

/**
 * @author 周泽
 * @date Create in 10:04 2019/6/11
 * @Description 发送消息的数据包
 */
@Data
public class MessageRequestPacket extends Packet {

    private String message;

    @Override
    public Byte getCommand() {
        return Command.MESSAGE_REQUEST;
    }

}
