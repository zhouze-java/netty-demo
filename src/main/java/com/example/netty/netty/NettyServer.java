package com.example.netty.netty;

import com.example.netty.code.PacketDecoder;
import com.example.netty.code.PacketEncoder;
import com.example.netty.code.Spliter;
import com.example.netty.common.CommonConfig;
import com.example.netty.handler.life.LifeCycleTestHandler;
import com.example.netty.handler.login.LoginRequestHandler;
import com.example.netty.handler.message.MessageRequestHandler;
import io.netty.bootstrap.ServerBootstrap;
import io.netty.channel.ChannelInitializer;
import io.netty.channel.ChannelOption;
import io.netty.channel.nio.NioEventLoopGroup;
import io.netty.channel.socket.ServerSocketChannel;
import io.netty.channel.socket.nio.NioServerSocketChannel;
import io.netty.channel.socket.nio.NioSocketChannel;
import io.netty.util.concurrent.Future;
import io.netty.util.concurrent.GenericFutureListener;
import lombok.extern.slf4j.Slf4j;

/**
 * @author 周泽
 * @date Create in 14:14 2019/5/29
 * @Description netty服务端
 */
@Slf4j
public class NettyServer {
    public static void main(String[] args) {
        ServerBootstrap serverBootstrap = new ServerBootstrap();

        NioEventLoopGroup boos = new NioEventLoopGroup();
        NioEventLoopGroup worker = new NioEventLoopGroup();

        serverBootstrap
                .group(boos, worker)
                .channel(NioServerSocketChannel.class)
                .childHandler(new ChannelInitializer<NioSocketChannel>() {
                    @Override
                    protected void initChannel(NioSocketChannel ch) {
                        log.info("取出childAttr属性:{}", ch.attr(CommonConfig.CLIENT_KEY).get());

                        ch.pipeline()
                                .addLast(new LifeCycleTestHandler())
                                .addLast(new Spliter())
                                .addLast(new PacketDecoder())
                                .addLast(new LoginRequestHandler())
                                .addLast(new MessageRequestHandler())
                                .addLast(new PacketEncoder());
                    }
                });


        serverBootstrap
                // handler() 服务端启动过程中的一些逻辑处理,通常情况用不到这个方法
                .handler(new ChannelInitializer<ServerSocketChannel>() {
                    @Override
                    protected void initChannel(ServerSocketChannel serverSocketChannel) throws Exception {
                        log.info("取出attr属性:{}", serverSocketChannel.attr(CommonConfig.SERVER_NAME_KEY).get());
                        log.info("服务端启动中...............");
                    }
                })
                // attributes() 给服务端的channel即NioServerSocketChannel指定一些自定义属性,通过channel.attributes()取出该属性,给NioServerSocketChannel维护一个map
                .attr(CommonConfig.SERVER_NAME_KEY, CommonConfig.SERVER_NAME_VALUE)

                // childAttr() 给每一条连接指定自定义属性,通过channel.attributes()取出该属性,对应的是childHandler
                .childAttr(CommonConfig.CLIENT_KEY, CommonConfig.CLIENT_VALUE)

                // childOption() 可以给每条连接设置一些TCP底层相关的属性
                // ChannelOption.SO_KEEPALIVE 表示是否开启TCP底层心跳机制,true为开启
                .childOption(ChannelOption.SO_KEEPALIVE, true)
                // ChannelOption.TCP_NODELAY 表示是否开启Nagle算法,true表示关闭,false表示开启,通俗地说,如果要求高实时性,有数据发送时就马上发送,就关闭,如果需要减少发送次数减少网络交互,就开启.
                .childOption(ChannelOption.TCP_NODELAY, true)
                // ChannelOption.SO_REUSEADDR 表示端口释放后立即就可以被再次使用,因为一般来说,一个端口释放后会等待两分钟之后才能再被使用
                .childOption(ChannelOption.SO_REUSEADDR, true)
                // option() 给服务端channel设置一些属性
                // ChannelOption.SO_BACKLOG 表示系统用于临时存放已完成三次握手的请求的队列的最大长度,如果连接建立频繁,服务器处理创建新连接较慢,适当调大该参数
                .option(ChannelOption.SO_BACKLOG, 1024);


        // 绑定端口
        bind(serverBootstrap, CommonConfig.NETTY_PORT);
    }

    public static void bind(final ServerBootstrap serverBootstrap, final int port) {
        serverBootstrap.bind(port)
                .addListener(new GenericFutureListener<Future<? super Void>>() {
                    @Override
                    public void operationComplete(Future<? super Void> future) throws Exception {
                        if (future.isSuccess()) {
                            log.info("端口:{}>绑定成功", port);
                        } else {
                            log.info("端口:{}>绑定失败", port);
                            bind(serverBootstrap, port + 1);
                        }
                    }
                });
    }
}
