package com.example.netty.util;

import com.example.netty.attributes.Attributes;
import io.netty.channel.Channel;

/**
 * @author 周泽
 * @date Create in 14:11 2019/6/11
 * @Description 登录工具类
 */
public class LoginUtil {

    /**
     * 标识已经登录
     * @param channel
     */
    public static void markAsLogin(Channel channel) {
        channel.attr(Attributes.LOGIN).set(true);
    }

    /**
     * 判断当前是否有登录的标识(只要标识存在,不管标识的值是什么)
     * @param channel
     * @return 是否已经登录
     */
    public static boolean hasLogin(Channel channel) {
        return channel.attr(Attributes.LOGIN).get() != null;
    }

}
