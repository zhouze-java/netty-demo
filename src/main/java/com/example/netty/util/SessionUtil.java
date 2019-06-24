package com.example.netty.util;

import com.example.netty.attributes.Attributes;
import com.example.netty.session.Session;
import io.netty.channel.Channel;
import lombok.extern.slf4j.Slf4j;

import java.util.Calendar;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

/**
 * @author 周泽
 * @date Create in 9:51 2019/6/24
 * @Description
 */
@Slf4j
public class SessionUtil {

    /**
     * userId 和 channel 的映射
     */
    private static final Map<String, Channel> userIdChannelMap = new ConcurrentHashMap<>();

    /**
     * channel 和 session 的绑定
     * @param session
     * @param channel
     */
    public static void bindSession(Session session, Channel channel) {
        userIdChannelMap.put(session.getUserId(), channel);
        channel.attr(Attributes.SESSION).set(session);
    }

    /**
     * 解绑session
     * @param channel
     */
    public static void unBindSession(Channel channel){
        if (hasLogin(channel)) {
            userIdChannelMap.remove(getSession(channel).getUserId());
            channel.attr(Attributes.SESSION).set(null);
        }
    }

    /**
     * 通过channel去取session
     * @param channel
     * @return session
     */
    public static Session getSession(Channel channel){
        return channel.attr(Attributes.SESSION).get();
    }

    /**
     * 通过用户id去获取channel
     * @param userId 用户id
     * @return channel
     */
    public static Channel getChannel(String userId){
        return userIdChannelMap.get(userId);
    }

    /**
     * 判断channel中是否有session这个变量
     * @param channel
     * @return true/false
     */
    public static boolean hasLogin(Channel channel) {
        return channel.hasAttr(Attributes.SESSION);
    }
}
