package com.example.netty.handler.inbound;

import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.ChannelInboundHandlerAdapter;
import lombok.extern.slf4j.Slf4j;

/**
 * @author 周泽
 * @date Create in 15:41 2019/6/12
 * @Description
 */
@Slf4j
public class InBoundHandlerC extends ChannelInboundHandlerAdapter {

    @Override
    public void channelRead(ChannelHandlerContext ctx, Object msg) throws Exception {
        log.info("InBoundHandlerC: {}", msg);
        ctx.channel().writeAndFlush(msg);
    }

}
