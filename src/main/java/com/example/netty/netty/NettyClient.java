package com.example.netty.netty;

import com.example.netty.code.PacketDecoder;
import com.example.netty.code.PacketEncoder;
import com.example.netty.code.Spliter;
import com.example.netty.common.CommonConfig;
import com.example.netty.handler.login.LoginRequestHandler;
import com.example.netty.handler.login.LoginResponseHandler;
import com.example.netty.handler.message.MessageResponseHandler;
import com.example.netty.packet.login.LoginRequestPacket;
import com.example.netty.packet.message.MessageRequestPacket;
import com.example.netty.util.LoginUtil;
import com.example.netty.util.SessionUtil;
import io.netty.bootstrap.Bootstrap;
import io.netty.channel.Channel;
import io.netty.channel.ChannelFuture;
import io.netty.channel.ChannelInitializer;
import io.netty.channel.ChannelOption;
import io.netty.channel.nio.NioEventLoopGroup;
import io.netty.channel.socket.SocketChannel;
import io.netty.channel.socket.nio.NioSocketChannel;
import lombok.extern.slf4j.Slf4j;

import java.util.Scanner;
import java.util.concurrent.TimeUnit;

/**
 * @author 周泽
 * @date Create in 15:12 2019/5/29
 * @Description
 */
@Slf4j
public class NettyClient {
    public static void main(String[] args) throws InterruptedException {
        Bootstrap bootstrap = new Bootstrap();
        NioEventLoopGroup group = new NioEventLoopGroup();

        bootstrap
                // 1.指定线程模型
                .group(group)
                // 2.指定 IO 类型为 NIO
                .channel(NioSocketChannel.class)
                // 3.IO 处理逻辑
                .handler(new ChannelInitializer<SocketChannel>() {
                    @Override
                    protected void initChannel(SocketChannel socketChannel) throws Exception {
                        socketChannel.pipeline()
                                .addLast(new Spliter())
                                .addLast(new PacketDecoder())
                                .addLast(new LoginResponseHandler())
                                .addLast(new MessageResponseHandler())
                                .addLast(new PacketEncoder());
                    }
                });

        bootstrap
                // attributes() 给客户端的channel即NioServerSocketChannel指定一些自定义属性,通过channel.attributes()取出该属性,给NioServerSocketChannel维护一个map
                .attr(CommonConfig.CLIENT_NAME_KEY, CommonConfig.CLIENT_NAME_VALUE)
                // option() 给连接设置一些 TCP 底层相关的属性
                // ChannelOption.CONNECT_TIMEOUT_MILLIS 示连接的超时时间,超过这个时间还是建立不上的话则代表连接失败
                .option(ChannelOption.CONNECT_TIMEOUT_MILLIS, 5000)
                // ChannelOption.SO_KEEPALIVE 表示是否开启 TCP 底层心跳机制,true 为开启
                .option(ChannelOption.SO_KEEPALIVE, true)
                // ChannelOption.TCP_NODELAY 表示是否开启Nagle算法,true表示关闭,false表示开启,通俗地说,如果要求高实时性,有数据发送时就马上发送,就关闭,如果需要减少发送次数减少网络交互,就开启.
                .option(ChannelOption.TCP_NODELAY, true);


        // 4. 建立连接
        connect(bootstrap, CommonConfig.NETTY_HOST, CommonConfig.NETTY_PORT, CommonConfig.MAX_RETRY);
    }


    public static void connect(Bootstrap bootstrap, String host, int port, int retry) {
        bootstrap.connect(host, port).addListener(future -> {
            if (future.isSuccess()) {
                log.info("连接成功");

                // 启动控制台线程
                Channel channel = ((ChannelFuture) future).channel();
                startConsoleThread(channel);

            } else if (retry == 0) {
                log.info("重试次数已经用完了,连接失败");
            } else {
                // 第几次重连
                int order = (CommonConfig.MAX_RETRY - retry) + 1;
                // 本次重连的间隔
                int delay = 1 << order;
                log.info("连接失败,进行第{}次重连", order);

                bootstrap.config().group()
                        .schedule(() -> connect(bootstrap, host, port, retry - 1), delay, TimeUnit.SECONDS);
            }
        });
    }

    public static void startConsoleThread(Channel channel) {
        Scanner sc = new Scanner(System.in);
        LoginRequestPacket loginRequestPacket = new LoginRequestPacket();

        new Thread(() -> {
            // 线程没有被中断就继续循环
            while (!Thread.interrupted()) {
                // 如果没有登陆的话,先登陆
                if (!SessionUtil.hasLogin(channel)){
                    log.info("请输入用户名:...");
                    // 然后接收用户输入的数据
                    String userName = sc.nextLine();
                    loginRequestPacket.setUserName(userName);
                    loginRequestPacket.setPassword("123456");

                    channel.writeAndFlush(loginRequestPacket);

                    waitForLoginResponse();
                } else {
                    // 登陆过了,那就是要发消息了
                    String toUserId = sc.nextLine();
                    String message = sc.nextLine();

                    MessageRequestPacket messageRequestPacket = new MessageRequestPacket();
                    messageRequestPacket.setToUserId(toUserId);
                    messageRequestPacket.setMessage(message);
                    channel.writeAndFlush(messageRequestPacket);
                }



            }
        }).start();
    }

    private static void waitForLoginResponse() {
        try {
            Thread.sleep(1000);
        } catch (InterruptedException ignored) {
        }
    }
}
