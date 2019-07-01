package com.example.netty.packet.heart;

import com.example.netty.packet.base.Packet;
import com.example.netty.packet.command.Command;

/**
 * @author 周泽
 * @date Create in 14:25 2019/7/1
 * @Description 心跳响应数据包
 */
public class HeartBeatResponsePacket extends Packet {

    @Override
    public Byte getCommand() {
        return Command.HEARTBEAT_RESPONSE;
    }

}
