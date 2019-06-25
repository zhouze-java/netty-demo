package com.example.netty.packet.group;

import com.example.netty.packet.base.Packet;
import com.example.netty.packet.command.Command;
import lombok.Data;

import java.util.List;

/**
 * @author 周泽
 * @date Create in 14:31 2019/6/25
 * @Description
 */
@Data
public class CreateGroupResponsePacket extends Packet {

    private boolean success;

    private String groupId;

    private List<String> userNameList;

    @Override
    public Byte getCommand() {
        return Command.CREATE_GROUP_RESPONSE;
    }

}
