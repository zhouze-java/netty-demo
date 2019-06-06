package com.example.netty.code;

import lombok.Data;

/**
 * @author 周泽
 * @date Create in 14:57 2019/6/5
 * @Description 通信对象的抽象类
 */
@Data
public abstract class Packet {

    /**
     * 协议版本号
     */
    private Byte version = 1;

    /**
     * 获取指令
     * @return 指令
     */
    public abstract Byte getCommand();

}
