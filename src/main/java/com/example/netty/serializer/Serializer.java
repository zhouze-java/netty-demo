package com.example.netty.serializer;

/**
 * @author 周泽
 * @date Create in 15:35 2019/6/5
 * @Description 序列化接口
 */
public interface Serializer {

    /**
     * json 序列化
     */
    byte JSON_SERIALIZER = 1;


    Serializer DEFAULT = new JSONSerializer();

    /**
     * 序列化算法
     */
    byte getSerializerAlgorithm();

    /**
     * java 对象转换成二进制
     */
    byte[] serialize(Object object);

    /**
     * 二进制转换成 java 对象
     */
    <T> T deserialize(Class<T> clazz, byte[] bytes);
}
