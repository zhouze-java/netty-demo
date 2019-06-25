package com.example.netty.console;

import io.netty.channel.Channel;

import java.util.Scanner;

/**
 * @author 周泽
 * @date Create in 10:19 2019/6/25
 * @Description 控制台命令抽象
 */
public interface ConsoleCommand {

    void exec(Scanner scanner, Channel channel);

}
