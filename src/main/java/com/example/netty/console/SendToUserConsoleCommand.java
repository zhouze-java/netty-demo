package com.example.netty.console;

import com.example.netty.packet.message.MessageRequestPacket;
import io.netty.channel.Channel;
import lombok.extern.slf4j.Slf4j;

import java.util.Scanner;

/**
 * @author 周泽
 * @date Create in 10:57 2019/6/25
 * @Description 给用户发送消息的控制台
 */
@Slf4j
public class SendToUserConsoleCommand implements ConsoleCommand {

    @Override
    public void exec(Scanner scanner, Channel channel) {
        log.info("给某个用户发送消息......");

        String toUserId = scanner.nextLine();
        String message = scanner.nextLine();

        MessageRequestPacket messageRequestPacket = new MessageRequestPacket();
        messageRequestPacket.setToUserId(toUserId);
        messageRequestPacket.setMessage(message);

        channel.writeAndFlush(messageRequestPacket);
    }

}
