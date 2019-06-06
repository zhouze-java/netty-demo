package com.example.netty;

import com.example.netty.code.LoginRequestPacket;
import com.example.netty.code.Packet;
import com.example.netty.code.PacketCodeC;
import com.example.netty.serializer.Serializer;
import io.netty.buffer.ByteBuf;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

@RunWith(SpringRunner.class)
@SpringBootTest
public class AppTests {

    @Test
    public void contextLoads() {

        // 序列化
        Serializer serializer = Serializer.DEFAULT;

        // 登录请求数据包
        LoginRequestPacket loginRequestPacket = new LoginRequestPacket();

        loginRequestPacket.setVersion((byte) 1);
        loginRequestPacket.setUserId(1);
        loginRequestPacket.setUsername("zhangsan");
        loginRequestPacket.setPassword("123");

        PacketCodeC packetCodeC = new PacketCodeC();
        // 编码
        ByteBuf byteBuf = packetCodeC.encode(loginRequestPacket);

        // 解码
        Packet packet = packetCodeC.decode(byteBuf);

        // 对比解码前后的序列化后的对象
        Assert.assertArrayEquals(serializer.serialize(loginRequestPacket), serializer.serialize(packet));

    }


}
