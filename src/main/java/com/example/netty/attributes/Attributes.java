package com.example.netty.attributes;

import com.example.netty.session.Session;
import io.netty.util.AttributeKey;

/**
 * @author 周泽
 * @date Create in 11:21 2019/6/11
 * @Description 针对channel的attr配置
 */
public interface Attributes {

    /**
     * 客户端是否登录的标识
     */
    AttributeKey<Boolean> LOGIN = AttributeKey.newInstance("login");

    /**
     * 登录标识
     */
    AttributeKey<Session> SESSION = AttributeKey.newInstance("session");
}
