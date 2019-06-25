package com.example.netty.console;

import com.example.netty.packet.login.LoginRequestPacket;
import io.netty.channel.Channel;
import lombok.extern.slf4j.Slf4j;

import java.util.Scanner;

/**
 * @author 周泽
 * @date Create in 11:15 2019/6/25
 * @Description 登录控制台
 */
@Slf4j
public class LoginConsoleCommand implements ConsoleCommand {

    @Override
    public void exec(Scanner scanner, Channel channel) {
        LoginRequestPacket loginRequestPacket = new LoginRequestPacket();

        log.info("登录,请输入用户名:");
        loginRequestPacket.setUserName(scanner.nextLine());
        log.info("请输入密码:");
        loginRequestPacket.setPassword(scanner.nextLine());

        // 发送登录数据包
        channel.writeAndFlush(loginRequestPacket);
        // 登录响应
        waitForLoginResponse();
    }

    private static void waitForLoginResponse() {
        try {
            Thread.sleep(1000);
        } catch (InterruptedException ignored) {
        }
    }

}
