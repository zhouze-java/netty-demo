package com.example.netty.packet.heart;

import com.example.netty.packet.base.Packet;
import com.example.netty.packet.command.Command;

/**
 * @author 周泽
 * @date Create in 11:07 2019/7/1
 * @Description 心跳请求数据包
 */
public class HeartBeatRequestPacket extends Packet {

    @Override
    public Byte getCommand() {
        return Command.HEARTBEAT_REQUEST;
    }

}
