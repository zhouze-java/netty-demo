package com.example.netty.handler.group;

import com.example.netty.packet.group.CreateGroupRequestPacket;
import com.example.netty.packet.group.CreateGroupResponsePacket;
import com.example.netty.util.IDUtil;
import com.example.netty.util.SessionUtil;
import io.netty.channel.Channel;
import io.netty.channel.ChannelHandler;
import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.SimpleChannelInboundHandler;
import io.netty.channel.group.ChannelGroup;
import io.netty.channel.group.DefaultChannelGroup;
import lombok.extern.slf4j.Slf4j;

import java.util.ArrayList;
import java.util.List;

/**
 * @author 周泽
 * @date Create in 11:56 2019/6/25
 * @Description 创建群聊处理
 */
@Slf4j
@ChannelHandler.Sharable
public class CreateGroupRequestHandler extends SimpleChannelInboundHandler<CreateGroupRequestPacket> {

    public static final CreateGroupRequestHandler INSTANCE  = new CreateGroupRequestHandler();

    protected CreateGroupRequestHandler(){}

    @Override
    protected void channelRead0(ChannelHandlerContext channelHandlerContext, CreateGroupRequestPacket createGroupRequestPacket) throws Exception {
        List<String> userIdList = createGroupRequestPacket.getUserIdList();

        List<String> userNameList = new ArrayList<>();

        // 创建一个channelGroup分组
        ChannelGroup channelGroup = new DefaultChannelGroup(channelHandlerContext.executor());

        // 把这个分组中用户的channel加到channelGroup中
        for (String userId : userIdList) {
            Channel channel = SessionUtil.getChannel(userId);
            if (channel != null) {
                channelGroup.add(channel);
                userNameList.add(SessionUtil.getSession(channel).getUserName());
            }
        }

        // 返回响应
        CreateGroupResponsePacket createGroupResponsePacket = new CreateGroupResponsePacket();
        createGroupResponsePacket.setGroupId(IDUtil.randomId());
        createGroupResponsePacket.setSuccess(true);
        createGroupResponsePacket.setUserNameList(userNameList);

        // 给每个客户端都发送响应
        channelGroup.writeAndFlush(createGroupResponsePacket);

        log.info("服务端创建群聊成功,id是[{}]", createGroupResponsePacket.getGroupId());
    }

}
