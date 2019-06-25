package com.example.netty.packet.group;

import com.example.netty.packet.base.Packet;
import com.example.netty.packet.command.Command;
import lombok.Data;

import java.util.List;

/**
 * @author 周泽
 * @date Create in 11:22 2019/6/25
 * @Description 创建群聊的数据包
 */
@Data
public class CreateGroupRequestPacket extends Packet {

    private List<String> userIdList;

    @Override
    public Byte getCommand() {
        return Command.CREATE_GROUP_REQUEST;
    }

}
