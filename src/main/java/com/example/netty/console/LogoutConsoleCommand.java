package com.example.netty.console;

import com.example.netty.packet.logout.LogoutRequestPacket;
import io.netty.channel.Channel;

import java.util.Scanner;

/**
 * @author 周泽
 * @date Create in 11:09 2019/6/25
 * @Description 注销控制台
 */
public class LogoutConsoleCommand implements ConsoleCommand{

    @Override
    public void exec(Scanner scanner, Channel channel) {
        LogoutRequestPacket logoutRequestPacket = new LogoutRequestPacket();
        channel.writeAndFlush(logoutRequestPacket);
    }

}
