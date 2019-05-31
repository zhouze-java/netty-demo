package com.example.netty.common;

import io.netty.util.AttributeKey;

/**
 * @author 周泽
 * @date Create in 15:28 2019/5/30
 * @Description 公共静态常量类
 */
public class CommonConfig {

    /**
     * 服务端ip
     */
    public static final String NETTY_HOST = "127.0.0.1";

    /**
     * 绑定端口
     */
    public static final int NETTY_PORT = 8000;

    /**
     * 尝试重新连接次数
     */
    public static final int MAX_RETRY = 5;

    /**
     * serverNameKey
     */
    public static final AttributeKey<Object> SERVER_NAME_KEY = AttributeKey.newInstance("serverName");

    /**
     * saerverNameValue
     */
    public static final String SERVER_NAME_VALUE = "nettyServer";

    /**
     * clientKey
     */
    public static final AttributeKey<Object> CLIENT_KEY = AttributeKey.newInstance("clientKey");

    /**
     * clientValue
     */
    public static final String CLIENT_VALUE = "clientValue";

    /**
     * clientNameKey
     */
    public static final AttributeKey<Object> CLIENT_NAME_KEY = AttributeKey.newInstance("ClientName");

    /**
     * ClientNameValue
     */
    public static final String CLIENT_NAME_VALUE = "nettyClient";



}
