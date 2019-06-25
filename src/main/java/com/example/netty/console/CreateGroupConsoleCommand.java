package com.example.netty.console;

import com.example.netty.packet.group.CreateGroupRequestPacket;
import io.netty.channel.Channel;
import lombok.extern.slf4j.Slf4j;

import java.util.Arrays;
import java.util.Scanner;

/**
 * @author 周泽
 * @date Create in 11:19 2019/6/25
 * @Description 创建群聊控制台
 */
@Slf4j
public class CreateGroupConsoleCommand implements ConsoleCommand{

    private static final String USER_ID_SPLITER = ",";


    @Override
    public void exec(Scanner scanner, Channel channel) {
        CreateGroupRequestPacket createGroupRequestPacket = new CreateGroupRequestPacket();

        log.info("输入userIds拉入群聊...");
        String userIds = scanner.nextLine();

        createGroupRequestPacket.setUserIdList(Arrays.asList(userIds.split(USER_ID_SPLITER)));
        channel.writeAndFlush(createGroupRequestPacket);
    }

}
